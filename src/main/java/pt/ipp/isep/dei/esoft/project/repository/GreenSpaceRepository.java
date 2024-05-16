package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.*;
import java.util.ArrayList;
import java.util.List;

public class GreenSpaceRepository {
    private final List<GreenSpace> greenSpaces = new ArrayList<>();

    public void addGreenSpace(GreenSpace greenSpace) {
        greenSpaces.add(greenSpace);
    }

    public List<GreenSpace> getAllGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }
}
