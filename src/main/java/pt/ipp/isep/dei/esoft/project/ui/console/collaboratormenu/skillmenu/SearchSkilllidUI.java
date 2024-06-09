package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu.skillmenu;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

/**
 * This class provides the user interface for searching a skill by its name.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class SearchSkilllidUI implements Runnable{
    /**
     * The repository of skills.
     */
    private final SkillsRepository skillsRepository;

    /**
     * Constructs a new SearchSkillIdUI.
     * It initializes the skills repository.
     */
    public SearchSkilllidUI() {
        this.skillsRepository = Repositories.getInstance().getSkillsRepository();
    }

    /**
     * Starts the skill search process.
     * It prompts the user to enter the name of the skill.
     * It then attempts to retrieve the skill with the entered name from the skills repository.
     * If the skill does not exist, it prints a message indicating this.
     * If the skill exists, it prints the skill.
     */
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
