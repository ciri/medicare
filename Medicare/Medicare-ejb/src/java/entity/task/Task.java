/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.task;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author ciri
 */
@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String status;

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tasktime;

    public Task() {}
    public Task(String status, Date tasktime) {
        setStatus(status);
        setTasktime(tasktime);
    }
    /**
     * Get the value of tasktime
     *
     * @return the value of tasktime
     */
    public Date getTasktime() {
        return tasktime;
    }

    /**
     * Set the value of tasktime
     *
     * @param tasktime new value of tasktime
     */
    public void setTasktime(Date tasktime) {
        this.tasktime = tasktime;
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.task.Task[id=" + id + "]";
    }

}
