package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.AddEntryToToDoListUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterGreenSpaceUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceMenuUI implements Runnable {
    public GreenSpaceMenuUI() {
    }

    /**
     * Runs the GSM UI.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Green Space", new RegisterGreenSpaceUI()));
        options.add(new MenuItem("Add a new entry to the To Do List", new AddEntryToToDoListUI()));
        options.add(new MenuItem("Add a new entry to the Agenda", new AddEntryToAgendaUI()));
        options.add(new MenuItem("Assign a team to an entry", new AssignTeamToAgendaEntryUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GREEN SPACE MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}



