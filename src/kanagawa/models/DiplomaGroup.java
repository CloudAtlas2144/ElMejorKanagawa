package kanagawa.models;

import java.util.ArrayList;

public class DiplomaGroup {

    private String groupeName;

    private ArrayList<Diploma> diplomas;

    public DiplomaGroup(String groupeName, ArrayList<Diploma> diplomas) {
        this.groupeName = groupeName;
        this.diplomas = diplomas;
    }

    public String getGroupeName() {
        return groupeName;
    }

    public ArrayList<Diploma> getDiplomas() {
        return diplomas;
    }
}
