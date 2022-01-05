package kanagawa.models;

import java.util.ArrayList;

import kanagawa.models.enums.Bonus;
import kanagawa.models.enums.Skill;

public class Inventory {

    private int credits;

    private int penCount;

    private ArrayList<PersonalWork> pwPossessed;

    private ArrayList<UV> uvPossessed;

    private ArrayList<Diploma> diplomaPossessed;

    private boolean hasProfessor;

    // Constructors
    public Inventory() {
        this.credits = 0;
        this.penCount = 0;
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
