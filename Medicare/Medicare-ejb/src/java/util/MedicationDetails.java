package util;

import java.io.Serializable;

public class MedicationDetails implements Serializable {

    private String name;
    private double standarddose;
    private String unitdose;
    private String measurementrequired;

    public MedicationDetails(String name, String standarddose, String unitdose,String measurementrequired) {
        this(name,Double.parseDouble(standarddose),unitdose,measurementrequired);
    }
    public MedicationDetails(String name, String standarddose, String unitdose) {
        this(name,Double.parseDouble(standarddose),unitdose,"");
    }
    public MedicationDetails(String name, double standarddose, String unitdose,String measurementrequired) {
        setName(name);
        setStandarddose(standarddose);
        setUnitdose(unitdose);
        setMeasurementrequired(measurementrequired);
    }

    /**
     * Get the value of measurementrequired
     *
     * @return the value of measurementrequired
     */
    public String getMeasurementrequired() {
        return measurementrequired;
    }

    /**
     * Set the value of measurementrequired
     *
     * @param measurementrequired new value of measurementrequired
     */
    public void setMeasurementrequired(String measurementrequired) {
        this.measurementrequired = measurementrequired;
    }

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

}
