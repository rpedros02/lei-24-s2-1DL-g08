package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.CreateVehicleUI;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu.CheckUpsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu.CreateVehicleCheckupUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the Vehicle Fleet Management (VFM) menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class VfmUI implements Runnable {

    /**
     * Constructs a new instance of VfmUI.
     */
    public VfmUI() {
    }

    /**
     * Starts the user interface for the VFM menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    @Override
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<MenuItem>();
        // Add option to create a vehicle
        options.add(new MenuItem("Create Vehicle", new CreateVehicleUI()));
        // Add option to register a vehicle check-up
        options.add(new MenuItem("Register a Vehicle Check up", new CreateVehicleCheckupUI()));
        // Add option to register a check-up or see vehicles needing check-up
        options.add(new MenuItem("Register Check Up or See Vehicles needing Check Up", new CheckUpsUI()));

        // Initial option selection
        int option = 0;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- VFM MENU -------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1); // Continue until the user decides to exit
    }
}
