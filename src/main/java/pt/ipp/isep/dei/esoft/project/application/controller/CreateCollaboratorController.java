package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
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


    public Optional<Collaborator> createCollaborator(String name, String admission_data, String birthdate, String address, int mobileNumber, String email, int taxpayer_number, int IDNumber, IdDocType IDType) {
        return Organization.getInstance("000000000").createCollaborator( name,  admission_data,  birthdate,  address,  mobileNumber, email, taxpayer_number, IDNumber,  IDType);
    }
}
