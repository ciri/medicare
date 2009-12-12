/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.patient;

import entity.patient.PatientFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Kristof
 */
@Stateless
public class PatientBean implements PatientRemote {

    @EJB
    private PatientFacadeLocal patientFacadeBean;

    public boolean addPatient(String username, String password) {
        try {
            patientFacadeBean.createPatient(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}



