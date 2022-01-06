package kanagawa.models;

import kanagawa.models.enums.Bonus;
import kanagawa.models.enums.Skill;
import kanagawa.utilities.InvalidGameObjectException;

public class PersonalWork {
    private boolean hasPen;

    private Skill skill;

    private Bonus bonus;

    public PersonalWork(Skill skill, Bonus bonus) {
        this.hasPen = false;
        this.skill = skill;
        this.bonus = bonus;
    }

    /**
     * Checks if the Object has been parsed and initialized correctly
     * 
     * @param parent Collection in which the object is stored
     * @throws InvalidGameObjectException
     */
    public void checkInitialization(Card parent) throws InvalidGameObjectException {
        if (hasPen != false || skill == null || bonus == null) {
            throw new InvalidGameObjectException(parent);
        }
    }

    @Override
    public String toString() {
        return "PersonalWork{" +
                "hasPen=" + hasPen +
                ", skill=" + skill +
                ", bonus=" + bonus +
                '}';
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

    public void setHasPen(boolean hasPen) {
        this.hasPen = hasPen;
    }
}
