package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.AssignSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.RegisterSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.teammenu.GenerateTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HrmUI implements Runnable {
    public HrmUI() {
    }

    /**
     * Runs the HRM UI.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Skill", new RegisterSkillUI()));
        options.add(new MenuItem("Register Job", new CreateJobUI()));
        options.add(new MenuItem("Register Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assigning Skills to a Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Generate Team Proposal", new GenerateTeamUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
