package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * The ListGreenSpacesController class is responsible for handling operations related to listing green spaces.
 * It interacts with the GreenSpaceRepository to perform operations such as retrieving all green spaces.
 */
public class ListGreenSpacesController {
    /**
     * The GreenSpaceRepository instance.
     * This instance is used to interact with the green space repository,
     * allowing the controller to perform operations related to green spaces.
     */
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * The default constructor for the ListGreenSpacesController.
     * It initializes the GreenSpaceRepository instance.
     */
    public ListGreenSpacesController() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    /**
     * Retrieves all green spaces.
     *
     * @return A list of GreenSpace objects.
     */
    public List<GreenSpace> getAllGreenSpaces(){
        return this.greenSpaceRepository.getGreenSpaces();
    }

}