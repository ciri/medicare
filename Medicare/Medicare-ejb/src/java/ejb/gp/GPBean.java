package ejb.gp;

import entity.gp.GPFacadeLocal;
import entity.measurement.MeasurementFacadeLocal;
import entity.medication.MedicationFacadeLocal;
import entity.patient.PatientFacadeLocal;
import entity.prescription.PrescriptionFacadeLocal;
import entity.task.TaskFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import util.GPDetails;
import util.MeasurementDetails;
import util.MedicationDetails;
import util.PatientDetails;
import util.PrescriptionDetails;
import util.TaskDetails;

@Stateless
public class GPBean implements GPRemote {

    @EJB
    private GPFacadeLocal gpFacadeBean;

    @EJB
    private PatientFacadeLocal patientFacadeBean;

    @EJB
    private MeasurementFacadeLocal measurementFacadeBean;

    @EJB
    private MedicationFacadeLocal medicationFacadeBean;

    @EJB
    private PrescriptionFacadeLocal prescriptionFacadeBean;

    @EJB
    private TaskFacadeLocal taskFacadeBean;

    public boolean addGP(String username, String password) {
        try {
            gpFacadeBean.createGp(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean addPatient(String gp_name, String p_name) {
        try {
             gpFacadeBean.addPatient(gp_name, p_name);
             return true;
         } catch (Exception e) {
             return false;
         }
    }
    public GPDetails getGPDetails(String gp_name) {
        return  gpFacadeBean.getGP(gp_name);
    }
    public PatientDetails getPatientDetails(String patient_name) {
        return patientFacadeBean.getPatient(patient_name);        
    }
    public boolean setPatientInformation(PatientDetails patient) {
        return patientFacadeBean.editPatient(patient);
    }
    public boolean createPatient(String gp_username, String p_SSN, String p_username, String p_password) {
        try {
            patientFacadeBean.createPatient(p_SSN, p_username, p_password);
        } catch(Exception e) {
            return false;
        }
        return gpFacadeBean.addPatient(gp_username, p_username);
    }
    public List<PatientDetails> getAllPatientDetails() {
        return patientFacadeBean.getAllPatients();
    }
    public boolean addMeasurement(String patient_name, MeasurementDetails md) {
        patientFacadeBean.addMeasurement(patient_name,md);
        return true;
    }

    public List<String> getMeasurementTypes(String p_username) {
       return measurementFacadeBean.getMeasurementTypes();
    }
    public boolean addMedication(MedicationDetails md) {
        return medicationFacadeBean.addMedication(md);
    }
    public List<MedicationDetails> getAllMedications() {
        return medicationFacadeBean.getAllMedications();
    }
    public MedicationDetails getMedication(String m) {
        return medicationFacadeBean.getMedication(m);
    }
    public boolean addPrescription(String p_username, PrescriptionDetails pd) {
        //TODO: remove
        //return patientFacadeBean.addPrescription(p_username,pd);
        Long id = prescriptionFacadeBean.addPrescription(p_username,pd);
        taskFacadeBean.addNextTask(id);
        return true;
    }

    public List<PrescriptionDetails> getPrescriptions(String p_username) {
        return prescriptionFacadeBean.getPrescriptions(p_username);
    }

    public List<TaskDetails> getTasks(String p_username) {
        return taskFacadeBean.getTasks(p_username);
    }
}



