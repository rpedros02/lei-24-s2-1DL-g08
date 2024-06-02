package pt.ipp.isep.dei.esoft.project.domain.Enums;

public enum EStatus {
    PENDING(1),
    IN_PROGRESS(2),
    FINISHED(3);

    private final int status;

    EStatus(int status) {
        this.status = status;
    }

    public int status() {
        return status;
    }

    public static EStatus getStatus(int status) {
        for (EStatus s : EStatus.values()) {
            if (s.status == status) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return switch (this) {
            case PENDING -> "Pending";
            case IN_PROGRESS -> "In Progress";
            case FINISHED -> "Finished";
        };
    }
}
