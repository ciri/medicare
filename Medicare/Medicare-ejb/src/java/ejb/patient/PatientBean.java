/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.patient;

import entity.measurement.MeasurementFacadeLocal;
import entity.patient.PatientFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import util.MeasurementDetails;

/**
 *
 * @author Kristof
 */
@Stateless
public class PatientBean implements PatientRemote {

    @EJB
    private PatientFacadeLocal patientFacadeBean;

    @EJB
    private MeasurementFacadeLocal measurementFacadeBean;
    
    public boolean addPatient(String SSN, String username, String password) {
        try {
            patientFacadeBean.createPatient(SSN, username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addMeasurement(String username, String password, MeasurementDetails md) {
        if(patientFacadeBean.isValid(username, password)) {
            patientFacadeBean.addMeasurement(username, md);
            return true;
        }
        else
            return false;
    }
}



