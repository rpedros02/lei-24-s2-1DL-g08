package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.agendamenu.ViewTasksTwoDatesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AgendaMenuUI implements Runnable{
    public AgendaMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("View Tasks between two dates.", new ViewTasksTwoDatesUI()));
        options.add(new MenuItem("Postpone an Entry.", new CreateJobUI()));
        options.add(new MenuItem("Cancel an Entry.", new ShowTextUI("Canceled")));
        options.add(new MenuItem("Exit", new AdminUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- AGENDA MENU ------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}

