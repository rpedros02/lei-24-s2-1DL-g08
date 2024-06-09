package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a TeamRepository in the system.
 * It manages a list of Team objects and provides methods to manipulate and retrieve them.
 */
public class TeamRepository {
    /**
     * The list of Team objects.
     */
    private final List<Team> teams;

    /**
     * Constructs a TeamRepository object.
     * It initializes the teams list as an empty ArrayList.
     */
    public TeamRepository() {
        this.teams = new ArrayList<>();
    }

    /**
     * Adds a Team to the repository.
     *
     * @param team the Team to add
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /**
     * Returns a list of all Team objects in the repository.
     *
     * @return a list of all Team objects
     */
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    /**
     * Validates a Team.
     * It checks if the Team already exists in the list.
     *
     * @param team the Team to validate
     * @return true if the Team is valid (does not exist in the list), false otherwise
     */
    private boolean validateTeam(Team team) {
        return !teams.contains(team);
    }

    /**
     * Adds a Team to the repository if it is valid.
     * If the Team is added successfully, it returns true.
     * If the Team is not valid (already exists in the list), it returns false.
     *
     * @param team the Team to add
     * @return true if the Team was added successfully, false otherwise
     */
    public boolean add(Team team) {
        if (validateTeam(team)) {
            teams.add(team);
            return true;
        }else {
            return false;
        }
    }
}