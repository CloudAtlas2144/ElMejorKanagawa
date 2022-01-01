package kanagawa.models.enums;

public enum Skill {
    MATH(0),
    INFO(1),
    ENERGY(2),
    ERGO(3),
    MECHANICS(4),
    INDUSTRY(5),
    LANGUAGE(6),
    MANAGEMENT(7);

    private final int skill;

    public static final int length = 8;

    private Skill(int skill) {
        this.skill = skill;
    }

    public int toInt() {
        return this.skill;
    }

    public UVCategory next() {
        // FIXME : Check if method is necessary
        System.out.println("Skill.next()");
        return null;
    }
}
