package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.AssignSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.RegisterSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu.SearchSkilllidUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SkillMenuUI implements Runnable{

    public SkillMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a new Skill", new RegisterSkillUI()));
        options.add(new MenuItem("Assign a Skill to a Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Search Skill by Name", new SearchSkilllidUI()));
        options.add(new MenuItem("Exit", new CollaboratorMenuUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- SKILL MENU ------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
