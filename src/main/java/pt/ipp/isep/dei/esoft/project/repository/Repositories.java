package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.isep.lei.esoft.auth.domain.model.Email;

public class Repositories {

    private static Repositories instance;
    private OrganizationRepository organizationRepository;
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

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public static Agenda getAgenda() {
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        if(organizationRepository == null) {
            organizationRepository = new OrganizationRepository();
            return new Agenda();
        }
        Agenda agenda = organizationRepository.getOrganizationByEmployeeEmail(getInstance().getEmployeeEmailFromSession()).getAgenda();
        if(agenda == null)
            return new Agenda();
        return new Agenda(agenda);
    }

    private String getEmployeeEmailFromSession() {
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }

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