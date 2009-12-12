package entity.prescription;

import entity.medication.Medication;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import util.PrescriptionDetails;

@Entity
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

    public Prescription() {}
    public Prescription(PrescriptionDetails pd) {
        setUnit(pd.getUnit());
        setFrequency(pd.getFrequency());
        setStarttime(pd.getStarttime());
        setEndtime(pd.getEndtime());
        setFixed(pd.isFixed());
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
