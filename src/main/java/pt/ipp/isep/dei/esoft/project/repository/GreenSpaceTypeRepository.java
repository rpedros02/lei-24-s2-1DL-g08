package pt.ipp.isep.dei.esoft.project.repository;

import java.util.List;

public enum GreenSpaceTypeRepository {
    GARDEN("Garden"),
    MEDIUM_SIZED("Medium Sized"),
    LARGE_SIZED("Large Sized");

    private final String type;

    GreenSpaceTypeRepository(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static List<String> getGreenSpaceTypes() {
        return List.of(GARDEN.getType(), MEDIUM_SIZED.getType(), LARGE_SIZED.getType());
    }
}
