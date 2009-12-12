/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.patient;

import javax.ejb.Remote;
import util.AccountDetails;
import util.PatientDetails;

/**
 *
 * @author Kristof
 */
@Remote
public interface PatientRemote {

    /*AccountDetails getAccountInformation(String username, String password);
    boolean addFriend(String username, String password, String friend);
    boolean removeFriend(String username, String password, String friend);
    boolean removeAccount(String username, String password);
    boolean isValidAccount(String username, String password);*/

    boolean addPatient(String username, String password);
}
