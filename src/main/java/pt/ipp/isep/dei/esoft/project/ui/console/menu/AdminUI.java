package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.AssignSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AssignVehicleAgendaUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the Admin menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class AdminUI implements Runnable {

    /**
     * The agenda that stores the entries.
     */
    private final Agenda agenda;

    /**
     * The controller that handles the to-do list.
     */
    private final ToDoListController toDoListController;

    /**
     * The controller that handles the agenda.
     */
    private final AgendaController agendaController;

    /**
     * The controller that handles the assignment of vehicles to entries.
     */
    private final AssignVehicleAgendaController assignVehicleAgendaController;

    /**
     * Constructs a new instance of AdminUI.
     * It initializes the agenda, the to-do list controller, the agenda controller, and the assign vehicle agenda controller.
     */
    public AdminUI() {
        this.agenda = Repositories.getAgenda();
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.assignVehicleAgendaController = new AssignVehicleAgendaController();
    }

    /**
     * Starts the user interface for the Admin menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Vehicle Menu", new VehicleMenuUI()));
        options.add(new MenuItem("Team Menu", new TeamMenuUI()));
        options.add(new MenuItem("Green Space Menu", new GreenSpaceMenuUI()));
        options.add(new MenuItem("Agenda Menu", new AgendaMenuUI()));
        options.add(new MenuItem("Collaborator Menu", new CollaboratorMenuUI()));
        options.add(new MenuItem("Create a Job", new CreateJobUI()));
        options.add(new MenuItem("Assign vehicle to entry",new AssignVehicleAgendaUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
