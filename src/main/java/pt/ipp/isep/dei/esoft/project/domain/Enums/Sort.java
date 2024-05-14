package pt.ipp.isep.dei.esoft.project.domain.Enums;

public enum Sort {
    ASCENDING_ID (1),
    DESCENDING_ID (2),
    ALPHABETICAL (3),
    DESCENDING_ALPHABETICAL (4);

    private final int sort;

    Sort(int sort) {
        this.sort = sort;
    }

    public int sort() {
        return sort;
    }

    public static Sort getSort(int sort) {
        for (Sort s : Sort.values()) {
            if (s.sort == sort) {
                return s;
            }
        }
        return null;
    }

}
