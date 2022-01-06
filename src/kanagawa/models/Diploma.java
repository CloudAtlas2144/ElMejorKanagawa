package kanagawa.models;

import kanagawa.models.enums.Skill;
import kanagawa.models.enums.UVCategory;
import kanagawa.utilities.InvalidGameObjectException;

import java.util.Arrays;

public class Diploma {

    private int[] UVArray;

    private int[] skillArray;

    private int credit;

    private boolean isAvailable;

    transient private DiplomaGroup group;

    public Diploma(int[] uVArray, int[] skillArray, int credit, boolean isAvailable) {
        UVArray = uVArray;
        this.skillArray = skillArray;
        this.credit = credit;
        this.isAvailable = isAvailable;
    }

    /**
     * Checks if the Object has been parsed and initialized correctly
     * 
     * @param parent Collection in which the object is stored
     * @throws InvalidGameObjectException
     */
    public void checkInitialization(DiplomaGroup parent) throws InvalidGameObjectException {
        if (UVArray == null || UVArray.length != UVCategory.length || skillArray == null
                || skillArray.length != Skill.length || credit == 0 || isAvailable != true) {
            throw new InvalidGameObjectException(this, parent);
        }
        this.group = parent;
    }

    public int[] getUVArray() {
        return UVArray;
    }

    public int[] getSkillArray() {
        return skillArray;
    }

    public int getCredit() {
        return credit;
    }

    public DiplomaGroup getGroup() {
        return group;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "Diploma{" +
                "UVArray=" + Arrays.toString(UVArray) +
                ", skillArray=" + Arrays.toString(skillArray) +
                ", credit=" + credit +
                ", isAvailable=" + isAvailable +
                ", group=" + group.getGroupeName() +
                '}';
    }
}
