package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

/**
 * This class provides the user interface for listing all Green Spaces.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class ListGreenSpacesUI implements Runnable {
    /**
     * The controller that handles the listing of Green Spaces.
     */
    private final ListGreenSpacesController controller;

    /**
     * Constructs a new instance of ListGreenSpacesUI.
     * It initializes the controller.
     */
    public ListGreenSpacesUI() {
        this.controller = new ListGreenSpacesController();
    }

    /**
     * Starts the user interface for listing all Green Spaces.
     * It retrieves all Green Spaces and displays them.
     * If there are no Green Spaces, it prints a message indicating that no Green Spaces were found.
     */
    @Override
    public void run() {
        System.out.println("Green Spaces:");
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        displayGreenSpaces(greenSpaces);
        System.out.println("--------------------");
    }

    /**
     * Displays the list of Green Spaces.
     * If the list is empty, it prints a message indicating that no Green Spaces were found.
     * Otherwise, it prints each Green Space in the list.
     *
     * @param greenSpaces the list of Green Spaces to display
     */
    private void displayGreenSpaces(List<GreenSpace> greenSpaces) {
        if (greenSpaces.isEmpty()) {
            System.out.println("No green spaces found for this GSM.");
        } else {
            for (GreenSpace greenSpace : greenSpaces) {
                System.out.println(greenSpace);
            }
        }
    }
}