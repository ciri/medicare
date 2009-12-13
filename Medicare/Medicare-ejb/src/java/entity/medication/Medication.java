package entity.medication;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ciri
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "entity.medication.Medication.findMedicationByName",
                query = "SELECT m FROM Medication m WHERE m.name = :name"),
    @NamedQuery(name = "entity.medication.Medication.findAllMedications",
                query = "SELECT m FROM Medication m")
})
public class Medication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private double standarddose;
    @Column
    private String unitdose;

    /**
     * Get the value of unitdose
     *
     * @return the value of unitdose
     */
    public String getUnitdose() {
        return unitdose;
    }

    /**
     * Set the value of unitdose
     *
     * @param unitdose new value of unitdose
     */
    public void setUnitdose(String unitdose) {
        this.unitdose = unitdose;
    }

    /**
     * Get the value of standarddose
     *
     * @return the value of standarddose
     */
    public double getStandarddose() {
        return standarddose;
    }

    /**
     * Set the value of standarddose
     *
     * @param standarddose new value of standarddose
     */
    public void setStandarddose(double standarddose) {
        this.standarddose = standarddose;
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
        if (!(object instanceof Medication)) {
            return false;
        }
        Medication other = (Medication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.medication.Medication[id=" + id + "]";
    }

}
