package pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu;


import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.controller.VehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;


import java.util.List;

/**
 * This class provides the user interface for the CheckUps menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class CheckUpsUI implements Runnable {
    // Controller for listing green spaces
    private final ListGreenSpacesController controller;

    /**
     * Constructs a new instance of CheckUpsUI.
     */
    public CheckUpsUI() {
        this.controller = new ListGreenSpacesController();
    }

    /**
     * Starts the user interface for the CheckUps menu.
     * It displays the list of green spaces.
     */
    @Override
    public void run() {
        System.out.println("Green Spaces:");
        // Get the list of green spaces
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        // Display the list of green spaces
        displayGreenSpaces(greenSpaces);
        System.out.println("--------------------");
    }

    /**
     * Displays the list of green spaces.
     * If the list is empty, it displays a message indicating that no green spaces were found.
     * Otherwise, it displays each green space in the list.
     * @param greenSpaces the list of green spaces
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