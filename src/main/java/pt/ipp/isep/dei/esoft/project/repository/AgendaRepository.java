package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository implements Serializable {
    private List<Agenda> agendaEntries;


    public AgendaRepository() {
        this.agendaEntries = new ArrayList<>();
    }

    public boolean addAgendaEntry(Agenda entry) {
        if (agendaEntries.contains(entry))
            return false;
        agendaEntries.add(entry);
        return true;
    }

    public boolean updateAgendaEntry(Agenda agenda, Entry entry, Team team, List<Vehicle> vehicles) {
        if (!agendaEntries.contains(agenda)) {
            return false;
        }

        agenda.setEntry(entry);
        agenda.setTeam(team);
        agenda.setVehicles(vehicles);
        return true;
    }

    public boolean removeAgendaEntry(Agenda agenda) {
        return agendaEntries.remove(agenda);
    }

    public List<Agenda> getAgendaEntries() {
        return agendaEntries;
    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        for (Agenda entry : agendaEntries) {
            if (entry.getTeam() != null) {
                teams.add(entry.getTeam());
            }
        }
        return teams;
    }

    public boolean assignTeamToAgendaEntry(Agenda entry, Team team) {
        if (!agendaEntries.contains(entry)) {
            return false;
        }
        entry.setTeam(team);
        return true;
    }

    public boolean isTaskInAgenda(Task task) {
        for (Agenda entry : agendaEntries) {
            if (entry.getEntry().equals(task)) {
                return true;
            }
        }
        return false;
    }
}