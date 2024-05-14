package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;


public class TeamRepository {
    private final List<Team> teams;

    public TeamRepository() {
        this.teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    private boolean validateTeam(Team team) {
        return !teams.contains(team);
    }

    public boolean add(Team team) {
        if (validateTeam(team)) {
            teams.add(team);
            return true;
        }else {
            return false;
        }
    }
}
