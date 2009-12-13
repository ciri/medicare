package entity.prescription;

import entity.medication.Medication;
import entity.patient.Patient;
import entity.task.Task;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import util.PrescriptionDetails;

@Entity

@NamedQueries({
    @NamedQuery(name = "entity.prescription.Prescription.findPrescriptionByPatientid",
                query = "SELECT p FROM Prescription p WHERE p.patient.id = :patientid")
})
public class Prescription implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private int unit;
    @Column
    private int frequency;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date starttime;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endtime;
    @Column
    private boolean fixed;
    
    @ManyToOne
    private Medication medication;

    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Collection<Task> tasks;
    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="PATIENT_ID")
    private Patient patient;


    public Prescription() {}
    public Prescription(PrescriptionDetails pd) {
        setUnit(pd.getUnit());
        setFrequency(pd.getFrequency());
        setStarttime(pd.getStarttime());
        setEndtime(pd.getEndtime());
        setFixed(pd.isFixed());
        setTasks(pd.getTasks());
    }

    /**
     * Get the value of tasks
     *
     * @return the value of tasks
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Set the value of tasks
     *
     * @param tasks new value of tasks
     */
    public void setPatient (Patient patient) {
        this.patient = patient;
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
     * Get the value of medication
     *
     * @return the value of medication
     */
    public Medication getMedication() {
        return medication;
    }

    /**
     * Set the value of medication
     *
     * @param medication new value of medication
     */
    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    /**
     * Get the value of fixed
     *
     * @return the value of fixed
     */
    public boolean isFixed() {
        return fixed;
    }

    /**
     * Set the value of fixed
     *
     * @param fixed new value of fixed
     */
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    /**
     * Get the value of endtime
     *
     * @return the value of endtime
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * Set the value of endtime
     *
     * @param endtime new value of endtime
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * Get the value of starttime
     *
     * @return the value of starttime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * Set the value of starttime
     *
     * @param starttime new value of starttime
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * Get the value of frequency
     *
     * @return the value of frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Set the value of frequency
     *
     * @param frequency new value of frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * Get the value of unit
     *
     * @return the value of unit
     */
    public int getUnit() {
        return unit;
    }

    /**
     * Set the value of unit
     *
     * @param unit new value of unit
     */
    public void setUnit(int unit) {
        this.unit = unit;
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
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.prescription.Prescription[id=" + id + "]";
    }

}
