package entity.patient;

import entity.gp.GP;
import entity.measurement.Measurement;
import entity.prescription.Prescription;
import entity.task.Task;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ciri
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "entity.patient.Patient.findAllPatients",
                query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "entity.patient.Patient.findPatientByUsername",
                query = "SELECT p FROM Patient p WHERE p.username = :username"),
    @NamedQuery(name = "entity.patient.Patient.findPatientBySSN",
                query = "SELECT p FROM Patient p WHERE p.SSN = :SSN")
})

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Required
    @Column(unique=true)
    private String SSN;
    @Column(unique=true)
    private String username;
    @Column
    private String password;

    //Optional
    @Column
    private String name;
    @Column
    private String bloodgroup;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;

    @OneToOne
    private Address address;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastconsult;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date firstconsult;

    @OneToMany
    private Collection<Task> tasks;
    @OneToMany
    private Collection<Measurement> measurements;
    @OneToMany
    private Collection<Prescription> prescriptions;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "patients")
    private Collection<GP> gps;
    
    public Patient() { }
    public Patient(String SSN, String username, String password) {
        setSSN(SSN);
        setName(username);
        setUsername(username);
        setPassword(password);
    }

    /**
     * Get the value of prescriptions
     *
     * @return the value of prescriptions
     */
    public Collection getPrescriptions() {
        return prescriptions;
    }

    /**
     * Set the value of prescriptions
     *
     * @param prescriptions new value of prescriptions
     */
    public void setPrescriptions(Collection prescriptions) {
        this.prescriptions = prescriptions;
    }

    /**
     * Get the value of measurements
     *
     * @return the value of measurements
     */
    public Collection getMeasurements() {
        return measurements;
    }

    /**
     * Set the value of measurements
     *
     * @param measurements new value of measurements
     */
    public void setMeasurements(Collection measurements) {
        this.measurements = measurements;
    }

    /**
     * Get the value of tasks
     *
     * @return the value of tasks
     */
    public Collection getTasks() {
        return tasks;
    }

    /**
     * Set the value of tasks
     *
     * @param tasks new value of tasks
     */
    public void setTasks(Collection tasks) {
        this.tasks = tasks;
    }

    /**
     * Get the value of firstconsult
     *
     * @return the value of firstconsult
     */
    public Date getFirstconsult() {
        return firstconsult;
    }

    /**
     * Set the value of firstconsult
     *
     * @param firstconsult new value of firstconsult
     */
    public void setFirstconsult(Date firstconsult) {
        this.firstconsult = firstconsult;
    }

    /**
     * Get the value of lastconsult
     *
     * @return the value of lastconsult
     */
    public Date getLastconsult() {
        return lastconsult;
    }

    /**
     * Set the value of lastconsult
     *
     * @param lastconsult new value of lastconsult
     */
    public void setLastconsult(Date lastconsult) {
        this.lastconsult = lastconsult;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get the value of birthday
     *
     * @return the value of birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set the value of birthday
     *
     * @param birthday new value of birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Get the value of bloodgroup
     *
     * @return the value of bloodgroup
     */
    public String getBloodgroup() {
        return bloodgroup;
    }

    /**
     * Set the value of bloodgroup
     *
     * @param bloodgroup new value of bloodgroup
     */
    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    /**
     * Get the value of SSN
     *
     * @return the value of SSN
     */
    public String getSSN() {
        return SSN;
    }

    /**
     * Set the value of SSN
     *
     * @param SSN new value of SSN
     */
    public void setSSN(String SSN) {
        this.SSN = SSN;
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
    public Collection<GP> getGps() {
        return gps;
    }

    /**
     * Set the value of gps
     *
     * @param GPs new value of gps
     */
    public void setGPs(Collection<GP> gps) {
        this.gps = gps;
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
