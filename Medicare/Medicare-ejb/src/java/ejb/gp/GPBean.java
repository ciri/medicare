package ejb.gp;

import entity.gp.GPFacadeLocal;
import entity.measurement.MeasurementFacadeLocal;
import entity.medication.MedicationFacadeLocal;
import entity.patient.PatientFacadeLocal;
import entity.prescription.PrescriptionFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import util.GPDetails;
import util.MeasurementDetails;
import util.MedicationDetails;
import util.PatientDetails;
import util.PrescriptionDetails;

@Stateless
public class GPBean implements GPRemote {

    @EJB
    private GPFacadeLocal gpFacadeBean;

    @EJB
    private PatientFacadeLocal patientFacadeBean;

    @EJB
    private MeasurementFacadeLocal measurementFacade;

    @EJB
    private MedicationFacadeLocal medicationFacade;

    public boolean addGP(String username, String password) {
        try {
            gpFacadeBean.createGp(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean addPatient(String gp_name, String gp_password,String p_name) {
        if (gpFacadeBean.isValidPassword(gp_name, gp_password)) {
            try {
                gpFacadeBean.addPatient(gp_name, p_name);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
    public GPDetails getGPDetails(String gp_name, String gp_password) {
        if(gpFacadeBean.isValidPassword(gp_name,gp_password))
            return  gpFacadeBean.getGP(gp_name);
        else
            return null;
    }
    public PatientDetails getPatientDetails(String gp_name, String gp_password, String patient_name) {
        if(gpFacadeBean.isValidPassword(gp_name,gp_password))
            return patientFacadeBean.getPatient(patient_name);
        else
            return null;
    }
    public boolean setPatientInformation(String gp_name, String gp_password, PatientDetails patient) {
        if(gpFacadeBean.isValidPassword(gp_name,gp_password))
            return patientFacadeBean.editPatient(patient);
        else
            return false;
    }
    public boolean createPatient(String gp_username, String gp_password, String p_SSN, String p_username, String p_password) {
        if(gpFacadeBean.isValidPassword(gp_username, gp_password)) {
            try {
                patientFacadeBean.createPatient(p_SSN, p_username, p_password);
            } catch(Exception e) {
                return false;
            }
            return gpFacadeBean.addPatient(gp_username, p_username);
        }
        return false;
    }
    public List<PatientDetails> getAllPatientDetails(String gp_name, String gp_password) {
        if(gpFacadeBean.isValidPassword(gp_name, gp_password))
            return patientFacadeBean.getAllPatients();
        else
            return null;
    }

    public boolean addMeasurement(String gp_name, String gp_password, String patient_name, MeasurementDetails md) {
        if(gpFacadeBean.isValidPassword(gp_name, gp_password)) {
            patientFacadeBean.addMeasurement(patient_name,md);
            return true;
        }
        return false;
    }

    public List<String> getMeasurementTypes(String gp_name, String gp_password, String p_username) {
        if(gpFacadeBean.isValidPassword(gp_name, gp_password))
            return measurementFacade.getMeasurementTypes();
        else
            return null;
    }

    public boolean addMedication(String gp_name, String gp_password, MedicationDetails md) {
        if(gpFacadeBean.isValidPassword(gp_name, gp_password))
            return medicationFacade.addMedication(md);
        else
            return false;
    }
    public List<MedicationDetails> getAllMedications(String gp_name, String gp_password) {
        if(gpFacadeBean.isValidPassword(gp_name, gp_password))
            return medicationFacade.getAllMedications();
        else
            return null;
    }
    public MedicationDetails getMedication(String gp_name, String gp_password, String m) {
        if(gpFacadeBean.isValidPassword(gp_name, gp_password))
            return medicationFacade.getMedication(m);
        else
            return null;
    }

    public boolean addPrescription(String gp_name, String gp_password, String p_username, PrescriptionDetails pd) {
        if(gpFacadeBean.isValidPassword(gp_name, gp_password))
            return patientFacadeBean.addPrescription(p_username,pd);
        else
            return false;
    }

}



