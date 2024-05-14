package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.util.List;

public class AssignSkillController {
    private SkillsRepository skillsRepository;
    private CollaboratorRepository collaboratorRepository;

    public AssignSkillController() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        skillsRepository = repositories.getSkillsRepository();

    }

    public List<Collaborator> getCollabortorList() {
        return collaboratorRepository.getCollaboratorList();
    }

    public List<Skill> getSkillList() {
        return skillsRepository.getSkillsList();
    }

    public boolean assignSkills(Collaborator nameOfCollaborator, List<Skill> LitsOfSkills) {
        return collaboratorRepository.assignSkills(nameOfCollaborator, LitsOfSkills);

    }

    public boolean checkIfTheCollaboratorExists(int idNumber) {
        return collaboratorRepository.checkIfTheCollaboratorExists(idNumber);
    }


}