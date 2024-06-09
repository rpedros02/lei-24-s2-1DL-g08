package pt.ipp.isep.dei.esoft.project.domain.Enums;

/**
 * The ESort enum represents the sorting options.
 * It has four options: ASCENDING_ID, DESCENDING_ID, ALPHABETICAL, and DESCENDING_ALPHABETICAL.
 */
public enum ESort {
    /**
     * Represents ascending ID sorting.
     */
    ASCENDING_ID (1),
    /**
     * Represents descending ID sorting.
     */
    DESCENDING_ID (2),
    /**
     * Represents alphabetical sorting.
     */
    ALPHABETICAL (3),
    /**
     * Represents descending alphabetical sorting.
     */
    DESCENDING_ALPHABETICAL (4);

    /**
     * The sort option.
     */
    private final int sort;

    /**
     * Constructs an ESort with the specified sort option.
     *
     * @param sort The sort option.
     */
    ESort(int sort) {
        this.sort = sort;
    }

    /**
     * Retrieves the sort option.
     *
     * @return The sort option.
     */
    public int sort() {
        return sort;
    }

    /**
     * Retrieves the ESort object corresponding to the provided sort option.
     * If no ESort object corresponds to the provided sort option, it returns null.
     *
     * @param sort The sort option.
     * @return The ESort object corresponding to the provided sort option, or null if no such object exists.
     */
    public static ESort getSort(int sort) {
        for (ESort s : ESort.values()) {
            if (s.sort == sort) {
                return s;
            }
        }
        return null;
    }
}
