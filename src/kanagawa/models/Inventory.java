package kanagawa.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int credits;
    private List<PersonalWork> personalWorks;

    // Constructors
    public Inventory() {
        this.credits = 0;
        this.personalWorks = new ArrayList<>();
    }

    public Inventory(int credits, List<PersonalWork> personalWorks) {
        this.credits = credits;
        this.personalWorks = personalWorks;
    }

    // Getters and setters
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<PersonalWork> getSkills() {
        return personalWorks;
    }

    public void setSkills(List<PersonalWork> personalWorks) {
        this.personalWorks = personalWorks;
    }

    public void addSkill(PersonalWork personalWork) {
        this.personalWorks.add(personalWork);
    }
}
