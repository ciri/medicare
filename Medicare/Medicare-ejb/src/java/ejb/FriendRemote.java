/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Remote;
import util.AccountDetails;

/**
 *
 * @author Kristof
 */
@Remote
public interface FriendRemote {

    AccountDetails getAccountInformation(String username, String password);
    boolean addFriend(String username, String password, String friend);
    boolean removeFriend(String username, String password, String friend);
    boolean removeAccount(String username, String password);
    boolean isValidAccount(String username, String password);
    boolean addAccount(String username, String password);

    
}
