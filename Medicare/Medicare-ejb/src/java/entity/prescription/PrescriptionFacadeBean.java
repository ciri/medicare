package entity.prescription;

import entity.medication.Medication;
import entity.patient.Patient;
import entity.task.Task;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.MedicationDetails;
import util.PrescriptionDetails;
import util.TaskDetails;

/**
 *
 * @author ciri
 */
@Stateless
public class PrescriptionFacadeBean implements PrescriptionFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public List<PrescriptionDetails> getPrescriptions(String p_username) {
        Patient p = findPatientByUsername(p_username);
        if(p == null) return null;
        
        Long pid = p.getId();
        List<Prescription> prescriptions =  (List<Prescription>) em.createNamedQuery("entity.prescription.Prescription.findPrescriptionByPatientid").setParameter("patientid", pid).getResultList();

        List<PrescriptionDetails> prescriptiondetails = new ArrayList<PrescriptionDetails>();
        for(Prescription pres : prescriptions) {
            prescriptiondetails.add( convert( pres) );
        }
        return prescriptiondetails;
    }
    public Long addPrescription(String p_username, PrescriptionDetails pd) {
        Prescription p = new Prescription();
        p.setStarttime(pd.getStarttime());
        p.setEndtime(pd.getEndtime());
        p.setFixed(pd.isFixed());
        p.setFrequency(pd.getFrequency());
        p.setMedication(findMedicationByName(pd.getMedication().getName()));
        p.setPatient(findPatientByUsername(p_username));
        persist(p);

        return p.getId();
    }
    private Medication findMedicationByName(String name) {
        List<Medication> medications = (List<Medication>) em.createNamedQuery("entity.medication.Medication.findMedicationByName").setParameter("name", name).getResultList();
        if (medications.size() == 1) {
            return medications.get(0);
        } else if (medications.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }
    private Patient findPatientByUsername(String username) {
        List<Patient> patients = (List<Patient>) em.createNamedQuery("entity.patient.Patient.findPatientByUsername").setParameter("username", username).getResultList();
        if (patients.size() == 1) {
            return patients.get(0);
        } else if (patients.size() > 1) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }
    private PrescriptionDetails convert(Prescription p) {
        PrescriptionDetails pd = new PrescriptionDetails();
        pd.setEndtime(p.getEndtime());
        pd.setStarttime(p.getStarttime());
        pd.setFixed(p.isFixed());
        pd.setFrequency(p.getFrequency());
        pd.setId(p.getId());
        pd.setUnit(p.getUnit());
        pd.setMedication( convertMedication(p.getMedication()) );
        pd.setTasks( convertTasks(p.getTasks()));
        return pd;
    }

    private MedicationDetails convertMedication(Medication m) {
        return new MedicationDetails(
                m.getName(),
                m.getStandarddose(),
                m.getUnitdose()
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
    public void persist(Object object) {
        em.persist(object);
    }
}
