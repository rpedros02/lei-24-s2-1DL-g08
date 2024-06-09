package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.util.List;

public class AssignSkillController {
    private final  SkillsRepository skillsRepository;
    private final CollaboratorRepository collaboratorRepository;

    public AssignSkillController() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        skillsRepository = repositories.getSkillsRepository();

    }

    public List<Collaborator> getCollabortorList() {
        return collaboratorRepository.getCollaborator();
    }

    public List<Skill> getSkillList() {
        return skillsRepository.getSkillsList();
    }


    public void assignSkills(Collaborator collaborator, List<Skill> skills) {
        collaboratorRepository.assignSkills(collaborator, skills);
    }

    public Collaborator getCollaboratorByEmail(String value) {
        return collaboratorRepository.getCollaboratorByEmail(value);
    }

    public Skill getSkillByName(String value) {
        return skillsRepository.getSkillByName(value);
    }
}