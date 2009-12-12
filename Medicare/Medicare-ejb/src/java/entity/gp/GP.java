package entity.gp;

import entity.patient.Patient;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = "entity.gp.GP.findAllGps",
                query = "SELECT p FROM GP p"),
    @NamedQuery(name = "entity.gp.GP.findGpByName",
                query = "SELECT p FROM GP p WHERE p.name = :name")
})

public class GP implements Serializable {
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

    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Patient> patients;

    public GP() { }
    public GP(String username, String password) {
        setUsername(username);
        setName(username);
        setPassword(password);
    }


    /**
     * Get the value of patients
     *
     * @return the value of patients
     */
    public Collection getPatients() {
        return patients;
    }

    /**
     * Set the value of patients
     *
     * @param patients new value of patients
     */
    public void setPatients(Collection patients) {
        this.patients = patients;
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
        if (!(object instanceof GP)) {
            return false;
        }
        GP other = (GP) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.gp.Gp[id=" + id + "]";
    }

    public boolean addPatient(Patient patient) {
        if(patient == null || patients.contains(patient)) {
            return false;
        }
        else {
            return patients.add(patient);
        }
    }
}
