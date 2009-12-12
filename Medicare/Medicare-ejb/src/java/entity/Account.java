/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Kristof
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "entity.Account.findAllAccounts",
                query = "SELECT p FROM Account p"),
    @NamedQuery(name = "entity.Account.findAccountByName",
                query = "SELECT p FROM Account p WHERE p.name = :name"),
    @NamedQuery(name = "entity.Account.findAccountByFriend",
                query = "SELECT DISTINCT p FROM Account p, IN(p.friends) f WHERE f = :friend")
})
public class Account implements Serializable {

    @ManyToMany
    private Collection<Account> friends;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
        //this.friends = new ArrayList<Account>();
    }

    public Account()
    {}

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

//    @ManyToMany
//    protected Collection<Account> friends;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of friends
     *
     * @return the value of friends
     */
    public Collection<Account> getFriends() {
        return friends;
    }

    /**
     * Set the value of friends
     *
     * @param friends new value of friends
     */
    public void setFriends(Collection<Account> friends) {
        this.friends = friends;
    }


    public boolean addFriend(Account friend)
    {
        if(friend == null || friends.contains(friend) || friend.equals(this))
        {
            return false;
        }
       else
        {
            return friends.add(friend);
        }
    }

    public boolean removeFriend(Account friend)
    {
       if(friends.contains(friend))
        {
            return friends.remove(friend);
        }
        else
        {
            return false;
        }
    }

    public boolean hasFriend(Account friend)
    {
        return friends.contains(friend);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Account[id=" + id + "]";
    }

}
