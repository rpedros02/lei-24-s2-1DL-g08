package pt.ipp.isep.dei.esoft.project.domain.Enums;

public enum EStatus {
    PLANNED(1),
    POSTPONED(2),
    FINISHED(3),
    CANCELED(4);

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
            case PLANNED -> "Planned";
            case POSTPONED -> "Postponed";
            case FINISHED -> "Finished";
            case CANCELED -> "Canceled";
        };
    }
}