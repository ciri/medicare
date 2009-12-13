package entity.measurement;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import util.MeasurementDetails;

/**
 *
 * @author ciri
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "entity.measurement.Measurement.findTypes",
                query = "SELECT DISTINCT m.type FROM Measurement m")
})
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    @Column
    private String type;
    @Column
    private float measuredvalue;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date measurementtime;

    public Measurement() {}

    public Measurement(MeasurementDetails md) {
        setName(md.getName());
        setType(md.getType());
        setMeasuredvalue(md.getMeasuredvalue());
        setMeasurementtime(md.getMeasurementtime());
    }
    /**
     * Get the measuredvalue of measurementtime
     *
     * @return the measuredvalue of measurementtime
     */
    public Date getMeasurementtime() {
        return measurementtime;
    }

    /**
     * Set the measuredvalue of measurementtime
     *
     * @param measurementtime new measuredvalue of measurementtime
     */
    public void setMeasurementtime(Date time) {
        this.measurementtime = time;
    }

    /**
     * Get the measuredvalue of measuredvalue
     *
     * @return the measuredvalue of measuredvalue
     */
    public float getMeasuredvalue() {
        return measuredvalue;
    }

    /**
     * Set the measuredvalue of measuredvalue
     *
     * @param measuredvalue new measuredvalue of measuredvalue
     */
    public void setMeasuredvalue(float value) {
        this.measuredvalue = value;
    }

    /**
     * Get the measuredvalue of type
     *
     * @return the measuredvalue of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the measuredvalue of type
     *
     * @param type new measuredvalue of type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the measuredvalue of name
     *
     * @return the measuredvalue of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the measuredvalue of name
     *
     * @param name new measuredvalue of name
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
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.measurement.Measurement[id=" + id + "]";
    }

}
