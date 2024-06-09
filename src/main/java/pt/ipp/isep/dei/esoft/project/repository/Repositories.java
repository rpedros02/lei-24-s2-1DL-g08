package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;

/**
 * This class serves as a central point for accessing all repositories in the system.
 * It follows the Singleton design pattern to ensure only one instance of this class exists in the system.
 */
public class Repositories {

    /**
     * The singleton instance of the Repositories class.
     */
    private static Repositories instance;

    /**
     * The repositories managed by this class.
     */
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final SkillsRepository skillsRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleCheckupRepository vehicleCheckupRepository;
    private final JobRepository jobRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final TeamRepository teamRepository;
    private final ToDoListRepository toDoListRepository;
    private final GreenSpaceRepository greenSpaceRepository;

    /**
     * Private constructor to prevent instantiation from outside this class.
     * Initializes all repositories.
     */
    private Repositories() {
        this.jobRepository = new JobRepository();
        this.collaboratorRepository = new CollaboratorRepository();
        this.organizationRepository = new OrganizationRepository();
        this.taskCategoryRepository = new TaskCategoryRepository();
        this.authenticationRepository = new AuthenticationRepository();
        this.skillsRepository = new SkillsRepository();
        this.vehicleRepository = new VehicleRepository();
        this.vehicleCheckupRepository = new VehicleCheckupRepository();
        this.teamRepository = new TeamRepository();
        this.greenSpaceRepository = new GreenSpaceRepository();
        this.toDoListRepository = new ToDoListRepository();
    }

    /**
     * Returns the singleton instance of the Repositories class.
     * If the instance is null, it initializes it.
     *
     * @return the singleton instance of the Repositories class
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    /**
     * Returns the Agenda of the current user's organization.
     * If the organization or the agenda does not exist, it returns a new Agenda.
     *
     * @return the Agenda of the current user's organization
     */
    public static Agenda getAgenda() {
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        if(organizationRepository == null) {
            return new Agenda();
        }
        Agenda agenda = organizationRepository.getOrganizationByEmployeeEmail(getInstance().getEmployeeEmailFromSession()).getAgenda();
        if(agenda == null)
            return new Agenda();
        return new Agenda(agenda);
    }

    /**
     * Returns the email of the current user from the session.
     *
     * @return the email of the current user
     */
    private String getEmployeeEmailFromSession() {
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }

    /**
     * Getter methods for all repositories.
     */
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    public ToDoListRepository getToDoListRepository() {
        return toDoListRepository;
    }

    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public SkillsRepository getSkillsRepository() {
        return skillsRepository;
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    public VehicleCheckupRepository getVehicleCheckupRepository() {
        return vehicleCheckupRepository;
    }

    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }
}