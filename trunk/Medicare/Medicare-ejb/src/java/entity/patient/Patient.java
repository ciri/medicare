package entity.patient;

import entity.gp.GP;
import java.io.Serializable;
import java.util.List;
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
 * @author ciri
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "entity.patient.Patient.findAllPatients",
                query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "entity.patient.Patient.findPatientByName",
                query = "SELECT p FROM Patient p WHERE p.name = :name")
})

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String username;
    @Column
    private String password;
    @Column(unique=true)
    private String name;
    @ManyToMany(mappedBy = "patients")
    private List<GP> GPs;
    
    public Patient() { }
    public Patient(String username, String password) {
        setName(username);
        setUsername(username);
        setPassword(password);
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
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of gps
     *
     * @return the value of gps
     */
    public List<GP> getGPs() {
        return GPs;
    }

    /**
     * Set the value of gps
     *
     * @param GPs new value of gps
     */
    public void setGPs(List<GP> GPs) {
        this.GPs = GPs;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.patient.Patient[id=" + id + "]";
    }

}
