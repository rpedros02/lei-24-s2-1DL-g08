package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAnEntryController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;

import java.util.ArrayList;
import java.util.List;

public class GsmUI implements Runnable {
    private Agenda agenda;
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a Green Space", new RegisterGreenSpaceUI()));
        options.add(new MenuItem("Add a new entry to the To Do List", new AddEntryToToDoListUI()));
        options.add(new MenuItem("Add a new entry to the Agenda", new AddEntryToAgendaUI(new ToDoListController(), new AgendaController())));
        options.add(new MenuItem("Assign a team to an entry", new AssignTeamToEntryUI()));
        options.add(new MenuItem("PostPone an entry in the agenda", new PostPoneAnEntryUI(agenda)));
        options.add(new MenuItem("Cancel an entry in the agenda", new CancelAnEntryUI(agenda)));
        options.add(new MenuItem("Assign one or more Vehicles", new AssignTeamToEntryUI()));
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