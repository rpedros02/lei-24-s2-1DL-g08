package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskCategoryRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

/**
 * The CreateTaskController class is responsible for handling operations related to creating tasks.
 * It interacts with the OrganizationRepository, TaskCategoryRepository, and AuthenticationRepository to perform operations such as retrieving repositories, creating tasks, and retrieving task categories.
 */
public class  CreateTaskController {

    /**
     * The OrganizationRepository instance.
     * This instance is used to interact with the organization repository,
     * allowing the controller to perform operations related to organizations.
     */
    private OrganizationRepository organizationRepository;

    /**
     * The TaskCategoryRepository instance.
     * This instance is used to interact with the task category repository,
     * allowing the controller to perform operations related to task categories.
     */
    private TaskCategoryRepository taskCategoryRepository;

    /**
     * The AuthenticationRepository instance.
     * This instance is used to interact with the authentication repository,
     * allowing the controller to perform operations related to authentication.
     */
    private AuthenticationRepository authenticationRepository;

    /**
     * The default constructor for the CreateTaskController.
     * It initializes the OrganizationRepository, TaskCategoryRepository, and AuthenticationRepository instances.
     */
    public CreateTaskController() {
        getOrganizationRepository();
        getTaskCategoryRepository();
        getAuthenticationRepository();
    }

    /**
     * The constructor for the CreateTaskController with OrganizationRepository, TaskCategoryRepository, and AuthenticationRepository parameters.
     * It initializes the OrganizationRepository, TaskCategoryRepository, and AuthenticationRepository instances with the provided repositories.
     *
     * @param organizationRepository The OrganizationRepository object.
     * @param taskCategoryRepository The TaskCategoryRepository object.
     * @param authenticationRepository The AuthenticationRepository object.
     */
    public CreateTaskController(OrganizationRepository organizationRepository,
                                TaskCategoryRepository taskCategoryRepository,
                                AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.taskCategoryRepository = taskCategoryRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the task category repository.
     * If the task category repository is null, it retrieves the task category repository from the repositories.
     *
     * @return The TaskCategoryRepository object.
     */
    private TaskCategoryRepository getTaskCategoryRepository() {
        if (taskCategoryRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            taskCategoryRepository = repositories.getTaskCategoryRepository();
        }
        return taskCategoryRepository;
    }

    /**
     * Retrieves the organization repository.
     * If the organization repository is null, it retrieves the organization repository from the repositories.
     *
     * @return The OrganizationRepository object.
     */
    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

    /**
     * Retrieves the authentication repository.
     * If the authentication repository is null, it retrieves the authentication repository from the repositories.
     *
     * @return The AuthenticationRepository object.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Creates a task.
     * It creates a new Task object with the provided parameters and adds it to the organization.
     * If the organization is present, it returns the created Task object wrapped in an Optional. Otherwise, it returns an empty Optional.
     *
     * @param reference The reference of the task.
     * @param description The description of the task.
     * @param informalDescription The informal description of the task.
     * @param technicalDescription The technical description of the task.
     * @param duration The duration of the task.
     * @param cost The cost of the task.
     * @param taskCategoryDescription The description of the task category.
     * @return An Optional<Task> object. If the task is successfully created, it contains the created Task object. Otherwise, it is empty.
     */
    public Optional<Task> createTask(String reference, String description, String informalDescription,
                                     String technicalDescription, int duration, double cost,
                                     String taskCategoryDescription) {

        TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

        Collaborator collaborator = getCollaboratorFromSession();
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByCollaborator(collaborator);

        Optional<Task> newTask = Optional.empty();

        if (organization.isPresent()) {
            newTask = organization.get()
                    .createTask(reference, description, informalDescription, technicalDescription, duration, cost,
                            taskCategory);
        }
        return newTask;
    }

    /**
     * Retrieves a collaborator from the current user session.
     *
     * @return The Collaborator object representing the collaborator from the current user session.
     */
    private Collaborator getCollaboratorFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Collaborator(email);
    }

    /**
     * Retrieves a task category by its description.
     *
     * @param taskCategoryDescription The description of the task category.
     * @return The TaskCategory object representing the task category with the specified description.
     */
    private TaskCategory getTaskCategoryByDescription(String taskCategoryDescription) {
        TaskCategoryRepository taskCategoryRepository = getTaskCategoryRepository();

        //Get the TaskCategory by its description
        TaskCategory taskCategoryByDescription =
                getTaskCategoryRepository().getTaskCategoryByDescription(taskCategoryDescription);
        return taskCategoryByDescription;

    }

    /**
     * Retrieves the list of task categories.
     *
     * @return A list of TaskCategory objects.
     */
    public List<TaskCategory> getTaskCategories() {
        TaskCategoryRepository taskCategoryRepository = getTaskCategoryRepository();
        return taskCategoryRepository.getTaskCategories();
    }
}