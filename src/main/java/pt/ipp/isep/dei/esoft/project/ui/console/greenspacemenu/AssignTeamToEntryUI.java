package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class AssignTeamToEntryUI implements Runnable {
    private final ToDoListController toDoListController;
    private final AgendaController agendaController;

    public AssignTeamToEntryUI() {
        this.agendaController = new AgendaController();
        this.toDoListController = new ToDoListController();
    }

    @Override
    public void run() {

    }
}
