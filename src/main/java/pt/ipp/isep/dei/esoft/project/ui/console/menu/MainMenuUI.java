package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the main menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class MainMenuUI implements Runnable {

    /**
     * Constructs a new instance of MainMenuUI.
     */
    public MainMenuUI() {
    }

    /**
     * Starts the user interface for the main menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    @Override
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<>();
        // Add option to do login
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        // Add option to know the development team
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        // Add option to load data
        options.add(new MenuItem("Load Data", new LoadDataUI()));

        // Initial option selection
        int option;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- MAIN MENU --------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }

        } while (option != -1); // Continue until the user decides to exit
    }
}
