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

public class CreateCollaboratorController {

    private CollaboratorRepository collaboratorRepository;
    private SkillsRepository skillsRepository;

    public CreateCollaboratorController(){
        getCollaboratorRepository();
    }
    public CreateCollaboratorController(CollaboratorRepository collaboratorRepository){
        this.collaboratorRepository = collaboratorRepository;
    }
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository ==null){
            Repositories repositories= Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }


    public Optional<Collaborator> createCollaborator(String name, LocalDate birthDate, LocalDate admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job, Task task) {
        return Organization.getInstance("000000000").createCollaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType, idNumber, address, job, task);
    }


    /**
     * Method to assign a task to a team
     *
     * @param team Team to assign the task to
     * @param task Task to assign
     */
    public void assignTaskToTeam(TreeSet<Collaborator> team, Task task) {
        collaboratorRepository.assignTaskToTeam(team, task);
    }

    /**
     * Method to get a collaborator by ID document type number
     *
     * @param idDocTypeNumber ID document type number of the collaborator
     * @return The collaborator with the provided ID document type number
     */
    public Collaborator getCollaborator(String idDocTypeNumber) {
        return collaboratorRepository.getCollaboratorByNumber(idDocTypeNumber);
    }

    public void assignSkill(Skill skill, Collaborator collaborator) {
        collaborator.addSkill(skill);
    }

    public List<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaborator();
    }

    public boolean skillExists(String trimmedSkill) {
        return skillsRepository.getSkillByName(trimmedSkill) != null;
    }

    public List<Skill> getAllSkills() {
        return skillsRepository.getAllSkills();
    }
}


