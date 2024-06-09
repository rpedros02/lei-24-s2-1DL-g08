package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu.CreateVehicleCheckupUI;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the Maintenance menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class MaintenanceMenuUI implements Runnable{

    /**
     * Constructs a new instance of MaintenanceMenuUI.
     */
    public MaintenanceMenuUI() {
    }

    /**
     * Starts the user interface for the Maintenance menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<MenuItem>();
        // Add option to register a check-up
        options.add(new MenuItem("Register Check-Up", new CreateVehicleCheckupUI()));
        // Add option to display the maintenance list
        options.add(new MenuItem("Maintenance List", new ShowTextUI("You have chosen Option 2.")));

        // Initial option selection
        int option = 0;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- Maintenance MENU ------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1); // Continue until the user decides to exit
    }
}