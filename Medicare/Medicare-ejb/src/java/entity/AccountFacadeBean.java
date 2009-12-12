/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.AccountDetails;


/**
 *
 * @author Kristof
 */
@Stateless
public class AccountFacadeBean implements AccountFacadeLocal {

    @PersistenceContext
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public void create(Account account) {
        em.persist(account);
    }

    public void edit(Account account) {
        em.merge(account);
    }

    public void remove(Account account) {
        em.remove(em.merge(account));
    }

    public List<Account> findAll() {
        return em.createQuery("select object(o) from Account as o").getResultList();
    }

    public Account findByName(String name) {
        List<Account> accounts = (List<Account>) em.createNamedQuery("entity.Account.findAccountByName").setParameter("name", name).getResultList();
        if (accounts.size() == 1) {
            return accounts.get(0);
        } else if (accounts.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }

    public Account findById(Long id) {
        return em.find(entity.Account.class, id);
    }

    public void createAccount(String name, String password) {
        if (exists(name))
            throw new IllegalArgumentException();
        Account account = new Account(name, password);
        create(account);
    }

    public boolean isValidPassword(String name, String password) {
        Account account = findByName(name);
        if (account != null)
            return account.getPassword().equals(password);
        else
            return false;
    }

    public boolean exists(String name) {
        return findByName(name) != null;
    }

    public Collection<String> getAllAccounts() {
        List<Account> accounts = (List<Account>) em.createNamedQuery
        ("entity.Account.findAllAccounts").getResultList();
        Collection<String> names = new ArrayList<String>();
        for (Account account : accounts)
            names.add(account.getName());
        return names;
    }

    public void addFriend(String name, String friend) {
        Account account = findByName(name);
        account.addFriend(findByName(friend));
        edit(account);
    }

    public void removeFriend(String name, String friend) {
        Account account = findByName(name);
        account.removeFriend(findByName(friend));
        edit(account);
    }

    public void removeAccount(String name) {
        Account account = findByName(name);
        List<Account> accounts = (List<Account>) em.createNamedQuery
         ("entity.Account.findAccountByFriend")
          .setParameter("friend", account).getResultList();
        for (Account acc : accounts)
            if (acc.hasFriend(account))
                acc.removeFriend(account);
        remove(account);
    }

    public AccountDetails getAccount(String name) {
        return convert(findByName(name), true);
    }

    public AccountDetails getAccount(Long id) {
        return convert(findById(id), true);
    }

    private AccountDetails convert(Account account,
                                   boolean includeFriendList) {
        if (account == null)
            return null;
        else {
            Collection<String> friends = new ArrayList<String>();
            if (includeFriendList)
                for (Account friend : account.getFriends())
                    friends.add(friend.getName());
            return new AccountDetails
                    (account.getId(),account.getName(), friends);
        }
    }

}
