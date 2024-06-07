package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SkillController implements Serializable {
    private SkillsRepository skillsRepository;

    /**
     * Instantiates a new Skill controller.
     */
    public SkillController() {
        if (skillsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillsRepository = repositories.getSkillsRepository();
        }
    }
    /**
     * Retrieves a list of skills that the collaborator does not already have.
     *
     * @param collaborator The collaborator.
     * @return The list of skills that the collaborator does not already have.
     */
    public List<Skill> displaySkills(Collaborator collaborator) {
        List<Skill> allSkills = skillsRepository.getAllSkills();
        if (allSkills == null) {
            allSkills = new ArrayList<>();
        }

        List<Skill> collaboratorSkills = collaborator.getAssignedSkills();
        if (collaboratorSkills == null) {
            collaboratorSkills = new ArrayList<>();
        }

        allSkills.removeAll(collaboratorSkills);

        return allSkills;
    }
    /**
     * Gets skills by names.
     *
     * @param skillNames the skill names
     * @return the skills by names
     */
    private List<Skill> getSkillsByNames(List<String> skillNames) {
        List<Skill> skills = new ArrayList<>();
        for (String skillName : skillNames) {
            Skill skill = skillsRepository.getSkillByName(skillName);
            if (skill != null) {
                skills.add(skill);
            }
        }
        return skills;
    }

    /**
     * Register skill boolean.
     *
     * @param skillId        the skillID
     * @param name the name
     * @return the boolean
     */
    public boolean RegisterSkill(int skillId, String name) {
        Skill existingSkill = skillsRepository.getSkillByName(name);
        if (existingSkill != null) {
            return false;
        }
        return skillsRepository.add(skillId, name);
    }

    public List<Skill> getSkills() {
        return skillsRepository.getAllSkills();
    }
}