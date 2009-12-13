package ejb.patient;

import entities.DoseCheckerBeanService;
import entity.patient.PatientFacadeLocal;
import entity.prescription.PrescriptionFacadeLocal;
import entity.task.TaskFacadeLocal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;
import util.MeasurementDetails;
import util.MedicationDetails;
import util.PatientDetails;
import util.PrescriptionDetails;
import util.TaskDetails;

/**
 *
 * @author ciri
 */
@Stateless
public class PatientBean implements PatientRemote {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/chaos169.test.atlantis.ugent.be_8080/DoseCheckerBeanService/DoseCheckerBean.wsdl")
    private DoseCheckerBeanService service;

    @EJB
    private PatientFacadeLocal patientFacadeBean;

    @EJB
    private TaskFacadeLocal taskFacadeBean;

    @EJB
    private PrescriptionFacadeLocal prescriptionFacadeBean;
    
    public TaskDetails getTask(String username) {
        List<TaskDetails> task_aggregate = new ArrayList<TaskDetails>();
        
        List<PrescriptionDetails> prescriptions = prescriptionFacadeBean.getPrescriptions(username);
        System.out.println("Fetched "+prescriptions.size()+" prescriptions.");
        for(PrescriptionDetails prescription : prescriptions) {
            List<TaskDetails> tasks = taskFacadeBean.getTaskForPrescription(prescription.getId());
            for(TaskDetails task : tasks) {
                if(task.getStatus() != null && task.getStatus().equals("in progress")) {
                    task.setPrescription(prescription);
                    task_aggregate.add(task);
                }
            }
        }
        if(!task_aggregate.isEmpty()) {
            Collections.sort(task_aggregate);
            TaskDetails curr_task = task_aggregate.get(0);

            //If is fixed, take the standard dose, else take the webservice
            if(curr_task.getPrescription().isFixed())
                curr_task.setDose(curr_task.getPrescription().getMedication().getStandarddose());
            else {
                int dose=12345;
                try {
                    entities.DoseCheckerBean port = service.getDoseCheckerBeanPort();
                    java.lang.String measurementType = "gewicht";
                    double measurementValue = 115.0d;
                    java.lang.String medication = "dieetpil";
                    double standardDose = 2.0d;
                    double result = port.getVariableDoseForName(measurementType, measurementValue, medication, standardDose);
                    dose = (int)Math.round(result);
                } catch (Exception ex) {
                    System.out.println("Error while trying to get the variable dosage ...");
                    ex.printStackTrace();
                    dose = 31337;
                }
                curr_task.setDose(dose);
            }
            return curr_task;
        }
        else
            return null;
/*        List<TaskDetails> task_aggregate = new ArrayList<TaskDetails>();
            
        PatientDetails pd = patientFacadeBean.getPatient(username);

        Collection<PrescriptionDetails> prescriptions = pd.getPrescriptions();
        if(prescriptions != null)
        for(PrescriptionDetails prescription :  prescriptions) {
            Collection<TaskDetails> tasks = prescription.getTasks();
            if(tasks != null)
            for(TaskDetails task : tasks) {
                if(task.getStatus() != null && task.getStatus().equals("in progress")) {
                    task.setPrescription(prescription);
                    task_aggregate.add(task);
                }
             }
        }
        if(!task_aggregate.isEmpty()) {
            Collections.sort(task_aggregate);
            TaskDetails curr_task = task_aggregate.get(0);

            //If is fixed, take the standard dose, else take the webservice
            if(curr_task.getPrescription().isFixed())
                curr_task.setDose(curr_task.getPrescription().getMedication().getStandarddose());
            else {
                int dose=12345;
                try {
                    entities.DoseCheckerBean port = service.getDoseCheckerBeanPort();
                    java.lang.String measurementType = "gewicht";
                    double measurementValue = 115.0d;
                    java.lang.String medication = "dieetpil";
                    double standardDose = 2.0d;
                    double result = port.getVariableDoseForName(measurementType, measurementValue, medication, standardDose);
                    dose = (int)Math.round(result);
                } catch (Exception ex) { 
                    System.out.println("Error while trying to get the variable dosage ...");
                    ex.printStackTrace();
                    dose = 31337; 
                }
                curr_task.setDose(dose);
            }
            return curr_task;
        }
        else
            return null;*/
    }
    public boolean updateTask( String prescriptionid, String taskid, String newstatus ) {
        //Update the old task
        if(taskFacadeBean.updateTask(Long.parseLong(taskid),newstatus)) {
            //Add a new one
            taskFacadeBean.addNextTask(Long.parseLong(prescriptionid), Long.parseLong(taskid));
            return true;
        }
        return false;
    }
    public PatientDetails getPatientDetails( String username ) {
        return patientFacadeBean.getPatient(username);
    }
    public boolean addMeasurement(String username,  MeasurementDetails md) {
         patientFacadeBean.addMeasurement(username, md);
         return true;
    }

    public List<PrescriptionDetails> getPrescriptions(String username) {
        return prescriptionFacadeBean.getPrescriptions(username);
    }

    public List<String> getMedications(String username) {
        List<PrescriptionDetails> prescriptions = getPrescriptions(username);
        List<String> medications  = new ArrayList<String>();

        for(PrescriptionDetails prescription : prescriptions) {
            medications.add(prescription.getMedication().getName());
        }
        return medications;
    }
}