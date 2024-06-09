package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a GreenSpaceRepository in the system.
 * It manages a list of GreenSpace objects and a GreenSpaceTypeRepository.
 */
public class GreenSpaceRepository {
    /**
     * The list of GreenSpace objects.
     */
    private List<GreenSpace> greenSpaces = new ArrayList<>();

    /**
     * The GreenSpaceTypeRepository instance used for managing GreenSpaceType objects.
     */
    private GreenSpaceTypeRepository greenSpaceTypeRepository;

    /**
     * Adds a GreenSpace to the repository.
     * It checks if a GreenSpace with the same name already exists in the list before adding.
     *
     * @param greenSpace The GreenSpace to add.
     * @return true if the GreenSpace was added successfully, false otherwise.
     */
    public boolean addGreenSpace(GreenSpace greenSpace) {
        for (GreenSpace existingGreenSpace : greenSpaces) {
            if (existingGreenSpace.getName().equals(greenSpace.getName())) {
                return false;
            }
        }
        greenSpaces.add(greenSpace);
        return true;
    }

    /**
     * Returns the list of all GreenSpace objects in the repository.
     *
     * @return the list of all GreenSpace objects.
     */
    public List<GreenSpace> getGreenSpaces() {
        return greenSpaces;
    }

    /**
     * Returns a GreenSpace object with the specified name.
     * If no GreenSpace has the specified name, it returns null.
     *
     * @param name the name of the GreenSpace to return.
     * @return the GreenSpace object with the specified name.
     */
    public GreenSpace getGreenSpaceByName(String name) {
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getName().equals(name)) {
                return greenSpace;
            }
        }
        return null;
    }

    /**
     * Removes the specified GreenSpace from the repository.
     *
     * @param greenSpace the GreenSpace to remove.
     * @return true if the GreenSpace was removed successfully, false otherwise.
     */
    public boolean removeGreenSpace(GreenSpace greenSpace) {
        return greenSpaces.remove(greenSpace);
    }

    /**
     * Updates the specified GreenSpace with the specified parameters.
     *
     * @param greenSpace the GreenSpace to update.
     * @param name the new name of the GreenSpace.
     * @param type the new type of the GreenSpace.
     * @param area the new area of the GreenSpace.
     * @param address the new address of the GreenSpace.
     * @return true if the GreenSpace was updated successfully, false otherwise.
     */
    public boolean updateGreenSpace(GreenSpace greenSpace, String name, GreenSpaceTypeRepository type, double area, Address address) {
        if (greenSpaces.contains(greenSpace)) {
            greenSpace.setName(name);
            greenSpace.setType(type);
            greenSpace.setArea(area);
            greenSpace.setAddress(address);
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified GreenSpace is managed by the GreenSpaceManager.
     *
     * @param greenSpace the GreenSpace to check.
     * @return true if the GreenSpace is managed by the GreenSpaceManager, false otherwise.
     */
    public boolean isManagedByGSM(GreenSpace greenSpace) {
        return greenSpaces.contains(greenSpace);
    }
}