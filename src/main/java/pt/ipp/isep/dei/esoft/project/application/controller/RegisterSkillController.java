package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

/**
 * The RegisterSkillController class is responsible for handling operations related to registering skills.
 * It interacts with the SkillsRepository to perform operations such as registering a skill, checking if a skill ID exists, and getting the next skill ID.
 */
public class RegisterSkillController {

    /**
     * The SkillsRepository instance.
     * This instance is used to interact with the skills repository,
     * allowing the controller to perform operations related to skills.
     */
    private final SkillsRepository skillsRepository;

    /**
     * The default constructor for the RegisterSkillController.
     * It initializes the SkillsRepository instance.
     */
    public RegisterSkillController() {
        this.skillsRepository = Repositories.getInstance().getSkillsRepository();
    }

    /**
     * Registers a skill.
     * It adds a new skill with the provided skill ID and name to the skills repository.
     *
     * @param skillId The ID of the skill.
     * @param name The name of the skill.
     * @return A boolean indicating the success of the operation.
     */
    public boolean registerSkill(int skillId, String name) {
        return skillsRepository.add(skillId, name);
    }

    /**
     * Checks if a skill ID exists.
     * It checks if a skill with the provided skill ID exists in the skills repository.
     *
     * @param skillId The ID of the skill.
     * @return A boolean indicating whether the skill ID exists.
     */
    public boolean skillIdExists(int skillId) {
        return skillsRepository.exists(skillId);
    }

    /**
     * Retrieves the next skill ID.
     * It retrieves the next skill ID from the skills repository.
     *
     * @return The next skill ID.
     */
    public int getNextSkillId() {
        return skillsRepository.getNextId();
    }
}