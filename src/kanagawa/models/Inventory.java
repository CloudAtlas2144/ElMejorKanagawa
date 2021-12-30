package kanagawa.models;

import java.util.ArrayList;

public class Inventory {

    private int credits;

    private ArrayList<PersonalWork> pwPossessed;

    private ArrayList<UV> uvPossessed;

    private ArrayList<Diploma> diplomaPossessed;

    private boolean hasProfessor;

    // Constructors
    public Inventory() {
        this.credits = 0;
        this.pwPossessed = new ArrayList<PersonalWork>();
        this.uvPossessed = new ArrayList<UV>();
        this.diplomaPossessed = new ArrayList<Diploma>();
        this.hasProfessor = false;
    }

}
