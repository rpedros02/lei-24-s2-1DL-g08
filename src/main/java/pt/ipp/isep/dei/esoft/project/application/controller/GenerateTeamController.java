package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GenerateTeamController {

    private final Random rand = new Random();
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;
    private ArrayList<Collaborator> collaborators;

    public GenerateTeamController() {
        getTeamRepository();
        getCollaboratorRepository();
    }
    public List<Team> getTeamList() {
        return teamRepository.getTeams();
    }

    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the Job Repository
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the Job Repository
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }


    public Optional<Collaborator> getCollaborator(String skillName) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.hasSkill(skillName)) {
                collaborators.remove(collaborator);
                return Optional.of(collaborator.clone());
            }
        }

        return Optional.empty();
    }

    public Collaborator getRandomCollaborator() {
        return collaborators.get(rand.nextInt(collaborators.size()));
    }


    public Team generateTeam(int minMembers, int maxMembers, List<Skill> teamSkills) {

        Team team = new Team(minMembers, maxMembers, teamSkills);
        collaborators = new ArrayList<>(getCollaboratorRepository().getCollaboratorList());

        int skillIndex = 0;
        while(collaborators.size() >= minMembers) {
            if (team.getNumberOfTeamMembers() < maxMembers) {
                Optional<Collaborator> OptCollaborator = getCollaborator(teamSkills.get(skillIndex).getName());
                if (OptCollaborator.isPresent()) {
                    collaborators.remove(OptCollaborator);
                    team.addTeamMember(OptCollaborator.get());
                } else {
                    Collaborator collaborator = getRandomCollaborator();
                    collaborators.remove(collaborator);
                    team.addTeamMember(collaborator);
                }
            }
        }

        return team;
    }
}
