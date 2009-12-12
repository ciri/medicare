/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.gp;

import javax.ejb.Local;
import util.GPDetails;

/**
 *
 * @author ciri
 */
@Local
public interface GPFacadeLocal {
    public void createGp(String username, String password);
    public boolean isValidPassword(String gp_name, String gp_password);

    /**
     * Adds a patient to the GP's patient list and adds  the GP to the patient's 
     * GP list
     * 
     * @param gp_name Name of the GP
     * @param p_name  Name of the patient
     * @return GPDetails object
     */
    public boolean addPatient(String gp_name, String p_username);

    /**
     * Returns a representation of the gp with name gp_name
     * 
     * @param gp_name Name of the GP
     * @return GPDetails object
     */
    public GPDetails getGP(String gp_name);
}
