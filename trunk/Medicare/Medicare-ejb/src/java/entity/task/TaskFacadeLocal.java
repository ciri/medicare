package entity.task;

import java.util.List;
import javax.ejb.Local;
import util.TaskDetails;

/**
 *
 * @author ciri
 */
@Local
public interface TaskFacadeLocal {
    public boolean updateTask(Long taskid, String newstatus);
    public void addNextTask(Long prescriptionid);
    public void addNextTask(Long prescriptionid,Long previoustaskid);
    public List<TaskDetails> getTaskForPrescription(Long id);
}
