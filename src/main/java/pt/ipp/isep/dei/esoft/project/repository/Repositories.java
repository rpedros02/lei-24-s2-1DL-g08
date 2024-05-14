package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final SkillsRepository skillsRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleCheckupRepository vehicleCheckupRepository;
    private final JobRepository jobRepository;

    private final CollaboratorRepository collaboratorRepository;
    private final TeamRepository teamRepository;

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
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
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