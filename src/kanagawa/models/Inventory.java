package kanagawa.models;

import java.util.ArrayList;

import kanagawa.models.enums.Bonus;

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

    // Getters
    public int getCredits() {
        return credits;
    }

    public ArrayList<PersonalWork> getPwPossessed() {
        return pwPossessed;
    }

    public ArrayList<UV> getUvPossessed() {
        return uvPossessed;
    }

    public ArrayList<Diploma> getDiplomaPossessed() {
        return diplomaPossessed;
    }

    public boolean isHasProfessor() {
        return hasProfessor;
    }

    // Setters
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setHasProfessor(boolean hasProfessor) {
        this.hasProfessor = hasProfessor;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "credits=" + credits +
                ", pwPossessed=" + pwPossessed +
                ", uvPossessed=" + uvPossessed +
                ", diplomaPossessed=" + diplomaPossessed +
                ", hasProfessor=" + hasProfessor +
                '}';
    }

    public void addPersonalWork(PersonalWork pw) {
        pwPossessed.add(pw);
        if (pw.getBonus() == Bonus.PROFESSOR) {
            setHasProfessor(true);

        }
    }

    public void addUv(UV uv) {
        uvPossessed.add(uv);
    }
}
