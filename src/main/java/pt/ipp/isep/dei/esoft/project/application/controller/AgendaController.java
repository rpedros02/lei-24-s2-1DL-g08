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
        organizationRepository = Repositories.getInstance().getOrganizationRepository();
        authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    private Collaborator getEmployeeFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new Collaborator(email);
    }

    public Optional<Entry> createEntry(Entry entry) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        Optional<Entry> newEntry = Optional.empty();

        if (organization.isPresent()) {
            newEntry = organization.get()
                    .addEntryToAgenda(entry);
        }
        return newEntry;
    }

    public boolean addEntry(Entry entry) {
        return !createEntry(entry).equals(Optional.empty());
    }

    public List<Entry> getEntriesBetweenDates(Date dateBegin, Date dateEnd) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        List<Entry> entries = List.of();

        if (organization.isPresent()) {
            entries = organization.get()
                    .getEntriesBetweenDates(dateBegin, dateEnd);
        }
        return entries;
    }
}