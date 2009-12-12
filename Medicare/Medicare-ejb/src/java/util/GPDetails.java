package util;

import java.io.Serializable;
import java.util.Collection;

public class GPDetails implements Serializable {

    private Long id;
    private String name;
    private Collection<String> patients;

    public GPDetails(Long id, String name, Collection<String> patients) {
        this.id = id;
        this.name = name;
        this.patients = patients;
    }

    public Collection<String> getPatients() {
        return patients;
    }

    public void setFriends(Collection patients) {
        this.patients = patients;
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
