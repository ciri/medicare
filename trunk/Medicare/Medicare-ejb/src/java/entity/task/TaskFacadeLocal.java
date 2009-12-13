package entity.task;

import javax.ejb.Local;

/**
 *
 * @author ciri
 */
@Local
public interface TaskFacadeLocal {
    public boolean updateTask(Long taskid, String newstatus);
}
