package pt.ipp.isep.dei.esoft.project.repository;

import java.util.List;

/**
 * This enum represents the types of GreenSpaces in the system.
 * It has three types: GARDEN, MEDIUM_SIZED, and LARGE_SIZED.
 */
public enum GreenSpaceTypeRepository {
    /**
     * Represents a garden type GreenSpace.
     */
    GARDEN("Garden"),

    /**
     * Represents a medium sized type GreenSpace.
     */
    MEDIUM_SIZED("Medium Sized"),

    /**
     * Represents a large sized type GreenSpace.
     */
    LARGE_SIZED("Large Sized");

    /**
     * The type of the GreenSpace.
     */
    private final String type;

    /**
     * Constructs a GreenSpaceTypeRepository object with the specified type.
     *
     * @param type the type of the GreenSpace
     */
    GreenSpaceTypeRepository(String type) {
        this.type = type;
    }

    /**
     * Returns the type of this GreenSpaceTypeRepository.
     *
     * @return the type of this GreenSpaceTypeRepository
     */
    public String getType() {
        return type;
    }

    /**
     * Returns a list of all GreenSpace types.
     *
     * @return a list of all GreenSpace types
     */
    public static List<String> getGreenSpaceTypes() {
        return List.of(GARDEN.getType(), MEDIUM_SIZED.getType(), LARGE_SIZED.getType());
    }

    /**
     * Returns a string representation of this GreenSpaceTypeRepository.
     * The string representation is the type of this GreenSpaceTypeRepository.
     *
     * @return a string representation of this GreenSpaceTypeRepository
     */
    @Override
    public String toString() {
        return this.type;
    }
}
