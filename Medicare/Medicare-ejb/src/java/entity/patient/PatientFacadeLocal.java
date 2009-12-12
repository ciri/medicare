/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.patient;

import javax.ejb.Local;
import util.PatientDetails;

/**
 *
 * @author ciri
 */
@Local
public interface PatientFacadeLocal {
    public void createPatient(String username, String password);
    public PatientDetails getPatient(String patient_name);
    public boolean editPatient(PatientDetails patient);
}
