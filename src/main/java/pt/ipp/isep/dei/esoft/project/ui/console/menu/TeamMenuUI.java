package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.teammenu.GenerateTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the Team menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class TeamMenuUI implements Runnable{

    /**
     * Constructs a new instance of TeamMenuUI.
     */
    public TeamMenuUI() {
    }

    /**
     * Starts the user interface for the Team menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<MenuItem>();
        // Add option to create a team proposal
        options.add(new MenuItem("Create Team Proposal", new GenerateTeamUI()));

        // Initial option selection
        int option = 0;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- TEAM MENU ------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1); // Continue until the user decides to exit
    }
}


