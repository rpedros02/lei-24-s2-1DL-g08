package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceMenuUI implements Runnable {
    private Agenda agenda;
    private ToDoListController toDoListController;
    private AgendaController agendaController;

    public GreenSpaceMenuUI(Agenda agenda, ToDoListController toDoListController, AgendaController agendaController) {
        this.agenda = agenda;
        this.toDoListController = toDoListController;
        this.agendaController = agendaController;
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register Green Space", new RegisterGreenSpaceUI()));
        options.add(new MenuItem("Add a new entry to the To Do List", new AddEntryToToDoListUI()));
        options.add(new MenuItem("Assign a team to an entry", new AssignTeamToEntryUI(toDoListController, agendaController, new GenerateTeamController()))); // Adicionando um novo GenerateTeamController
        options.add(new MenuItem("Postpone an entry in the Agenda", new PostPoneAnEntryUI(agenda)));
        options.add(new MenuItem("Cancel an entry in the Agenda", new CancelAnEntryUI(agenda)));
        options.add(new MenuItem("List Green Spaces", new ListGreenSpacesUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GREEN SPACE MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
