package kanagawa.models;

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

    public void setGroup(DiplomaGroup group) {
        this.group = group;
    }
}
