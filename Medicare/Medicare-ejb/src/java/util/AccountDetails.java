package util;

import java.io.Serializable;
import java.util.Collection;

public class AccountDetails implements Serializable {

    private Long id;
    private String name;
    private Collection<String> friends;

    public AccountDetails(Long id, String name, Collection<String> friends) {
        this.id = id;
        this.name = name;
        this.friends = friends;
    }

    public Collection<String> getFriends() {
        return friends;
    }

    public void setFriends(Collection friends) {
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
