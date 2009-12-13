package entity.task;

import entity.prescription.Prescription;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.TaskDetails;

/**
 *
 * @author ciri
 */
@Stateless
public class TaskFacadeBean implements TaskFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void addNextTask(Long prescriptionid) {
        addNextTask(prescriptionid,new Long(-1));
    }
    public void addNextTask(Long prescriptionid, Long previoustaskid) {
        Prescription p = findPrescriptionById(prescriptionid);

        Task previoustask = findById(previoustaskid);
        Task nexttask = new Task();
        nexttask.setStatus("in progress");
        nexttask.setPrescription(findPrescriptionById(prescriptionid));
        

        //Add next timestamp, or the starttime of the prescription if this is the first time we're adding it
        Calendar c = new GregorianCalendar();
        if(previoustask == null)
            c.setTime(p.getStarttime());
        else
            c.setTime(previoustask.getTasktime());

        if(p.getFrequency() != 0) {
            int nextyear = c.get(Calendar.YEAR);
            int nextday  = c.get(Calendar.DAY_OF_YEAR);
            int nexthour = c.get(Calendar.HOUR_OF_DAY) + 24/p.getFrequency();

            if(nexthour >= 24) {
                nexthour = nexthour - 24;
                nextday++;
                if( nextday == 31 && c.get(Calendar.MONTH) == Calendar.DECEMBER) {
                    nextyear ++;
                    nextday = 1;
                }
            }
            System.out.println("TaskFacadeBean.addNextTask("+prescriptionid+")  : ");
            c.set(Calendar.HOUR_OF_DAY, nexthour);
            c.set(Calendar.DAY_OF_YEAR, nextday);
            c.set(Calendar.YEAR,nextyear);

            Date next_medication = c.getTime();
            
            if(next_medication.compareTo(p.getEndtime()) < 0) {
                nexttask.setTasktime(next_medication);
                persist(nexttask);
            }
        }
    }
    public boolean updateTask(Long taskid, String newstatus) {
        Task t = findById(taskid);
        if(t == null) return false;
        
        t.setStatus(newstatus);
        persist(t);
        return true;
    }
    public List<TaskDetails> getTaskForPrescription(Long pid) {
        List<Task> tasks =  (List<Task>) em.createNamedQuery("entity.task.Task.findTaskByPrescriptionid").setParameter("prescriptionid", pid).getResultList();
        List<TaskDetails> taskdetails = new ArrayList<TaskDetails>();

        for(Task task : tasks) {
            TaskDetails td = convert(task);
            taskdetails.add( td );
        }
        return taskdetails;
    }
    private TaskDetails convert(Task t) {
        return new TaskDetails(
                t.getId(),
                t.getStatus(),
                t.getTasktime()
        );
    }
    public Task findById(Long id) {
        return em.find(Task.class, id);
    }
    public Prescription findPrescriptionById(Long id) {
        return em.find(Prescription.class, id);
    }
    public void persist(Object object) {
        em.persist(object);
    }
}
