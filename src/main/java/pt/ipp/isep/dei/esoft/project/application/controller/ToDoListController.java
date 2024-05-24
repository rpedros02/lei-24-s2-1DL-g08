package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;
import pt.ipp.isep.dei.esoft.project.repository.DegreeOfUrgencyRepository;

import java.util.List;
import java.util.Optional;

public class ToDoListController {
    private ToDoListRepository toDoListRepository;
    private OrganizationRepository organizationRepository;
    private AuthenticationRepository authenticationRepository;

    public ToDoListController() {
        if (toDoListRepository == null) {
            toDoListRepository = Repositories.getInstance().getToDoListRepository();
        }
        organizationRepository = Repositories.getInstance().getOrganizationRepository();
        authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    public boolean addEntryToToDoList(Entry entry) {
        return toDoListRepository.addEntryToToDoList(entry);
    }

    public Optional<Entry> createEntry(String title, String description, DegreeOfUrgencyRepository degreeOfUrgency, double duration, String status, GreenSpace greenSpace) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);

        Optional<Entry> newEntry = Optional.empty();

        if (organization.isPresent()) {
            newEntry = organization.get()
                    .createEntry(title, description, degreeOfUrgency, duration, status, greenSpace);
        }
        return newEntry;
    }

    private Collaborator getEmployeeFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new Collaborator(email.getEmail());
    }

    public List<Entry> getEntries() {
        return toDoListRepository.getToDoList().getEntries();
    }


}
