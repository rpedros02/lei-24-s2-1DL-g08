package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

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

    public boolean addTaskToToDoList(Entry entry) {
        return toDoListRepository.addTaskToToDoList(task);
    }

    public Optional<Task> createTask(String title, String description, DegreeOfUrgencyRepository degreeOfUrgency, double duration, String status, GreenSpace greenSpace) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByEmployee(collaborator);

        Optional<Task> newTask = Optional.empty();

        if (organization.isPresent()) {
            newTask = organization.get()
                    .createTask(title, description, degreeOfUrgency, duration, status, greenSpace);
        }
        return newTask;
    }

    private Collaborator getEmployeeFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new Collaborator(email.getEmail());
    }

    public List<Task> getTasks() {
        return toDoListRepository.getToDoList().getTasks();
    }


}
