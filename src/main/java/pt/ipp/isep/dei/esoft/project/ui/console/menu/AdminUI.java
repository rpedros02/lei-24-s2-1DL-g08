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

public class AdminUI implements Runnable {

    private final Agenda agenda;
    private final ToDoListController toDoListController;
    private final AgendaController agendaController;
    private final AssignVehicleAgendaController assignVehicleAgendaController; // Adicione a variável

    public AdminUI() {
        this.agenda = Repositories.getAgenda();
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
        this.assignVehicleAgendaController = new AssignVehicleAgendaController(); // Inicialize a variável
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Vehicle Menu", new VehicleMenuUI()));
        options.add(new MenuItem("Team Menu", new TeamMenuUI()));
        options.add(new MenuItem("Green Space Menu", new GreenSpaceMenuUI(agenda, toDoListController, agendaController)));
        options.add(new MenuItem("Agenda Menu", new AgendaMenuUI()));
        options.add(new MenuItem("Collaborator Menu", new CollaboratorMenuUI()));
        options.add(new MenuItem("Create a Job", new CreateJobUI()));
        options.add(new MenuItem("Assign vehicle to entry",new AssignVehicleAgendaUI(assignVehicleAgendaController)));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
