package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class AgendaController {

    private final OrganizationRepository organizationRepository;
    private final AuthenticationRepository authenticationRepository;

    public AgendaController() {
        this.organizationRepository = Repositories.getInstance().getOrganizationRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    private Collaborator getEmployeeFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new Collaborator(email);
    }

    public Optional<Entry> createEntry(Entry entry) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        if (organization.isPresent()) {
            Organization org = organization.get();
            return org.addEntryToAgenda(entry);
        }
        return Optional.empty();
    }

    public boolean addEntry(Entry entry) {
        return createEntry(entry).isPresent();
    }

    public List<Entry> getEntriesBetweenDates(Date dateBegin, Date dateEnd) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.map(org -> org.getEntriesBetweenDates(dateBegin, dateEnd)).orElse(List.of());
    }

    public Optional<Entry> getEntryByTitle(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.flatMap(org -> {
            Agenda agenda = org.getAgenda();
            return Optional.ofNullable(agenda.getEntryByTitle(entryTitle));
        });
    }

    public boolean exists(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.map(org -> {
            Agenda agenda = org.getAgenda();
            return agenda.getEntryByTitle(entryTitle) != null;
        }).orElse(false);
    }

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


    public boolean cancelEntry(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        return organization.map(org -> {
            Agenda agenda = org.getAgenda();
            return agenda.cancelEntry(entryTitle);
        }).orElse(false);
    }


}
