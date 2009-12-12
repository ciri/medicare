package entity.measurement;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ciri
 */
@Stateless
public class MeasurementFacadeBean implements MeasurementFacadeLocal {
    @PersistenceContext
    private EntityManager em;
    public List<String> getMeasurementTypes() {
        List<String> types = (List<String>) em.createNamedQuery("entity.measurement.Measurement.findTypes").getResultList();
        return types;
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
