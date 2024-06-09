package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

/**
 * The AgendaController class is responsible for handling operations related to the agenda.
 * It interacts with the OrganizationRepository and AuthenticationRepository to perform operations such as creating, adding, and retrieving entries.
 */
public class AgendaController {

    /**
     * The OrganizationRepository instance.
     * This instance is used to interact with the organization repository,
     * allowing the controller to perform operations related to the organization.
     */
    private final OrganizationRepository organizationRepository;

    /**
     * The AuthenticationRepository instance.
     * This instance is used to interact with the authentication repository,
     * allowing the controller to perform operations related to the authentication.
     */
    private final AuthenticationRepository authenticationRepository;

    /**
     * The constructor for the AgendaController.
     * It initializes the OrganizationRepository and AuthenticationRepository instances.
     */
    public AgendaController() {
        this.organizationRepository = Repositories.getInstance().getOrganizationRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Retrieves the collaborator from the current session.
     *
     * @return A Collaborator object representing the collaborator of the current session.
     */
    private Collaborator getEmployeeFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new Collaborator(email);
    }

    /**
     * Creates an entry in the agenda.
     *
     * @param entry The Entry object to be created.
     * @return An Optional<Entry> object. If the entry is successfully created, it contains the created Entry object. Otherwise, it is empty.
     */
    public Optional<Entry> createEntry(Entry entry) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        if (organization.isPresent()) {
            Organization org = organization.get();
            return org.addEntryToAgenda(entry);
        }
        return Optional.empty();
    }

    /**
     * Adds an entry to the agenda.
     *
     * @param entry The Entry object to be added.
     * @return A boolean indicating the success of the operation. If the entry is successfully added, it returns true. Otherwise, it returns false.
     */
    public boolean addEntry(Entry entry) {
        return createEntry(entry).isPresent();
    }

    /**
     * Retrieves the entries between the specified dates.
     *
     * @param dateBegin The start date.
     * @param dateEnd The end date.
     * @return A list of Entry objects representing the entries between the specified dates.
     */
    public List<Entry> getEntriesBetweenDates(Date dateBegin, Date dateEnd) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.map(org -> org.getEntriesBetweenDates(dateBegin, dateEnd)).orElse(List.of());
    }

    /**
     * Retrieves the entry with the specified title.
     *
     * @param entryTitle The title of the entry.
     * @return An Optional<Entry> object. If an entry with the specified title exists, it contains the Entry object. Otherwise, it is empty.
     */
    public Optional<Entry> getEntryByTitle(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.flatMap(org -> {
            Agenda agenda = org.getAgenda();
            return Optional.ofNullable(agenda.getEntryByTitle(entryTitle));
        });
    }

    /**
     * Checks if an entry with the specified title exists.
     *
     * @param entryTitle The title of the entry.
     * @return A boolean indicating whether an entry with the specified title exists.
     */
    public boolean exists(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.map(org -> {
            Agenda agenda = org.getAgenda();
            return agenda.getEntryByTitle(entryTitle) != null;
        }).orElse(false);
    }

    /**
     * Assigns a team to the entry with the specified title.
     *
     * @param entryTitle The title of the entry.
     * @param team The Team object to be assigned.
     * @return A boolean indicating the success of the operation. If the team is successfully assigned, it returns true. Otherwise, it returns false.
     */
    public boolean assignTeamToEntry(String entryTitle, Team team) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.map(org -> {
            Agenda agenda = org.getAgenda();
            Entry entry = agenda.getEntryByTitle(entryTitle);
            if (entry != null) {
                entry.setTeam(team);
                return true;
            }
            return false;
        }).orElse(false);
    }

    /**
     * Postpones the entry with the specified title to the new date.
     *
     * @param entryTitle The title of the entry.
     * @param newDate The new date.
     * @return A boolean indicating the success of the operation. If the entry is successfully postponed, it returns true. Otherwise, it returns false.
     */
    public boolean postponeEntry(String entryTitle, Date newDate) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.map(org -> {
            Agenda agenda = org.getAgenda();
            Entry entry = agenda.getEntryByTitle(entryTitle);
            if (entry != null) {
                entry.setDateBegin(newDate);
                entry.setDateEnd(newDate);
                return true;
            }
            return false;
        }).orElse(false);
    }

    /**
     * Cancels the entry with the specified title.
     *
     * @param entryTitle The title of the entry.
     * @return A boolean indicating the success of the operation. If the entry is successfully cancelled, it returns true. Otherwise, it returns false.
     */
    public boolean cancelEntry(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.map(org -> {
            Agenda agenda = org.getAgenda();
            return agenda.cancelEntry(entryTitle);
        }).orElse(false);
    }
}