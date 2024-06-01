package pt.ipp.isep.dei.esoft.project.domain.Enums;

public enum ESort {
    ASCENDING_ID (1),
    DESCENDING_ID (2),
    ALPHABETICAL (3),
    DESCENDING_ALPHABETICAL (4);

    private final int sort;

    ESort(int sort) {
        this.sort = sort;
    }

    public int sort() {
        return sort;
    }

    public static ESort getSort(int sort) {
        for (ESort s : ESort.values()) {
            if (s.sort == sort) {
                return s;
            }
        }
        return null;
    }

}
