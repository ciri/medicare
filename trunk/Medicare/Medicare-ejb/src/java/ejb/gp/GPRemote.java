/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.gp;

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
    boolean addPatient(String gp_name, String gp_password, String p_name);
    GPDetails getGPDetails(String gp_name,String gp_password);

    PatientDetails getPatientDetails(String gp_name,String gp_password,String patient_name);
    boolean setPatientInformation(String gp_name,String gp_password,PatientDetails patient);
}
