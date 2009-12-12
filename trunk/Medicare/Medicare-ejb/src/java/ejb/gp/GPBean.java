package ejb.gp;

import entity.gp.GPFacadeLocal;
import entity.patient.PatientFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import util.GPDetails;
import util.PatientDetails;

@Stateless
public class GPBean implements GPRemote {

    @EJB
    private GPFacadeLocal gpFacadeBean;

    @EJB
    private PatientFacadeLocal patientFacadeBean;

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
}



