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

/**
 * The GenerateTeamController class is responsible for handling operations related to generating teams.
 * It interacts with the TeamRepository and CollaboratorRepository to perform operations such as retrieving repositories, generating teams, and registering teams.
 */
public class GenerateTeamController {

    /**
     * The Random instance used for generating random numbers.
     */
    private final Random rand = new Random();

    /**
     * The TeamRepository instance.
     * This instance is used to interact with the team repository,
     * allowing the controller to perform operations related to teams.
     */
    private TeamRepository teamRepository;

    /**
     * The CollaboratorRepository instance.
     * This instance is used to interact with the collaborator repository,
     * allowing the controller to perform operations related to collaborators.
     */
    private CollaboratorRepository collaboratorRepository;

    /**
     * The list of Collaborator objects.
     */
    private ArrayList<Collaborator> collaborators;

    /**
     * The default constructor for the GenerateTeamController.
     * It initializes the TeamRepository and CollaboratorRepository instances.
     */
    public GenerateTeamController() {
        getTeamRepository();
        getCollaboratorRepository();
    }

    /**
     * Retrieves the team repository.
     * If the team repository is null, it retrieves the team repository from the repositories.
     */
    private void getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the Job Repository
            teamRepository = repositories.getTeamRepository();
        }
    }

    /**
     * Retrieves the collaborator repository.
     * If the collaborator repository is null, it retrieves the collaborator repository from the repositories.
     *
     * @return The CollaboratorRepository object.
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the Job Repository
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Retrieves a collaborator by their skill name.
     * If a collaborator with the specified skill name is found, it removes the collaborator from the list of collaborators and returns the collaborator.
     * If a collaborator with the specified skill name is not found, it returns an empty Optional.
     *
     * @param skillName The name of the skill.
     * @return An Optional<Collaborator> object. If a collaborator with the specified skill name is found, it contains the Collaborator object. Otherwise, it is empty.
     */
    public Optional<Collaborator> getCollaborator(String skillName) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.hasSkill(skillName)) {
                collaborators.remove(collaborator);
                return Optional.of(collaborator.clone());
            }
        }

        return Optional.empty();
    }

    /**
     * Retrieves a random collaborator from the list of collaborators.
     *
     * @return The Collaborator object representing the random collaborator.
     */
    public Collaborator getRandomCollaborator() {
        return collaborators.get(rand.nextInt(collaborators.size()));
    }

    /**
     * Generates a team.
     * It creates a new Team object with the provided parameters and adds collaborators to the team until the team reaches its maximum number of members or there are no more collaborators.
     * If a collaborator with a required skill is found, it removes the collaborator from the list of collaborators and adds the collaborator to the team.
     * If a collaborator with a required skill is not found, it removes a random collaborator from the list of collaborators and adds the collaborator to the team.
     *
     * @param minMembers The minimum number of members in the team.
     * @param maxMembers The maximum number of members in the team.
     * @param teamSkills The list of skills required in the team.
     * @return The Team object representing the generated team.
     */
    public Team generateTeam(int minMembers, int maxMembers, List<Skill> teamSkills) {

        Team team = new Team(minMembers, maxMembers, teamSkills);
        collaborators = new ArrayList<>(getCollaboratorRepository().getAll());

        int skillIndex = 0;
        while(collaborators.size() >= minMembers) {
            if (team.getNumberOfTeamMembers() < maxMembers) {
                Optional<Collaborator> OptCollaborator = getCollaborator(teamSkills.get(skillIndex).getName());
                if (OptCollaborator.isPresent()) {
                    collaborators.remove(OptCollaborator.get());
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

    /**
     * Registers a team.
     * If the team is successfully added to the team repository, it prints a message indicating that the team was successfully registered.
     * If the team is not successfully added to the team repository, it prints a message indicating that the team was not registered.
     *
     * @param team The Team object to be registered.
     */
    public void registerTeam(Team team) {
        teamRepository.add(team);
    }
}