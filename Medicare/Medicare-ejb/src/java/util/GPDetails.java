package util;

import java.io.Serializable;
import java.util.Collection;

public class GPDetails implements Serializable {

    private Long id;
    private String name;
    private Collection<PatientDetails> patients;

    public GPDetails(Long id, String name, Collection<PatientDetails> patients) {
        this.id = id;
        this.name = name;
        this.patients = patients;
    }

    public Collection<PatientDetails> getPatients() {
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
