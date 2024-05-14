package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

public class CreateCollaboratorController {

    private CollaboratorRepository collaboratorRepository;

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


    public Optional<Collaborator> createCollaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job) {
        return Organization.getInstance("000000000").createCollaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType, idNumber, address, job);
    }
}
