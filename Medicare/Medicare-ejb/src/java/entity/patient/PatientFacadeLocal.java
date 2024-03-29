package entity.patient;

import java.util.List;
import javax.ejb.Local;
import util.MeasurementDetails;
import util.PatientDetails;
import util.PrescriptionDetails;

/**
 *
 * @author ciri
 */
@Local
public interface PatientFacadeLocal {
    public boolean  createPatient(String SSN, String username, String password);
    public boolean  editPatient(PatientDetails patient);
    
    public PatientDetails       getPatient(String patient_name);
    public List<PatientDetails> getAllPatients();
    
    public void     addMeasurement(String patient_name, MeasurementDetails md);
    public boolean  addPrescription(String p_username, PrescriptionDetails pd);

    public boolean  isValid(String username, String password);
}
