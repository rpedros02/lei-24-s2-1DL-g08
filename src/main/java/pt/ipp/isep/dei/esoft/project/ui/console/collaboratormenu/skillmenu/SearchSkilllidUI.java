package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class SearchSkilllidUI implements Runnable{
    private final SkillsRepository skillsRepository;

    public SearchSkilllidUI() {
        this.skillsRepository = Repositories.getInstance().getSkillsRepository();
    }

    @Override
    public void run() {
        String name = Utils.readLineFromConsole("Please insert the name of the skill you want to search: ");
        Skill skill = skillsRepository.getSkillByName(name);
        if (skill == null) {
            System.out.println("There is no skill with the name " + name);
        } else {
            System.out.println(skill.toString());
        }
        System.out.println("--------------------");
    }
}
