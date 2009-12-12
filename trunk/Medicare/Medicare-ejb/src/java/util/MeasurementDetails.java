package util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ciri
 */
public class MeasurementDetails implements Serializable {

    private String name;
    private String type;
    private float measuredvalue;
    private Date measurementtime;

    public MeasurementDetails() {}
    public MeasurementDetails(String name, String type, float measuredvalue) {
        this(name,type,measuredvalue,new Date());
    }
    public MeasurementDetails(String name, String type, String measuredvalue) {
        this(name,type,Float.parseFloat(measuredvalue),new Date());
    }
    public MeasurementDetails(String name, String type, float measuredvalue, Date measurementtime) {
        setName(name);
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

}
