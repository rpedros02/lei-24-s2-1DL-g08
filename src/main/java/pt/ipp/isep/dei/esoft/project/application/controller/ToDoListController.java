package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class ToDoListController {

    private final OrganizationRepository organizationRepository;
    private final AuthenticationRepository authenticationRepository;

    public ToDoListController() {
        organizationRepository = Repositories.getInstance().getOrganizationRepository();
        authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    public Optional<Entry> createEntry(String title, String description, DegreeOfUrgency degreeOfUrgency, Date dateBegin, Date dateEnd, EStatus status, GreenSpace greenSpace, Team team, List<Vehicle> vehicles, Task task) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        if (team == null) {
            System.out.println("Team is null.");
            return Optional.empty();
        }

        Entry entry = new Entry(title, description, degreeOfUrgency, dateBegin, dateEnd, status, greenSpace, team, vehicles, task);
        Optional<Entry> newEntryToDoList = Optional.empty();

        if (organization.isPresent()) {
            newEntryToDoList = organization.get().addEntryToToDoList(entry);
        }
        return newEntryToDoList;
    }

    public boolean exists(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        return organization.get().getEntriesFromToDoList().contains(getEntry(entryTitle));
    }

    private Entry getEntry(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        return organization.get().getEntryFromToDoList(entryTitle);
    }

    public Entry getToDoListEntry(String entryTitle) {
        return getEntry(entryTitle);
    }

    public boolean addEntry(Entry entry) {
        return !createEntry(entry.getTitle(), entry.getDescription(), entry.getDegreeOfUrgency(), entry.getDateBegin(), entry.getDateEnd(), entry.getStatus(), entry.getGreenSpace(), entry.getTeam(), entry.getVehicles(), entry.getTask()).equals(Optional.empty());
    }

    private Collaborator getEmployeeFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new Collaborator(email);
    }

    public Optional<Team> getTeam(String teamName) {
        Optional<Team> teamOpt = organizationRepository.getTeamByName(teamName);
        return teamOpt;
    }


    public Optional<List<String>> getMembers(String teamName) {
        Optional<Team> teamOpt = getTeam(teamName);
        if (teamOpt.isPresent()) {
            Team team = teamOpt.get();
            List<String> members = team.getMembers();
            return Optional.of(members);
        } else {
            return Optional.empty();
        }
    }

    public boolean updateStatus(Entry selectedEntry, EStatus eStatus) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        if (organization.isPresent()) {
            return organization.get().updateStatus(selectedEntry, eStatus);
        }
        return false;
    }
}
