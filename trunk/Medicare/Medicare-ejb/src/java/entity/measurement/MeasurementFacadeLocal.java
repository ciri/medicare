/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.measurement;

import java.util.List;
import javax.ejb.Local;

@Local
public interface MeasurementFacadeLocal {

    public List<String> getMeasurementTypes();
    
}
