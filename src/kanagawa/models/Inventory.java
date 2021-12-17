package kanagawa.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int credits;
    private List<Skill> skills;

    // Constructors
    public Inventory() {
        this.credits = 0;
        this.skills = new ArrayList<>();
    }

    public Inventory(int credits, List<Skill> skills) {
        this.credits = credits;
        this.skills = skills;
    }

    // Getters and setters
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
}
