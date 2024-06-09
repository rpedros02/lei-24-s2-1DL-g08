package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The SkillController class is responsible for handling operations related to skills.
 * It interacts with the SkillsRepository to perform operations such as displaying skills, getting skills by names, registering a skill, and getting all skills.
 * This class implements Serializable, which means it can be serialized to a byte stream and restored from a byte stream.
 */
public class SkillController implements Serializable {

    /**
     * The SkillsRepository instance.
     * This instance is used to interact with the skills repository,
     * allowing the controller to perform operations related to skills.
     */
    private SkillsRepository skillsRepository;

    /**
     * The default constructor for the SkillController.
     * It initializes the SkillsRepository instance.
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
     * Retrieves skills by their names.
     *
     * @param skillNames The names of the skills.
     * @return The list of skills with the specified names.
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
     * Registers a skill.
     * If a skill with the provided name already exists, it returns false.
     * Otherwise, it adds a new skill with the provided skill ID and name to the skills repository and returns true.
     *
     * @param skillId The ID of the skill.
     * @param name The name of the skill.
     * @return A boolean indicating the success of the operation.
     */
    public boolean RegisterSkill(int skillId, String name) {
        Skill existingSkill = skillsRepository.getSkillByName(name);
        if (existingSkill != null) {
            return false;
        }
        return skillsRepository.add(skillId, name);
    }

    /**
     * Retrieves all skills.
     *
     * @return The list of all skills.
     */
    public List<Skill> getSkills() {
        return skillsRepository.getAllSkills();
    }
}