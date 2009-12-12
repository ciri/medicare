package util;

import java.io.Serializable;
import java.util.List;

public class PatientDetails implements Serializable {

    private Long id;
    private String name;
    private String username;
    private List<String> gps;

    public PatientDetails(){};
    public PatientDetails(Long id, String name,String username, List<String> gps) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.gps = gps;
    }

    public List<String> getGps() {
        return gps;
    }

    public void setGps(List gps) {
        this.gps = gps;
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
