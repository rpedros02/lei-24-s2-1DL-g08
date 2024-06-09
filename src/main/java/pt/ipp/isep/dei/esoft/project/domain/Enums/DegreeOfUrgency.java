package pt.ipp.isep.dei.esoft.project.domain.Enums;

import java.util.List;

/**
 * The DegreeOfUrgency enum represents the degree of urgency of a task.
 * It has three levels: HIGH, MEDIUM, and LOW.
 */
public enum DegreeOfUrgency {
    /**
     * Represents a high degree of urgency.
     */
    HIGH("high"),
    /**
     * Represents a medium degree of urgency.
     */
    MEDIUM("medium"),
    /**
     * Represents a low degree of urgency.
     */
    LOW("low");

    /**
     * The type of the degree of urgency.
     */
    private final String type;

    /**
     * Constructs a DegreeOfUrgency with the specified type.
     *
     * @param type The type of the degree of urgency.
     */
    DegreeOfUrgency(String type) {
        this.type = type;
    }

    /**
     * Retrieves the type of the degree of urgency.
     *
     * @return The type of the degree of urgency.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves a list of all degrees of urgency.
     *
     * @return A list of all degrees of urgency.
     */
    public static List<String> getDegreesOfUrgency() {
        return List.of(HIGH.getType(), MEDIUM.getType(), LOW.getType());
    }

    /**
     * Returns a string representation of the degree of urgency.
     *
     * @return A string representation of the degree of urgency.
     */
    @Override
    public String toString() {
        return this.type;
    }
}
