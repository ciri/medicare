package util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author ciri
 */
public class PrescriptionDetails implements Serializable {
    private Long id;
    private int unit;
    private int frequency;
    private Date starttime;
    private Date endtime;
    private boolean fixed;
    private MedicationDetails medication;
    protected List<TaskDetails> tasks;

    public PrescriptionDetails() {}
    public PrescriptionDetails(String unit, String frequency, String starttime, String endtime,String fixed, String medication ) {
        Date stime = new Date();
        Date etime = new Date();
        try {
            DateFormat formatter ;
            formatter = new SimpleDateFormat("dd-MM-yy");
            
            stime = (Date)formatter.parse(starttime);
            etime = (Date)formatter.parse(endtime);
        } catch (ParseException e) {
              System.out.println("Exception :"+e);
        }

         setUnit(Integer.parseInt(unit));
         setFrequency(Integer.parseInt(frequency));
         setStarttime(stime);
         setEndtime(etime);
         setFixed(Boolean.parseBoolean(fixed));
         setMedication(new MedicationDetails(medication,"0","milligram"));
         addTasks();
    }
    public PrescriptionDetails(int unit, int frequency, Date starttime, Date endtime, boolean fixed, MedicationDetails medication,List<TaskDetails> taskDetails) {
        setUnit(unit);
        setFrequency(frequency);
        setStarttime(starttime);
        setEndtime(endtime);
        setFixed(fixed);
        setMedication(medication);
        setTasks(taskDetails);
    }
    private void addTasks() {
        tasks = new ArrayList<TaskDetails>();
      //  tasks.add(new TaskDetails("in progress",starttime));

        /*Calendar c = new GregorianCalendar();
        c.setTime(starttime);
        int start_year = c.get(Calendar.YEAR);
        int start_day  = c.get(Calendar.DAY_OF_YEAR);

        c.setTime(endtime);
        int end_year = c.get(Calendar.YEAR);
        int end_day  = c.get(Calendar.DAY_OF_YEAR);

        c.setTime(starttime);
        if(frequency != 0) {
          for(int y = start_year  ; y <= end_year ; y++ ) {
             for(int d = start_day; d <= end_day  ; d++ ) {
                 c.set(Calendar.YEAR, y);
                 c.set(Calendar.DAY_OF_YEAR, d);
                 for(int h = 0; h < 24; h+= 24/frequency) {
                     c.set(Calendar.HOUR_OF_DAY, h);
                     Date medication_date = c.getTime();
                     TaskDetails t = new TaskDetails("in progress",medication_date);
                     tasks.add(t);
                 }
             }
           }
         }*/
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
     * Get the value of tasks
     *
     * @return the value of tasks
     */
    public List<TaskDetails> getTasks() {
        return tasks;
    }

    /**
     * Set the value of tasks
     *
     * @param tasks new value of tasks
     */
    public void setTasks(List<TaskDetails> tasks) {
        this.tasks = tasks;
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
