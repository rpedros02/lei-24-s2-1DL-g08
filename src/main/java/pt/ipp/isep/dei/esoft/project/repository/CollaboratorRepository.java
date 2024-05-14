package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;

import java.util.ArrayList;
import java.util.List;

public class CollaboratorRepository {

    private final List<Collaborator> collaborator;

    public CollaboratorRepository() {
        this.collaborator = new ArrayList<>();
    }

    public boolean addCollaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, int idNumber, Address address, Job job) {
        if (collaborator.isEmpty()) {
            collaborator.add(new Collaborator(name,birthDate,admissionDate,mobileNumber,email,taxPayerNumber,idDocType,idNumber,address,job));
            return true;
        }

        if (!checkIfTheCollaboratorExists(idNumber)) {
            collaborator.add(new Collaborator(name,birthDate,admissionDate,mobileNumber,email,taxPayerNumber,idDocType,idNumber,address,job));
            return true;
        }
        return false;
    }

    public boolean checkIfTheCollaboratorExists(int idNumber) {
        for (Collaborator collaborator : this.collaborator) {
            if (collaborator.getIdNumber() == idNumber) {
                return true;
            }
        }
        return false;
    }

    public boolean assignSkills(Collaborator collaborator, List<Skill> skills) {
        boolean success = false;
        for (Skill s : skills) {
            success = collaborator.addSkill(s);
            if (!success) {
                return false;
            }
        }
        return success;
    }

    public List<Collaborator> getCollaboratorList() {
        return collaborator;
    }
}