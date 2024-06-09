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

/**
 * The ToDoListController class is responsible for handling operations related to a To-Do List.
 * It interacts with the OrganizationRepository and AuthenticationRepository to perform operations such as creating an entry, checking if an entry exists, getting an entry, adding an entry, getting a team, and getting members of a team.
 */
public class ToDoListController {

    /**
     * The OrganizationRepository instance.
     * This instance is used to interact with the organization repository,
     * allowing the controller to perform operations related to organizations.
     */
    private final OrganizationRepository organizationRepository;

    /**
     * The AuthenticationRepository instance.
     * This instance is used to interact with the authentication repository,
     * allowing the controller to perform operations related to authentication.
     */
    private final AuthenticationRepository authenticationRepository;

    /**
     * The default constructor for the ToDoListController.
     * It initializes the OrganizationRepository and AuthenticationRepository instances.
     */
    public ToDoListController() {
        organizationRepository = Repositories.getInstance().getOrganizationRepository();
        authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Creates an entry.
     * It creates a new Entry object with the provided parameters and adds it to the To-Do List of the organization of the collaborator who is currently in session.
     *
     * @param title The title of the entry.
     * @param description The description of the entry.
     * @param degreeOfUrgency The degree of urgency of the entry.
     * @param dateBegin The start date of the entry.
     * @param dateEnd The end date of the entry.
     * @param status The status of the entry.
     * @param greenSpace The green space of the entry.
     * @param team The team of the entry.
     * @param vehicles The vehicles of the entry.
     * @param task The task of the entry.
     * @return An Optional containing the new Entry object if it was successfully added to the To-Do List, or an empty Optional otherwise.
     */
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

    /**
     * Checks if an entry exists.
     * It checks if an entry with the provided title exists in the To-Do List of the organization of the collaborator who is currently in session.
     *
     * @param entryTitle The title of the entry.
     * @return A boolean indicating whether the entry exists.
     */
    public boolean exists(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        return organization.get().getEntriesFromToDoList().contains(getEntry(entryTitle));
    }

    /**
     * Retrieves an entry.
     * It retrieves an entry with the provided title from the To-Do List of the organization of the collaborator who is currently in session.
     *
     * @param entryTitle The title of the entry.
     * @return The Entry object.
     */
    private Entry getEntry(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        return organization.get().getEntryFromToDoList(entryTitle);
    }

    /**
     * Retrieves a To-Do List entry.
     * It retrieves a To-Do List entry with the provided title.
     *
     * @param entryTitle The title of the entry.
     * @return The Entry object.
     */
    public Entry getToDoListEntry(String entryTitle) {
        return getEntry(entryTitle);
    }

    /**
     * Adds an entry.
     * It adds the provided Entry object to the To-Do List of the organization of the collaborator who is currently in session.
     *
     * @param entry The Entry object.
     * @return A boolean indicating the success of the operation.
     */
    public boolean addEntry(Entry entry) {
        return !createEntry(entry.getTitle(), entry.getDescription(), entry.getDegreeOfUrgency(), entry.getDateBegin(), entry.getDateEnd(), entry.getStatus(), entry.getGreenSpace(), entry.getTeam(), entry.getVehicles(), entry.getTask()).equals(Optional.empty());
    }

    /**
     * Retrieves the collaborator who is currently in session.
     *
     * @return The Collaborator object.
     */
    private Collaborator getEmployeeFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new Collaborator(email);
    }

    /**
     * Retrieves a team.
     * It retrieves a team with the provided name from the organization repository.
     *
     * @param teamName The name of the team.
     * @return An Optional containing the Team object if it exists, or an empty Optional otherwise.
     */
    public Optional<Team> getTeam(String teamName) {
        Optional<Team> teamOpt = organizationRepository.getTeamByName(teamName);
        return teamOpt;
    }

    /**
     * Retrieves the members of a team.
     * It retrieves the members of a team with the provided name.
     *
     * @param teamName The name of the team.
     * @return An Optional containing a list of the members if the team exists, or an empty Optional otherwise.
     */
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

    public boolean updateStatus(Entry selectedEntry, EStatus eStatus)
    {
        return selectedEntry.updateStatus(eStatus);
    }
}
