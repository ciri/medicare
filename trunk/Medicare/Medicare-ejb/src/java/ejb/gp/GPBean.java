package ejb.gp;

import entity.gp.GPFacadeLocal;
import entity.patient.PatientFacadeLocal;
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
}



