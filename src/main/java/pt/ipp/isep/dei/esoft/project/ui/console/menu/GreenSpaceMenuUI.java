package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AddEntryToToDoListUI;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AssignTeamToEntryUI;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.PostPoneAnEntryUI;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.RegisterGreenSpaceUI;
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
        options.add(new MenuItem("Assign a team to an entry", new AssignTeamToEntryUI()));
        options.add(new MenuItem("Postpone an entry in the Agenda", new PostPoneAnEntryUI));
        options.add(new MenuItem("Cancel an entry in the Agenda", new CancelAnEntryUI));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GREEN SPACE MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}



