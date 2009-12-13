package util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ciri
 */
public class MeasurementDetails implements Serializable {

    private String type;
    private float measuredvalue;
    private Date measurementtime;

    public MeasurementDetails() {}
    public MeasurementDetails(String type, float measuredvalue) {
        this(type,measuredvalue,new Date());
    }
    public MeasurementDetails(String type, String measuredvalue) {
        this(type,Float.parseFloat(measuredvalue),new Date());
    }
    public MeasurementDetails( String type, float measuredvalue, Date measurementtime) {
        setType(type);
        setMeasuredvalue(measuredvalue);
        setMeasurementtime(measurementtime);
    }
    /**
     * Get the value of measurementtime
     *
     * @return the value of measurementtime
     */
    public Date getMeasurementtime() {
        return measurementtime;
    }

    /**
     * Set the value of measurementtime
     *
     * @param measurementtime new value of measurementtime
     */
    public void setMeasurementtime(Date measurementtime) {
        this.measurementtime = measurementtime;
    }

    /**
     * Get the value of measuredvalue
     *
     * @return the value of measuredvalue
     */
    public float getMeasuredvalue() {
        return measuredvalue;
    }

    /**
     * Set the value of measuredvalue
     *
     * @param measuredvalue new value of measuredvalue
     */
    public void setMeasuredvalue(float measuredvalue) {
        this.measuredvalue = measuredvalue;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }
}
