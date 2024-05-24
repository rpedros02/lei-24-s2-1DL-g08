package pt.ipp.isep.dei.esoft.project.repository;

import java.util.List;

public enum DegreeOfUrgencyRepository {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private final String type;

    DegreeOfUrgencyRepository(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static List<String> getDegreesOfUrgency() {
        return List.of(HIGH.getType(), MEDIUM.getType(), LOW.getType());
    }
}
