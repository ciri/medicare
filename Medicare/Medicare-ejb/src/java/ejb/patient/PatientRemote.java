package ejb.patient;

import javax.ejb.Remote;
import util.MeasurementDetails;
import util.PatientDetails;
import util.TaskDetails;

/**
 *
 * @author ciri
 */
@Remote
public interface PatientRemote {
    boolean addMeasurement(String username, MeasurementDetails md);
    PatientDetails getPatientDetails(String username);
    TaskDetails getTask(String username);
    boolean updateTask(String taskid, String newstatus);
}