package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GsmUI implements Runnable {
    private final AgendaController agendaController;
    private final ToDoListController toDoListController;
    private final AssignVehicleAgendaController assignVehicleAgendaController;
    private final GenerateTeamController generateTeamController;

    public GsmUI(AgendaController agendaController, ToDoListController toDoListController,
                 AssignVehicleAgendaController assignVehicleAgendaController, GenerateTeamController generateTeamController) {
        this.agendaController = agendaController;
        this.toDoListController = toDoListController;
        this.assignVehicleAgendaController = assignVehicleAgendaController;
        this.generateTeamController = generateTeamController;
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a Green Space", new RegisterGreenSpaceUI()));
        options.add(new MenuItem("Add a new entry to the To Do List", new AddEntryToToDoListUI(toDoListController)));
        options.add(new MenuItem("Add a new entry to the Agenda", new AddEntryToAgendaUI(toDoListController, agendaController, assignVehicleAgendaController)));
        options.add(new MenuItem("Assign a team to an entry", new AssignTeamToEntryUI(toDoListController, agendaController, generateTeamController)));
        options.add(new MenuItem("PostPone an entry in the agenda", new PostPoneAnEntryUI(agendaController)));
        options.add(new MenuItem("Cancel an entry in the agenda", new CancelAnEntryUI(agendaController)));
        options.add(new MenuItem("Assign one or more Vehicles", new AssignVehicleAgendaUI(assignVehicleAgendaController)));
        options.add(new MenuItem("List Green Spaces", new ListGreenSpacesUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
