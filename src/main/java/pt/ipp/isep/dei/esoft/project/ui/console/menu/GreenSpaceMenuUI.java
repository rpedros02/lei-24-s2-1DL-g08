package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleAgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the Green Space menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class GreenSpaceMenuUI implements Runnable {

    /**
     * Constructs a new instance of GreenSpaceMenuUI.
     */
    public GreenSpaceMenuUI() {
    }

    /**
     * Starts the user interface for the Green Space menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    @Override
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<>();
        // Add option to register a green space
        options.add(new MenuItem("Register Green Space", new RegisterGreenSpaceUI()));
        // Add option to add a new entry to the to-do list
        options.add(new MenuItem("Add a new entry to the To Do List", new AddEntryToToDoListUI()));
        // Add option to assign a team to an entry
        options.add(new MenuItem("Assign a team to an entry", new AssignTeamToEntryUI()));
        // Add option to postpone an entry in the agenda
        options.add(new MenuItem("Postpone an entry in the Agenda", new PostPoneAnEntryUI()));
        // Add option to list green spaces
        options.add(new MenuItem("List Green Spaces", new ListGreenSpacesUI()));

        // Initial option selection
        int option = 0;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- GREEN SPACE MENU -------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1); // Continue until the user decides to exit
    }
}
