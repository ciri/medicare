package entity.medication;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.MedicationDetails;

/**
 *
 * @author ciri
 */
@Stateless
public class MedicationFacadeBean implements MedicationFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public boolean addMedication(MedicationDetails md) {
        if(findByName(md.getName()) != null)
            return false;
        Medication m = new Medication();
        m.setName(md.getName());
        m.setStandarddose(m.getStandarddose());
        m.setUnitdose(md.getUnitdose());
        m.setMeasurementrequired(md.getMeasurementrequired());
        em.persist(m);
        return true;
    }

    public MedicationDetails getMedication(String name) {
        Medication m = findByName(name);
        if( m != null)
             return convert(m);
        return null;
    }
    public List<MedicationDetails> getAllMedications() {
        List<Medication> medications = (List<Medication>) em.createNamedQuery("entity.medication.Medication.findAllMedications").getResultList();
        List<MedicationDetails> mds = new ArrayList<MedicationDetails>();
        
        for(Medication m : medications)
            mds.add(convert(m));
        return mds;
    }

    public Medication findByName(String name) {
        List<Medication> medications = (List<Medication>) em.createNamedQuery("entity.medication.Medication.findMedicationByName").setParameter("name", name).getResultList();
        if (medications.size() == 1) {
            return medications.get(0);
        } else {
            return null;
        }
    }
    private MedicationDetails convert(Medication m ) {
        return new MedicationDetails(
            m.getName(),
            m.getStandarddose(),
            m.getUnitdose(),
            m.getMeasurementrequired()
        );
    }
    public void persist(Object object) {
        em.persist(object);
    }
}
