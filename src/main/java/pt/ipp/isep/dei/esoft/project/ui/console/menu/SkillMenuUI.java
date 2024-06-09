package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.AssignSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.RegisterSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.SearchSkilllidUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the Skill menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class SkillMenuUI implements Runnable{

    /**
     * Constructs a new instance of SkillMenuUI.
     */
    public SkillMenuUI() {
    }

    /**
     * Starts the user interface for the Skill menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<>();
        // Add option to register a new skill
        options.add(new MenuItem("Register a new Skill", new RegisterSkillUI()));
        // Add option to assign a skill to a collaborator
        options.add(new MenuItem("Assign a Skill to a Collaborator", new AssignSkillUI()));
        // Add option to search skill by name
        options.add(new MenuItem("Search Skill by Name", new SearchSkilllidUI()));
        // Add option to exit
        options.add(new MenuItem("Exit", new CollaboratorMenuUI()));

        // Initial option selection
        int option = 0;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- SKILL MENU ------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1); // Continue until the user decides to exit
    }
}