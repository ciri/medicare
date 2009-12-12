/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.AccountFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import util.AccountDetails;

/**
 *
 * @author Kristof
 */
@Stateless
public class FriendBean implements FriendRemote {

    @EJB
    private AccountFacadeLocal accountFacadeBean;

    public AccountDetails getAccountInformation(String username, String password) {
        if (accountFacadeBean.isValidPassword(username, password)) {
            return accountFacadeBean.getAccount(username);
        } else {
            return null;
        }
    }

    public boolean addFriend(String username, String password, String friend) {
        if (accountFacadeBean.isValidPassword(username, password)) {
            try {
                accountFacadeBean.addFriend(username, friend);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean removeFriend(String username, String password, String friend) {
        if (accountFacadeBean.isValidPassword(username, password)) {
            try {
                accountFacadeBean.removeFriend(username, friend);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean removeAccount(String username, String password) {
        if (accountFacadeBean.isValidPassword(username, password)) {
            accountFacadeBean.removeAccount(username);
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidAccount(String username, String password) {
        return accountFacadeBean.isValidPassword(username, password);
    }

    public boolean addAccount(String username, String password) {
        try {
            accountFacadeBean.createAccount(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
    
    

