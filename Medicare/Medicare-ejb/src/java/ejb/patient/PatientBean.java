package ejb.patient;

import entity.measurement.MeasurementFacadeLocal;
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
    private MeasurementFacadeLocal measurementFacadeBean;

    @EJB
    private TaskFacadeLocal taskFacadeBean;
    
    public boolean addPatient(String SSN, String username, String password) {
        try {
            patientFacadeBean.createPatient(SSN, username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public TaskDetails getTask(String username, String password) {
        if(patientFacadeBean.isValid(username,password)) {
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
            return null;
        }
        else
            return null;
    }
    public boolean updateTask(String username, String password, String taskid, String newstatus) {
        if(patientFacadeBean.isValid(username,password))
            return taskFacadeBean.updateTask(Long.parseLong(taskid),newstatus);
        else
            return false;
    }
    public PatientDetails getPatientDetails(String username, String password) {
        if(patientFacadeBean.isValid(username,password))
            return patientFacadeBean.getPatient(username);
        else
            return null;
    }
    public boolean addMeasurement(String username, String password, MeasurementDetails md) {
        if(patientFacadeBean.isValid(username, password)) {
            patientFacadeBean.addMeasurement(username, md);
            return true;
        }
        else
            return false;
    }

}



