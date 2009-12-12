package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PatientDetails implements Serializable {

    //Required
    private Long id;
    private String SSN;
    private String username;

    //Optional
    private String name;
    private String bloodgroup;
    private Date birthday;
    private AddressDetails address;
    private Date lastconsult;
    private Date firstconsult;
    private Collection<String> gps;
    private Collection<TaskDetails> tasks;
    private Collection<MeasurementDetails> measurements;
    private Collection<PrescriptionDetails> prescriptions;

    public PatientDetails(){};
    
    public PatientDetails(Long id, String SSN,String username) {
        this.id = id;
        this.SSN = SSN;
        this.username = username;
    }

    /**
     * Get the value of prescriptions
     *
     * @return the value of prescriptions
     */
    public Collection<PrescriptionDetails> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Set the value of prescriptions
     *
     * @param prescriptions new value of prescriptions
     */
    public void setPrescriptions(Collection<PrescriptionDetails> prescriptions) {
        this.prescriptions = prescriptions;
    }

    /**
     * Get the value of measurements
     *
     * @return the value of measurements
     */
    public Collection<MeasurementDetails> getMeasurements() {
        return measurements;
    }

    /**
     * Set the value of measurements
     *
     * @param measurements new value of measurements
     */
    public void setMeasurements(Collection<MeasurementDetails> measurements) {
        this.measurements = measurements;
    }

    /**
     * Get the value of TaskDetails
     *
     * @return the value of TaskDetails
     */
    public Collection<TaskDetails> getTasks() {
        return tasks;
    }

    /**
     * Set the value of TaskDetails
     *
     * @param TaskDetails new value of TaskDetails
     */
    public void setTasks(Collection<TaskDetails> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get the value of firstconsult
     *
     * @return the value of firstconsult
     */
    public Date getFirstconsult() {
        return firstconsult;
    }

    /**
     * Set the value of firstconsult
     *
     * @param firstconsult new value of firstconsult
     */
    public void setFirstconsult(Date firstconsult) {
        this.firstconsult = firstconsult;
    }

    /**
     * Get the value of lastconsult
     *
     * @return the value of lastconsult
     */
    public Date getLastconsult() {
        return lastconsult;
    }

    /**
     * Set the value of lastconsult
     *
     * @param lastconsult new value of lastconsult
     */
    public void setLastconsult(Date lastconsult) {
        this.lastconsult = lastconsult;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public AddressDetails getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(AddressDetails address) {
        this.address = address;
    }

    /**
     * Get the value of birthday
     *
     * @return the value of birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set the value of birthday
     *
     * @param birthday new value of birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Get the value of bloodgroup
     *
     * @return the value of bloodgroup
     */
    public String getBloodgroup() {
        return bloodgroup;
    }

    /**
     * Set the value of bloodgroup
     *
     * @param bloodgroup new value of bloodgroup
     */
    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public Collection<String> getGps() {
        return gps;
    }
    public void setGps(Collection gps) {
        this.gps = gps;
    }
    public String getSSN() {
        return SSN;
    }
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //EXTRA
    public List<MeasurementDetails> getMeasurementsOfType(String type) {
        List<MeasurementDetails> mds = new ArrayList<MeasurementDetails>();
        for(MeasurementDetails md : this.measurements) {
            if(md.getType().equals(type))
                mds.add(md);
        }
        return mds;
    }
}
