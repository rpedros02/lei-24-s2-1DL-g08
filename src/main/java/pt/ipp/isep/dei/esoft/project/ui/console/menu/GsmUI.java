package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the GSM menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class GsmUI implements Runnable {
    // Controllers for various functionalities
    private final AgendaController agendaController;
    private final ToDoListController toDoListController;
    private final AssignVehicleAgendaController assignVehicleAgendaController;
    private final GenerateTeamController generateTeamController;

    /**
     * Constructs a new instance of GsmUI.
     * Initializes the controllers for agenda, to-do list, vehicle assignment, and team generation.
     */
    public GsmUI() {
        this.agendaController = new AgendaController();
        this.toDoListController = new ToDoListController();
        this.assignVehicleAgendaController = new AssignVehicleAgendaController();
        this.generateTeamController = new GenerateTeamController();
    }

    /**
     * Starts the user interface for the GSM menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    @Override
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<>();
        // Add various options to the menu
        options.add(new MenuItem("Register a Green Space", new RegisterGreenSpaceUI()));
        options.add(new MenuItem("Add a new entry to the To Do List", new AddEntryToToDoListUI()));
        options.add(new MenuItem("Add a new entry to the Agenda", new AddEntryToAgendaUI()));
        options.add(new MenuItem("Assign a team to an entry", new AssignTeamToEntryUI()));
        options.add(new MenuItem("PostPone an entry in the agenda", new PostPoneAnEntryUI()));
        options.add(new MenuItem("Cancel an entry in the agenda", new CancelAnEntryUI()));
        options.add(new MenuItem("Assign one or more Vehicles", new AssignVehicleAgendaUI()));
        options.add(new MenuItem("List Green Spaces", new ListGreenSpacesUI()));

        // Initial option selection
        int option = 0;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1); // Continue until the user decides to exit
    }
}
