package entity.prescription;

import java.util.List;
import javax.ejb.Local;
import util.PrescriptionDetails;

/**
 *
 * @author ciri
 */
@Local
public interface PrescriptionFacadeLocal {
    public Long addPrescription(String p_username, PrescriptionDetails pd);
    public List<PrescriptionDetails> getPrescriptions(String p_username);
}
