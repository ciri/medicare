package entity.patient;

import entity.gp.GP;
import entity.measurement.Measurement;
import entity.medication.Medication;
import entity.prescription.Prescription;
import entity.task.Task;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return convert(findByUsername(username));
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

            //Populate possible secondary level fields
            Medication m = findMedicationByName(pd.getMedication().getName());
            p.setMedication(findMedicationByName(pd.getMedication().getName()));

            List<Task> tasks = new ArrayList<Task>();
            for(TaskDetails td : pd.getTasks())
                tasks.add(new Task(td.getStatus(),td.getTasktime()));
            p.setTasks(tasks);

        pat.getPrescriptions().add(p);
        edit(pat);
        return true;
    }
    public boolean isValid(String username, String password) {
        Patient p = findByUsername(username);
        if (p != null)
            return p.getPassword().equals(password);
        else
            return false;
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
    private PatientDetails convert(Patient p) {
        if (p == null)
            return null;
        else {
            Collection<String> gps = new ArrayList<String>();

            for (Object gp : p.getGps())
                gps.add(((GP)gp).getName());
            
            PatientDetails pd = new PatientDetails(p.getId(),p.getSSN(),p.getUsername());
            pd.setName(p.getName());
            pd.setGps(gps);
            pd.setBirthdate(p.getBirthdate());
            pd.setBloodgroup(p.getBloodgroup());
            pd.setFirstconsult(p.getFirstconsult());
            pd.setLastconsult(p.getLastconsult());
            pd.setMeasurements(
                    convertMeasurements(p.getMeasurements())
            );
            pd.setPrescriptions(
                    convertPrescriptions(p.getPrescriptions())
            );
            pd.setAddress(p.getAddress());
            return pd;
        }
    }
    private List<MeasurementDetails> convertMeasurements(Collection<Measurement> measurements) {
        List<MeasurementDetails> mds = new ArrayList<MeasurementDetails>();
        for(Measurement m : measurements) {
            mds.add(
                new MeasurementDetails(m.getType(),m.getMeasuredvalue())
            );
        }
        return mds;
    }
    private List<PrescriptionDetails> convertPrescriptions(Collection<Prescription> prescriptions) {
        List<PrescriptionDetails> pds = new ArrayList<PrescriptionDetails>();
        for(Prescription p : prescriptions) {
            pds.add(
                new PrescriptionDetails(
                    p.getUnit(),
                    p.getFrequency(),
                    p.getStarttime(),
                    p.getEndtime(),
                    p.isFixed(),
                    convertMedication(
                        p.getMedication()
                    ),
                    convertTasks(
                        p.getTasks()
                    )
                )
            );
        }
        return pds;
    }
    private MedicationDetails convertMedication(Medication m) {
        return new MedicationDetails(
                m.getName(),
                m.getStandarddose(),
                m.getUnitdose(),
                m.getMeasurementrequired()
        );
    }
    private List<TaskDetails> convertTasks(Collection<Task> tasks) {
        List<TaskDetails> tds = new ArrayList<TaskDetails>();
        for(Task t : tasks)
            tds.add(
                new TaskDetails(
                    t.getId(),
                    t.getStatus(),
                    t.getTasktime()
                )
             );
        return tds;
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
