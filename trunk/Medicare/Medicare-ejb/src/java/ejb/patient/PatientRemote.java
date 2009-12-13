package ejb.patient;

import javax.ejb.Remote;
import util.MeasurementDetails;
import util.PatientDetails;
import util.TaskDetails;

/**
 *
 * @author Kristof
 */
@Remote
public interface PatientRemote {
    boolean addPatient(String SSN, String username, String password);
    boolean addMeasurement(String username, String password, MeasurementDetails md);
    PatientDetails getPatientDetails(String username, String password);
    TaskDetails getTask(String username, String password);
    boolean updateTask(String username, String password,String taskid, String newstatus);
}
