package util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ciri
 */
public class PrescriptionDetails implements Serializable {

    private int unit;
    private int frequency;
    private Date starttime;
    private Date endtime;
    private boolean fixed;
    private MedicationDetails medication;

    public PrescriptionDetails() {}
    public PrescriptionDetails(String unit, String frequency, String starttime, String endtime,String fixed, String medication ) {
        this(
                Integer.parseInt(unit),
                Integer.parseInt(frequency),
                new Date(),
                new Date(),
                Boolean.parseBoolean(fixed),
                new MedicationDetails(medication,0,0)
        );
    }
    public PrescriptionDetails(int unit, int frequency, Date starttime, Date endtime, boolean fixed, MedicationDetails medication) {
        setUnit(unit);
        setFrequency(frequency);
        setStarttime(starttime);
        setEndtime(endtime);
        setFixed(fixed);
        setMedication(medication);
    }
    /**
     * Get the value of medication
     *
     * @return the value of medication
     */
    public MedicationDetails getMedication() {
        return medication;
    }

    /**
     * Set the value of medication
     *
     * @param medication new value of medication
     */
    public void setMedication(MedicationDetails medication) {
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

}
