package pt.ipp.isep.dei.esoft.project.domain.Enums;

import java.util.List;

public enum DegreeOfUrgency {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private final String type;

    DegreeOfUrgency(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static List<String> getDegreesOfUrgency() {
        return List.of(HIGH.getType(), MEDIUM.getType(), LOW.getType());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
