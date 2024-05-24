package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.*;
import java.util.ArrayList;
import java.util.List;

public class GreenSpaceRepository {
    private List<GreenSpace> greenSpaces = new ArrayList<>();
    private GreenSpaceTypeRepository greenSpaceTypeRepository;


    public boolean addGreenSpace(GreenSpace greenSpace) {
        for (GreenSpace existingGreenSpace : greenSpaces) {
            if (existingGreenSpace.getName().equals(greenSpace.getName())) {
                return false;
            }
        }
        greenSpaces.add(greenSpace);
        return true;
    }

    public List<GreenSpace> getGreenSpaces() {
        return greenSpaces;
    }

    public GreenSpace getGreenSpaceByName(String name) {
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getName().equals(name)) {
                return greenSpace;
            }
        }
        return null;
    }

    public boolean removeGreenSpace(GreenSpace greenSpace) {
        return greenSpaces.remove(greenSpace);
    }

    public boolean updateGreenSpace(GreenSpace greenSpace, String name, GreenSpaceTypeRepository type, double area) {
        if (greenSpaces.contains(greenSpace)) {
            greenSpace.setName(name);
            greenSpace.setType(type);
            greenSpace.setArea(area);
            return true;
        }
        return false;
    }

    public boolean isManagedByGSM(GreenSpace greenSpace) {
        return greenSpaces.contains(greenSpace);
    }

}
