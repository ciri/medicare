/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.patient;

import entity.gp.GP;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.PatientDetails;

/**
 *
 * @author ciri
 */
@Stateless
public class PatientFacadeBean implements PatientFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    /************************** FACADE-METHODES *******************************/
    public void createPatient(String name, String password) {
        if (findByName(name) != null)  throw new IllegalArgumentException();
        Patient patient = new Patient(name, password);
        create(patient);
    }
    public PatientDetails getPatient(String name) {
        return convert(findByName(name),true);
    }
    public boolean editPatient(PatientDetails pd) {
        Patient p = findById(pd.getId());
        if(p == null) return false;

        p.setName(pd.getName());
        p.setUsername(pd.getUsername());
        edit(p);
        return true;
    }
    
    /************************** HULPMETHODES *********************************/
    public Patient findById(Long id) {
        return em.find(Patient.class, id);
    }
    public Patient findByName(String name) {
        List<Patient> patients = (List<Patient>) em.createNamedQuery("entity.patient.Patient.findPatientByName").setParameter("name", name).getResultList();
        if (patients.size() == 1) {
            return patients.get(0);
        } else if (patients.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }
    private PatientDetails convert(Patient p, boolean includeGPList) {
        if (p == null)
            return null;
        else {
            List<String> gps = new ArrayList<String>();
            if(includeGPList) {
                for (Object gp : p.getGPs())
                    gps.add(((GP)gp).getName());
            }
            return new PatientDetails(p.getId(),p.getName(),p.getUsername(),gps);
        }
    }
    
    /********************** PERSISTENTIEMETHODES ******************************/
    public void create(Patient patient) {
        em.persist(patient);
    }
    public void edit(Patient patient) {
        em.merge(patient);
        em.flush();
    }
    public void remove(Patient patient) {
        em.remove(em.merge(patient));
    }

}
