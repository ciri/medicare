package entity.patient;

import entity.gp.GP;
import entity.measurement.Measurement;
import entity.medication.Medication;
import entity.prescription.Prescription;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.MeasurementDetails;
import util.PatientDetails;
import util.PrescriptionDetails;

/**
 *
 * @author ciri
 */
@Stateless
public class PatientFacadeBean implements PatientFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    /************************** FACADE-METHODES *******************************/
    public boolean createPatient(String SSN, String username,String password) {
        if (findByUsername(username) != null || findBySSN(SSN) != null)
            return false;
        Patient patient = new Patient(SSN,username,password);
        create(patient);
        return true;
    }
    public PatientDetails getPatient(String username) {
        return convert(findByUsername(username),true);
    }
    public boolean editPatient(PatientDetails pd) {
        Patient p = findById(pd.getId());
        if(p == null) return false;

        p.setName(pd.getName());
        p.setUsername(pd.getUsername());
        edit(p);
        return true;
    }
    public List<PatientDetails> getAllPatients() {
        List<Patient> patients = (List<Patient>) em.createNamedQuery("entity.patient.Patient.findAllPatients").getResultList();
        List<PatientDetails> ps = new ArrayList<PatientDetails>();
        for(Patient p : patients) {
            ps.add(new PatientDetails(p.getId(),p.getSSN(),p.getUsername()));
        }
        return ps;
    }

    public void addMeasurement(String username, MeasurementDetails md) {
        Patient p = findByUsername(username);
        Measurement m = new Measurement(md);
        p.getMeasurements().add(m);
        edit(p);
    }
    public boolean addPrescription(String p_username, PrescriptionDetails pd) {
        Patient pat = findByUsername(p_username);
        Prescription p = new Prescription(pd);
                     p.setMedication(findMedicationByName(pd.getMedication().getName()));

        pat.getPrescriptions().add(p);
        edit(pat);
        return true;
    }

    /************************** HULPMETHODES *********************************/
    public Patient findById(Long id) {
        return em.find(Patient.class, id);
    }
    public Patient findByUsername(String username) {
        List<Patient> patients = (List<Patient>) em.createNamedQuery("entity.patient.Patient.findPatientByUsername").setParameter("username", username).getResultList();
        if (patients.size() == 1) {
            return patients.get(0);
        } else if (patients.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }
    public Patient findBySSN(String SSN) {
        List<Patient> patients = (List<Patient>) em.createNamedQuery("entity.patient.Patient.findPatientBySSN").setParameter("SSN", SSN).getResultList();
        if (patients.size() == 1) {
            return patients.get(0);
        } else if (patients.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }

    public Medication findMedicationByName(String name) {
        List<Medication> medications = (List<Medication>) em.createNamedQuery("entity.medication.Medication.findMedicationByName").setParameter("name", name).getResultList();
        if (medications.size() == 1) {
            return medications.get(0);
        } else if (medications.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }
    private PatientDetails convert(Patient p, boolean includeGPList) {
        if (p == null)
            return null;
        else {
            Collection<String> gps = new ArrayList<String>();
            if(includeGPList) {
                for (Object gp : p.getGps())
                    gps.add(((GP)gp).getName());
            }
            PatientDetails pd = new PatientDetails(p.getId(),p.getSSN(),p.getUsername());
            pd.setName(p.getName());
            pd.setGps(p.getGps());
            pd.setBirthday(p.getBirthday());
            pd.setBloodgroup(p.getBloodgroup());
            pd.setFirstconsult(pd.getFirstconsult());
            pd.setLastconsult(p.getLastconsult());
            pd.setMeasurements(
                    convertMeasurements(p.getMeasurements())
            );
            //pd.setTasks(p.getTasks());
            //pd.setAddress(p.getAddress());
            return pd;
        }
    }
    private List<MeasurementDetails> convertMeasurements(Collection<Measurement> measurements) {
        List<MeasurementDetails> mds = new ArrayList<MeasurementDetails>();
        for(Measurement m : measurements) {
            mds.add(
                new MeasurementDetails(m.getName(),m.getType(),m.getMeasuredvalue())
            );
        }
        return mds;
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
