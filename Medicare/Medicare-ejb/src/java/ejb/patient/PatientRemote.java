/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.patient;

import javax.ejb.Remote;
import util.AccountDetails;
import util.MeasurementDetails;
import util.PatientDetails;

/**
 *
 * @author Kristof
 */
@Remote
public interface PatientRemote {
    boolean addPatient(String SSN, String username, String password);
    boolean addMeasurement(String username, String password, MeasurementDetails md);
}
