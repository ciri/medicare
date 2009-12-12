package util;

import java.io.Serializable;
import java.util.Collection;

public class PatientDetails implements Serializable {

    private Long id;

    private String SSN;
    private String username;

    private String name;
    private Collection<String> gps;

    public PatientDetails(){};
    
    public PatientDetails(Long id, String SSN,String username) {
        this.id = id;
        this.SSN = SSN;
        this.username = username;
    }

    public Collection<String> getGps() {
        return gps;
    }
    public void setGps(Collection gps) {
        this.gps = gps;
    }
    public String getSSN() {
        return SSN;
    }
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
