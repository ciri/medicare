package entity.medication;

import java.util.List;
import javax.ejb.Local;
import util.MedicationDetails;

/**
 *
 * @author ciri
 */
@Local
public interface MedicationFacadeLocal {

    public boolean addMedication(MedicationDetails md);

    public List<MedicationDetails> getAllMedications();

    public MedicationDetails getMedication(String m);
    
}
