package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.AssignSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.RegisterSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.teammenu.GenerateTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the user interface for the HRM (Human Resource Management) menu.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class HrmUI implements Runnable {

    /**
     * Constructs a new instance of HrmUI.
     */
    public HrmUI() {
    }

    /**
     * Starts the user interface for the HRM menu.
     * It displays the menu options and prompts the user to select an option.
     * It then runs the selected option.
     * It continues to display the menu options and prompt the user to select an option until the user decides to exit.
     */
    @Override
    public void run() {
        // List of menu options
        List<MenuItem> options = new ArrayList<>();
        // Add option to register a skill
        options.add(new MenuItem("Register Skill", new RegisterSkillUI()));
        // Add option to register a job
        options.add(new MenuItem("Register Job", new CreateJobUI()));
        // Add option to register a collaborator
        options.add(new MenuItem("Register Collaborator", new RegisterCollaboratorUI()));
        // Add option to assign skills to a collaborator
        options.add(new MenuItem("Assigning Skills to a Collaborator", new AssignSkillUI()));
        // Add option to generate a team proposal
        options.add(new MenuItem("Generate Team Proposal", new GenerateTeamUI()));

        // Initial option selection
        int option = 0;
        do {
            // Display menu and get user selection
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            // If a valid option is selected, run the corresponding action
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1); // Continue until the user decides to exit
    }
}
