package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.AssignSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.RegisterSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.teammenu.GenerateTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class HrmUI implements Runnable {

    public HrmUI() {

    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a Skill", new RegisterSkillUI()));
        options.add(new MenuItem("Register a Job", new CreateJobUI()));
        options.add(new MenuItem("Register a Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assign a Skill to a Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Generate Team", new GenerateTeamUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}