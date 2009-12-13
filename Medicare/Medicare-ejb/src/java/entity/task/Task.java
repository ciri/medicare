package entity.task;

import entity.prescription.Prescription;
import java.io.Serializable;
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
import javax.persistence.Temporal;

/**
 *
 * @author ciri
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "entity.task.Task.findTaskByPrescriptionid",
                query = "SELECT t FROM Task t WHERE t.prescription.id = :prescriptionid")
})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String status;

    @Column(columnDefinition="timestamp")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date tasktime;

    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="PRESCRIPTION_ID")
    private Prescription prescription;

    public Task() {}
    public Task(String status, Date tasktime) {
        setStatus(status);
        setTasktime(tasktime);
    }

    /**
     * Get the value of prescription
     *
     * @return the value of prescription
     */
    public Prescription getPrescription() {
        return prescription;
    }

    /**
     * Set the value of prescription
     *
     * @param tasktime new value of prescription
     */
    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    /**
     * Get the value of tasktime
     *
     * @return the value of tasktime
     */
    public Date getTasktime() {
        return tasktime;
    }

    /**
     * Set the value of tasktime
     *
     * @param tasktime new value of tasktime
     */
    public void setTasktime(Date tasktime) {
        this.tasktime = tasktime;
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.task.Task[id=" + id + "]";
    }

}
