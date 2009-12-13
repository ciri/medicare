package ejb.patient;

import entity.patient.PatientFacadeLocal;
import entity.task.TaskFacadeLocal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import util.MeasurementDetails;
import util.PatientDetails;
import util.PrescriptionDetails;
import util.TaskDetails;

/**
 *
 * @author ciri
 */
@Stateless
public class PatientBean implements PatientRemote {

    @EJB
    private PatientFacadeLocal patientFacadeBean;

    @EJB
    private TaskFacadeLocal taskFacadeBean;
    
    public TaskDetails getTask(String username) {
        List<TaskDetails> task_aggregate = new ArrayList<TaskDetails>();
            
        PatientDetails pd = patientFacadeBean.getPatient(username);

        Collection<PrescriptionDetails> prescriptions = pd.getPrescriptions();
        if(prescriptions != null)
        for(PrescriptionDetails prescription :  prescriptions) {
            Collection<TaskDetails> tasks = prescription.getTasks();
            if(tasks != null)
            for(TaskDetails task : tasks) {
                if(task.getStatus() != null && task.getStatus().equals("in progress")) {
                    task.setPrescription(prescription);
                    task_aggregate.add(task);
                }
             }
        }
        if(!task_aggregate.isEmpty()) {
            Collections.sort(task_aggregate);
            TaskDetails curr_task = task_aggregate.get(0);
            curr_task.setDose(curr_task.getPrescription().getMedication().getStandarddose());
            return curr_task;
        }
        else
            return null;
    }
    public boolean updateTask( String taskid, String newstatus ) {
        return taskFacadeBean.updateTask(Long.parseLong(taskid),newstatus);
    }
    public PatientDetails getPatientDetails( String username ) {
        return patientFacadeBean.getPatient(username);
    }
    public boolean addMeasurement(String username,  MeasurementDetails md) {
         patientFacadeBean.addMeasurement(username, md);
         return true;
    }
}