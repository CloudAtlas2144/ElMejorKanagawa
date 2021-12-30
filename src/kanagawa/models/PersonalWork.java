package kanagawa.models;

import kanagawa.models.enums.Bonus;
import kanagawa.models.enums.Skill;

public class PersonalWork {
    private boolean hasPen;

    private Skill skill;

    private Bonus bonus;

    public PersonalWork(Skill skill, Bonus bonus) {
        this.hasPen = false;
        this.skill = skill;
        this.bonus = bonus;
    }

    public boolean isHasPen() {
        return hasPen;
    }

    public Skill getSkill() {
        return skill;
    }

    public Bonus getBonus() {
        return bonus;
    }
}
