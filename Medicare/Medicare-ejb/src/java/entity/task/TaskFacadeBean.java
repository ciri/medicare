package entity.task;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ciri
 */
@Stateless
public class TaskFacadeBean implements TaskFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public boolean updateTask(Long taskid, String newstatus) {
        Task t = findById(taskid);
        if(t == null) return false;
        
        t.setStatus(newstatus);
        persist(t);
        return true;
    }
    public Task findById(Long id) {
        return em.find(Task.class, id);
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
