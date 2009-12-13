/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.gp;

import java.util.List;
import javax.ejb.Remote;
import util.GPDetails;
import util.MeasurementDetails;
import util.MedicationDetails;
import util.PatientDetails;
import util.PrescriptionDetails;

@Remote
public interface GPRemote {
    boolean addGP(String username, String password);
    GPDetails getGPDetails(String gp_name,String gp_password);

    /* Patient operations */
    boolean addPatient(String gp_name, String gp_password, String p_username);
    boolean createPatient(String gp_username, String gp_password,
                          String p_SSN, String p_username, String p_password);
    boolean setPatientInformation(String gp_name,String gp_password,PatientDetails patient);

    PatientDetails getPatientDetails(String gp_name,String gp_password,String p_username);
    List<PatientDetails> getAllPatientDetails(String gp_name, String gp_password);

    /* Measurement operations */
    boolean addMeasurement(String gp_name, String gp_password, String p_username, MeasurementDetails md);
    List<String> getMeasurementTypes(String gp_name,String gp_password, String p_username);

    /* Medication operations */
    boolean addMedication(String gp_name, String gp_password, MedicationDetails md);
    List<MedicationDetails> getAllMedications(String gp_name, String gp_password);
    MedicationDetails getMedication(String gp_name, String gp_password, String m);
    
    /* Prescription operations */
    /**
     * Adds a prescription, but also adds the necessary prescription tasks ...
     * 
     * @param gp_name
     * @param gp_password
     * @param p_username
     * @param pd
     * @return
     */
    boolean addPrescription(String gp_name, String gp_password,
                            String p_username,
                            PrescriptionDetails pd
    );
}
