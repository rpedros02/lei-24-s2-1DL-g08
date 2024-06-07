package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AddEntryToAgendaUI;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AddEntryToToDoListUI;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AssignTeamToEntryUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GsmUI implements Runnable {
    public GsmUI() {
    }

    /**
     * Runs the GSM UI.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Add a new entry to the To Do List", new AddEntryToToDoListUI()));
        options.add(new MenuItem("Add a new entry to the Agenda", new AddEntryToAgendaUI(new ToDoListController(), new AgendaController())));
        options.add(new MenuItem("Assign a team to an entry", new AssignTeamToEntryUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}