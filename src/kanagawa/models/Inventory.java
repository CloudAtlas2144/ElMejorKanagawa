package kanagawa.models;

import java.util.ArrayList;

import kanagawa.models.enums.Bonus;
import kanagawa.models.enums.Skill;

public class Inventory {

    private int credits;

    private int penCount;

    private int tempPenCount;

    private ArrayList<PersonalWork> pwPossessed;

    private ArrayList<UV> uvPossessed;

    private ArrayList<Diploma> diplomaPossessed;

    /**
     * List of the diplomas that the player has already refused.
     */
    private ArrayList<Diploma> refusedDiplomas;

    /**
     * List of the groups in which the player already has got a diploma.
     */
    private ArrayList<DiplomaGroup> unavailableDiplomaGroups;

    private boolean hasProfessor;

    // Constructors
    public Inventory() {
        this.credits = 0;
        this.penCount = 2;
        this.tempPenCount = this.penCount;
        this.pwPossessed = new ArrayList<PersonalWork>();
        this.uvPossessed = new ArrayList<UV>();
        this.diplomaPossessed = new ArrayList<Diploma>();
        this.refusedDiplomas = new ArrayList<Diploma>();
        this.unavailableDiplomaGroups = new ArrayList<DiplomaGroup>();
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

    public int getTempPenCount() {
        return tempPenCount;
    }

    public ArrayList<Diploma> getDiplomaPossessed() {
        return diplomaPossessed;
    }

    /**
     * Returns the list of the diplomas that the player has already refused.
     */
    public ArrayList<Diploma> getRefusedDiplomas() {
        return refusedDiplomas;
    }

    /**
     * Returns the list of the groups in which the player already has got a diploma.
     */
    public ArrayList<DiplomaGroup> getUnavailableDiplomaGroups() {
        return unavailableDiplomaGroups;
    }

    public boolean isHasProfessor() {
        return hasProfessor;
    }

    public int getPenCount() {
        return penCount;
    }

    // Setters
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setPenCount(int penCount) {
        this.penCount = penCount;
    }

    public void setHasProfessor(boolean hasProfessor) {
        this.hasProfessor = hasProfessor;
    }

    /**
     * Adds a diploma to the inventory of the player and adds the group of the added
     * diploma to the list of unavailable diploma groups.
     * 
     * @param diploma
     */
    public void addDiploma(Diploma diploma) {
        if (this.diplomaPossessed.contains(diploma)) {
            System.err.println("Inventory.addDiploma() : Diploma already possessed.");
            return;
        }
        this.diplomaPossessed.add(diploma);
        this.unavailableDiplomaGroups.add(diploma.getGroup());
    }

    /**
     * Adds a refused diploma to the inventory of the player.
     * 
     * @param diploma
     */
    public void addRefusedDiploma(Diploma diploma) {
        if (this.refusedDiplomas.contains(diploma)) {
            System.err.println("Inventory.addRefusedDiploma() : Diploma already refused.");
        }
        this.refusedDiplomas.add(diploma);
    }

    public void setTempPenCount(int tempPenCount) {
        this.tempPenCount = tempPenCount;
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

    public int getSkillCount(Skill skill) {
        int count = 0;
        for (PersonalWork pw : pwPossessed) {
            if (pw.getSkill() == skill) {
                count++;
            }
        }

        return count;
    }
}
