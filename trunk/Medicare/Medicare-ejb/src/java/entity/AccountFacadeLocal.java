/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Collection;
import javax.ejb.Local;
import util.AccountDetails;

/**
 *
 * @author Kristof
 */
@Local
public interface AccountFacadeLocal {
    boolean isValidPassword(String name, String password);
    void createAccount(String name, String password);
    boolean exists(String name);
    AccountDetails getAccount(String name);
    AccountDetails getAccount(Long id);
    Collection<String> getAllAccounts();
    void addFriend(String name, String friend);
    void removeFriend(String name, String friend);
    void removeAccount(String name);
}
