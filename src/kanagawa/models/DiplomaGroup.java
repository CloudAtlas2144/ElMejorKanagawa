package kanagawa.models;

import java.util.ArrayList;

import kanagawa.utilities.InvalidGameObjectException;

public class DiplomaGroup {

    private String groupeName;

    private ArrayList<Diploma> diplomas;

    public DiplomaGroup(String groupeName, ArrayList<Diploma> diplomas) {
        this.groupeName = groupeName;
        this.diplomas = diplomas;
    }

    /**
     * Checks if the Object has been parsed and initialized correctly
     * 
     * @throws InvalidGameObjectException
     */
    public void checkInitialization() throws InvalidGameObjectException {
        if (groupeName != null && diplomas != null) {
            for (Diploma diploma : diplomas) {
                diploma.checkInitialization(this);
            }
        } else {
            throw new InvalidGameObjectException(this);
        }
    }

    public String getGroupeName() {
        return groupeName;
    }

    public ArrayList<Diploma> getDiplomas() {
        return diplomas;
    }
}
