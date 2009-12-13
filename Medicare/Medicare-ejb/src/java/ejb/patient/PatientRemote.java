package ejb.patient;

import java.util.List;
import javax.ejb.Remote;
import util.MeasurementDetails;
import util.PatientDetails;
import util.PrescriptionDetails;
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
    boolean updateTask(String prescriptionid, String taskid, String newstatus);
    List<PrescriptionDetails> getPrescriptions(String username);
    List<String> getMedications(String username);
}