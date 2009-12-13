package ejb.gp;

import java.util.List;
import javax.ejb.Remote;
import util.GPDetails;
import util.MeasurementDetails;
import util.MedicationDetails;
import util.PatientDetails;
import util.PrescriptionDetails;
import util.TaskDetails;

@Remote
public interface GPRemote {
    boolean addGP(String username, String password);
    GPDetails getGPDetails(String gp_name);

    /* Patient operations */
    boolean addPatient(String gp_username, String p_username);
    boolean createPatient(String gp_username,
                          String p_SSN, String p_username, String p_password);
    boolean setPatientInformation(PatientDetails patient);

    PatientDetails getPatientDetails(String p_username);
    List<PatientDetails> getAllPatientDetails();

    /* Measurement operations */
    boolean addMeasurement(String p_username, MeasurementDetails md);
    List<String> getMeasurementTypes(String p_username);

    /* Medication operations */
    boolean addMedication(MedicationDetails md);
    List<MedicationDetails> getAllMedications();
    MedicationDetails getMedication(String m);
    
    /**
     * Adds a prescription, but also adds the necessary prescription tasks ...
     * 
     * @param gp_name
     * @param gp_password
     * @param p_username
     * @param pd
     * @return
     */
    boolean addPrescription( String p_username, PrescriptionDetails pd );
    List<PrescriptionDetails> getPrescriptions(String p_username);

    List<TaskDetails> getTasks(String p_username);
}
