/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.patient;

import java.util.List;
import javax.ejb.Local;
import util.PatientDetails;

/**
 *
 * @author ciri
 */
@Local
public interface PatientFacadeLocal {
    public boolean createPatient(String SSN, String username, String password);
    public PatientDetails getPatient(String patient_name);
    public boolean editPatient(PatientDetails patient);
    public List<PatientDetails> getAllPatients();
}
