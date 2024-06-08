package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.ipp.isep.dei.esoft.project.ui.console.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainMenuUI implements Runnable {

    private final AgendaController agendaController;
    private final ToDoListController toDoListController;
    private final AssignVehicleAgendaController assignVehicleAgendaController;
    private final GenerateTeamController generateTeamController;

    public MainMenuUI(AgendaController agendaController, ToDoListController toDoListController,
                      AssignVehicleAgendaController assignVehicleAgendaController, GenerateTeamController generateTeamController) {
        this.agendaController = agendaController;
        this.toDoListController = toDoListController;
        this.assignVehicleAgendaController = assignVehicleAgendaController;
        this.generateTeamController = generateTeamController;
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Do Login", new AuthenticationUI(agendaController, toDoListController,
                assignVehicleAgendaController, generateTeamController)));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        options.add(new MenuItem("Load Data", new LoadDataUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- MAIN MENU --------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }

        } while (option != -1);
    }
}
