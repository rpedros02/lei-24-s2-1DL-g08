package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.agendamenu.ViewTasksTwoDatesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the Collaborator menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class CollaboratorUI implements Runnable{

    /**
     * Starts the user interface for the Collaborator menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<>();
        // Add option to consult tasks assigned between two dates
        options.add(new MenuItem("Consult the tasks assign in the between 2 dates", new ViewTasksTwoDatesUI()));
        // Add option to record the completion of a task
        options.add(new MenuItem("Record the completion of a task", new CompleteTasksUI()));

        // Initial option selection
        int option  = 1;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- COLLABORATOR-------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1); // Continue until the user decides to exit
    }
}
