package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the Collaborator menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class CollaboratorMenuUI implements Runnable{

    /**
     * Constructs a new instance of CollaboratorMenuUI.
     */
    public CollaboratorMenuUI() {
    }

    /**
     * Starts the user interface for the Collaborator menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a new Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Register a Job to a Collaborator", new CreateJobUI()));
        options.add(new MenuItem("Skills Menu", new SkillMenuUI()));
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

