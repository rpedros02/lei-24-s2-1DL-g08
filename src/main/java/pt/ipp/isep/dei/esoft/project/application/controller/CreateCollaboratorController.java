package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

/**
 * The CreateCollaboratorController class is responsible for handling operations related to creating and managing collaborators.
 * It interacts with the CollaboratorRepository and SkillsRepository to perform operations such as creating collaborators, assigning tasks to teams, retrieving collaborators by ID document type number, assigning skills to collaborators, and retrieving lists of collaborators and skills.
 */
public class CreateCollaboratorController {

    /**
     * The CollaboratorRepository instance.
     * This instance is used to interact with the collaborator repository,
     * allowing the controller to perform operations related to collaborators.
     */
    private CollaboratorRepository collaboratorRepository;

    /**
     * The SkillsRepository instance.
     * This instance is used to interact with the skills repository,
     * allowing the controller to perform operations related to skills.
     */
    private SkillsRepository skillsRepository;

    /**
     * The default constructor for the CreateCollaboratorController.
     * It initializes the CollaboratorRepository instance.
     */
    public CreateCollaboratorController(){
        getCollaboratorRepository();
    }

    /**
     * The constructor for the CreateCollaboratorController with a CollaboratorRepository parameter.
     * It initializes the CollaboratorRepository instance with the provided CollaboratorRepository.
     *
     * @param collaboratorRepository The CollaboratorRepository object.
     */
    public CreateCollaboratorController(CollaboratorRepository collaboratorRepository){
        this.collaboratorRepository = collaboratorRepository;
    }

    /**
     * Retrieves the collaborator repository.
     * If the collaborator repository is null, it retrieves the collaborator repository from the repositories.
     *
     * @return The CollaboratorRepository object.
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository ==null){
            Repositories repositories= Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Creates a collaborator.
     *
     * @param name The name of the collaborator.
     * @param birthDate The birth date of the collaborator.
     * @param admissionDate The admission date of the collaborator.
     * @param mobileNumber The mobile number of the collaborator.
     * @param email The email of the collaborator.
     * @param taxPayerNumber The tax payer number of the collaborator.
     * @param idDocType The ID document type of the collaborator.
     * @param idNumber The ID number of the collaborator.
     * @param address The address of the collaborator.
     * @param job The job of the collaborator.
     * @param task The task of the collaborator.
     * @return An Optional<Collaborator> object. If the collaborator is successfully created, it contains the created Collaborator object. Otherwise, it is empty.
     */
    public Optional<Collaborator> createCollaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job, Task task) {
        return Organization.getInstance("000000000").createCollaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType, idNumber, address, job, task);
    }

    /**
     * Assigns a task to a team.
     *
     * @param team The TreeSet<Collaborator> object representing the team to which the task is to be assigned.
     * @param task The Task object to be assigned.
     */
    public void assignTaskToTeam(TreeSet<Collaborator> team, Task task) {
        collaboratorRepository.assignTaskToTeam(team, task);
    }

    /**
     * Retrieves a collaborator by their ID document type number.
     *
     * @param idDocTypeNumber The ID document type number of the collaborator.
     * @return The Collaborator object representing the collaborator with the specified ID document type number.
     */
    public Collaborator getCollaborator(String idDocTypeNumber) {
        return collaboratorRepository.getCollaboratorByNumber(idDocTypeNumber);
    }

    /**
     * Assigns a skill to a collaborator.
     *
     * @param skill The Skill object to be assigned.
     * @param collaborator The Collaborator object to which the skill is to be assigned.
     */
    public void assignSkill(Skill skill, Collaborator collaborator) {
        collaborator.addSkill(skill);
    }

    /**
     * Retrieves the list of collaborators.
     *
     * @return A list of Collaborator objects.
     */
    public List<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaborator();
    }

    /**
     * Checks if a skill exists by its name.
     *
     * @param trimmedSkill The name of the skill.
     * @return A boolean indicating whether a skill with the specified name exists.
     */
    public boolean skillExists(String trimmedSkill) {
        return skillsRepository.getSkillByName(trimmedSkill) != null;
    }

    /**
     * Retrieves the list of all skills.
     *
     * @return A list of Skill objects.
     */
    public List<Skill> getAllSkills() {
        return skillsRepository.getAllSkills();
    }
}


