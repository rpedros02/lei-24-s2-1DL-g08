package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.util.List;

/**
 * The AssignSkillController class is responsible for handling operations related to assigning skills.
 * It interacts with the SkillsRepository and CollaboratorRepository to perform operations such as retrieving lists of skills and collaborators, and assigning skills to collaborators.
 */
public class AssignSkillController {
    /**
     * The SkillsRepository instance.
     * This instance is used to interact with the skills repository,
     * allowing the controller to perform operations related to skills.
     */
    private final SkillsRepository skillsRepository;

    /**
     * The CollaboratorRepository instance.
     * This instance is used to interact with the collaborator repository,
     * allowing the controller to perform operations related to collaborators.
     */
    private final CollaboratorRepository collaboratorRepository;

    /**
     * The constructor for the AssignSkillController.
     * It initializes the SkillsRepository and CollaboratorRepository instances.
     */
    public AssignSkillController() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        skillsRepository = repositories.getSkillsRepository();
    }

    /**
     * Retrieves the list of collaborators.
     *
     * @return A list of Collaborator objects.
     */
    public List<Collaborator> getCollabortorList() {
        return collaboratorRepository.getCollaborator();
    }

    /**
     * Retrieves the list of skills.
     *
     * @return A list of Skill objects.
     */
    public List<Skill> getSkillList() {
        return skillsRepository.getSkillsList();
    }

    /**
     * Assigns a list of skills to a collaborator.
     *
     * @param collaborator The Collaborator object to which the skills are to be assigned.
     * @param skills The list of Skill objects to be assigned.
     */
    public void assignSkills(Collaborator collaborator, List<Skill> skills) {
        collaboratorRepository.assignSkills(collaborator, skills);
    }

    /**
     * Retrieves a collaborator by their email.
     *
     * @param value The email of the collaborator.
     * @return A Collaborator object representing the collaborator with the specified email.
     */
    public Collaborator getCollaboratorByEmail(String value) {
        return collaboratorRepository.getCollaboratorByEmail(value);
    }

    /**
     * Retrieves a skill by its name.
     *
     * @param value The name of the skill.
     * @return A Skill object representing the skill with the specified name.
     */
    public Skill getSkillByName(String value) {
        return skillsRepository.getSkillByName(value);
    }
}