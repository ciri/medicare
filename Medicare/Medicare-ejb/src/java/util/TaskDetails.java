package util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ciri
 */
public class TaskDetails implements Serializable,Comparable {
    private Long id;
    private int dose;
    private String status;
    private Date tasktime;
    private PrescriptionDetails prescription;


    public TaskDetails(Long id, String status, Date tasktime) {
        setId(id);
        setStatus(status);
        setTasktime(tasktime);
    }
    public TaskDetails(String status, Date tasktime) {
        setStatus(status);
        setTasktime(tasktime);
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of dose
     *
     * @return the value of dose
     */
    public int getDose() {
        return dose;
    }

    /**
     * Set the value of dose
     *
     * @param dose new value of dose
     */
    public void setDose(int dose) {
        this.dose = dose;
    }

    /**
     * Get the value of prescription
     *
     * @return the value of prescription
     */
    public PrescriptionDetails getPrescription() {
        return prescription;
    }

    /**
     * Set the value of prescription
     *
     * @param prescription new value of prescription
     */
    public void setPrescription(PrescriptionDetails prescription) {
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

    public int compareTo(Object obj) {
        return this.tasktime.compareTo(((TaskDetails)obj).getTasktime());
    }
}
