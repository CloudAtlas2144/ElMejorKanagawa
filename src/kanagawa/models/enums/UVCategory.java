package kanagawa.models.enums;

/**
 * UVCategory
 */
public enum UVCategory {
    CS(0),
    TM(1),
    EC(2),
    TSS(3);

    private final int uvCategory;

    public static final int length = 4;

    private UVCategory(int uvCategory) {
        this.uvCategory = uvCategory;
    }

    public int toInt() {
        return this.uvCategory;
    }

    public UVCategory next() {
        // FIXME : Check if method is necessary
        System.out.println("UVCategory.next()");
        return null;
    }

}