package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * The GreenSpaceController class is responsible for handling operations related to green spaces.
 * It interacts with the GreenSpaceRepository to perform operations such as registering, retrieving, removing, and updating green spaces.
 */
public class GreenSpaceController {
    /**
     * The GreenSpaceRepository instance.
     * This instance is used to interact with the green space repository,
     * allowing the controller to perform operations related to green spaces.
     */
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * The default constructor for the GreenSpaceController.
     * It initializes the GreenSpaceRepository instance.
     */
    public GreenSpaceController() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }

    /**
     * Registers a green space.
     * It creates a new GreenSpace object with the provided parameters and adds it to the green space repository.
     *
     * @param name The name of the green space.
     * @param type The type of the green space.
     * @param area The area of the green space.
     * @param address The address of the green space.
     * @param gsm The collaborator responsible for the green space.
     * @return A boolean indicating the success of the operation.
     */
    public boolean registerGreenSpace(String name, GreenSpaceTypeRepository type, double area, Address address, Collaborator gsm) {
        GreenSpace greenSpace = new GreenSpace(name, type, area, address, gsm);
        return greenSpaceRepository.addGreenSpace(greenSpace);
    }

    /**
     * Retrieves all green spaces.
     *
     * @return A list of GreenSpace objects.
     */
    public List<GreenSpace> getAllGreenSpaces() {
        return greenSpaceRepository.getGreenSpaces();
    }

    /**
     * Retrieves a green space by its name.
     *
     * @param name The name of the green space.
     * @return The GreenSpace object representing the green space with the specified name.
     */
    public GreenSpace getGreenSpaceByName(String name) {
        return greenSpaceRepository.getGreenSpaceByName(name);
    }

    /**
     * Removes a green space.
     *
     * @param greenSpace The GreenSpace object to be removed.
     * @return A boolean indicating the success of the operation.
     */
    public boolean removeGreenSpace(GreenSpace greenSpace) {
        return greenSpaceRepository.removeGreenSpace(greenSpace);
    }

    /**
     * Updates a green space.
     * It updates the specified GreenSpace object with the provided parameters.
     *
     * @param greenSpace The GreenSpace object to be updated.
     * @param name The new name of the green space.
     * @param type The new type of the green space.
     * @param area The new area of the green space.
     * @param address The new address of the green space.
     * @return A boolean indicating the success of the operation.
     */
    public boolean updateGreenSpace(GreenSpace greenSpace, String name, GreenSpaceTypeRepository type, double area, Address address) {
        return greenSpaceRepository.updateGreenSpace(greenSpace, name, type, area, address);
    }

    /**
     * Sets the GreenSpaceRepository instance.
     *
     * @param greenSpaceRepository The GreenSpaceRepository object.
     */
    public void setGreenSpaceRepository(GreenSpaceRepository greenSpaceRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
    }

    /**
     * Retrieves the green space repository.
     *
     * @return The GreenSpaceRepository object.
     */
    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    /**
     * Sets the GreenSpaceRepository instance.
     * It retrieves the GreenSpaceRepository instance from the repositories.
     */
    public void setGreenSpaceRepository() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }
}