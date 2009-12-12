/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.gp;

import entity.patient.Patient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.GPDetails;
import util.PatientDetails;

/**
 *
 * @author ciri
 */
@Stateless
public class GPFacadeBean implements GPFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    /************************** FACADE-METHODES *******************************/

    public void createGp(String name, String password) {
        if (findByName(name) != null)  throw new IllegalArgumentException();
        GP gp = new GP(name, password);
        create(gp);
    }

    public boolean isValidPassword(String name, String password) {
        GP gp = findByName(name);
        if (gp != null)
            return gp.getPassword().equals(password);
        else
            return false;
    }

    public boolean addPatient(String gp_name, String p_username) {
        GP gp = findByName(gp_name);
        gp.addPatient(findPatientByName(p_username));
        edit(gp);
        return true;
    }
    public GPDetails getGP(String gp_name) {
        return convert(findByName(gp_name),true);
    }
    /************************** HULPMETHODES *********************************/

    public GP findByName(String name) {
        List<GP> gps = (List<GP>) em.createNamedQuery("entity.gp.GP.findGpByName").setParameter("name", name).getResultList();
        if (gps.size() == 1) {
            return gps.get(0);
        } else if (gps.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }
    public Patient findPatientByName(String username) {
        List<Patient> gps = (List<Patient>) em.createNamedQuery("entity.patient.Patient.findPatientByUsername").setParameter("username", username).getResultList();
        if (gps.size() == 1) {
            return gps.get(0);
        } else if (gps.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }

    private GPDetails convert(GP gp, boolean includePatientList) {
        if (gp == null)
            return null;
        else {
            Collection<PatientDetails> patients = new ArrayList<PatientDetails>();

            if(includePatientList) {
                for (Object patient : gp.getPatients()) {
                    Patient p = (Patient) patient;
                    patients.add(new PatientDetails(
                        p.getId(), p.getSSN(),p.getUsername()
                    ));
                }
            }
            return new GPDetails(gp.getId(),gp.getName(), patients);
        }
    }
    /************************** PERSISTENTIEMETHODES *********************************/

    public void create(GP gp) {
        em.persist(gp);
    }
    public void edit(GP gp) {
        em.merge(gp);
    }
    public void remove(GP gp) {
        em.remove(em.merge(gp));
    }
}
