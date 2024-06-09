package pt.ipp.isep.dei.esoft.project.domain.Enums;

/**
 * The EStatus enum represents the status of a task.
 * It has four statuses: PLANNED, POSTPONED, FINISHED, and CANCELED.
 */
public enum EStatus {
    /**
     * Represents a planned status.
     */
    PLANNED(1),
    /**
     * Represents a postponed status.
     */
    POSTPONED(2),
    /**
     * Represents a finished status.
     */
    FINISHED(3),
    /**
     * Represents a canceled status.
     */
    CANCELED(4);

    /**
     * The status value.
     */
    private final int status;

    /**
     * Constructs an EStatus with the specified status value.
     *
     * @param status The status value.
     */
    EStatus(int status) {
        this.status = status;
    }

    /**
     * Retrieves the status value.
     *
     * @return The status value.
     */
    public int status() {
        return status;
    }

    /**
     * Retrieves the EStatus object corresponding to the provided status value.
     * If no EStatus object corresponds to the provided status value, it returns null.
     *
     * @param status The status value.
     * @return The EStatus object corresponding to the provided status value, or null if no such object exists.
     */
    public static EStatus getStatus(int status) {
        for (EStatus s : EStatus.values()) {
            if (s.status == status) {
                return s;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the status.
     *
     * @return A string representation of the status.
     */
    @Override
    public String toString() {
        return switch (this) {
            case PLANNED -> "Planned";
            case POSTPONED -> "Postponed";
            case FINISHED -> "Finished";
            case CANCELED -> "Canceled";
        };
    }
}