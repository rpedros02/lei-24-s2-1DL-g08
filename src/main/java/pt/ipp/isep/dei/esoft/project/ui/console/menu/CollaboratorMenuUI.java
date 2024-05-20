package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CollaboratorMenuUI implements Runnable{
    public CollaboratorMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a new Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Register a Job to a Collaborator", new CreateJobUI()));
        options.add(new MenuItem("Exit", new AdminUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- COLLABORATOR MENU ------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}

