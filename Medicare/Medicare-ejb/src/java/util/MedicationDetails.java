package util;

import java.io.Serializable;

public class MedicationDetails implements Serializable {

    private String name;
    private int standarddose;
    private int unitdose;

    public MedicationDetails(String name, String standarddose, String unitdose) {
        this(name,Integer.parseInt(standarddose),Integer.parseInt(unitdose));
    }
    public MedicationDetails(String name, int standarddose, int unitdose) {
        setName(name);
        setStandarddose(standarddose);
        setUnitdose(unitdose);
    }

    /**
     * Get the value of unitdose
     *
     * @return the value of unitdose
     */
    public int getUnitdose() {
        return unitdose;
    }

    /**
     * Set the value of unitdose
     *
     * @param unitdose new value of unitdose
     */
    public void setUnitdose(int unitdose) {
        this.unitdose = unitdose;
    }

    /**
     * Get the value of standarddose
     *
     * @return the value of standarddose
     */
    public int getStandarddose() {
        return standarddose;
    }

    /**
     * Set the value of standarddose
     *
     * @param standarddose new value of standarddose
     */
    public void setStandarddose(int standarddose) {
        this.standarddose = standarddose;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

}
