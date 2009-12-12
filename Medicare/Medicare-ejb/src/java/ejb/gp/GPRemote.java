/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.gp;

import java.util.List;
import javax.ejb.Remote;
import util.GPDetails;
import util.PatientDetails;

@Remote
public interface GPRemote {

    /*AccountDetails getAccountInformation(String username, String password);
    boolean addFriend(String username, String password, String friend);
    boolean removeFriend(String username, String password, String friend);
    boolean removeAccount(String username, String password);
    boolean isValidAccount(String username, String password);*/

    boolean addGP(String username, String password);
    GPDetails getGPDetails(String gp_name,String gp_password);

    /* Patient operations */
    boolean addPatient(String gp_name, String gp_password, String p_name);
    boolean createPatient(String gp_username, String gp_password,
                          String p_SSN, String p_username, String p_password);
    boolean setPatientInformation(String gp_name,String gp_password,PatientDetails patient);

    PatientDetails getPatientDetails(String gp_name,String gp_password,String patient_name);
    List<PatientDetails> getAllPatientDetails(String gp_name, String gp_password);
}
