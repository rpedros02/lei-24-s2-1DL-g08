package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addCollaborator();
        addJobs();
        addUsers();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addCollaborator(new Collaborator(new Email("admin@this.app")));
        organization.addCollaborator(new Collaborator(new Email("employee@this.app")));
        organizationRepository.add(organization);
    }

    private void addTaskCategories() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("US12 Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "pwd",
                AuthenticationController.ROLE_HRM);


    }
    private void addSkills() {
        SkillsRepository skillRepository = Repositories.getInstance().getSkillsRepository();

        skillRepository.add(1,"Plant Identification");
        skillRepository.add(2,"Botany Basics");
        skillRepository.add(3,"Herbalism Knowledge");
        skillRepository.add(4,"Floral Arrangement");
        skillRepository.add(5,"Ecological Conservation");
        skillRepository.add(6,"Horticulture Techniques");
        skillRepository.add(7,"Wildlife Habitat Restoration");
        skillRepository.add(8,"Gardening Expertise");
        skillRepository.add(9,"Permaculture Principles");
        skillRepository.add(10,"Arboriculture Proficiency");
        skillRepository.add(11,"Native Plant Recognition");
        skillRepository.add(12,"Soil Analysis Skills");
        skillRepository.add(13,"Plant Taxonomy Understanding");
        skillRepository.add(14,"Urban Green Space Design");
        skillRepository.add(15,"Invasive Species Management");
        skillRepository.add(16,"Ethnobotany Insights");
        skillRepository.add(17,"Medicinal Plant Identification");
        skillRepository.add(18,"Plant Physiology Knowledge");
        skillRepository.add(19,"Organic Farming Practices");
        skillRepository.add(20,"Agroecology Mastery");

    }
    private void addJobs(){
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        
        jobRepository.add("Gardener");
        jobRepository.add("Landscape Designer");
        jobRepository.add("Botanical Garden Curator");
        jobRepository.add("Nursery Worker");
        jobRepository.add("Arborist");
        jobRepository.add("Floral Arranger");
        jobRepository.add("Greenhouse Manager");
        jobRepository.add("Urban Farmer");
        jobRepository.add("Park Ranger");
        jobRepository.add("Horticultural Therapist");
        jobRepository.add("Permaculture Designer");
        jobRepository.add("Groundskeeper");
        jobRepository.add("Plant Biotechnologist");
        jobRepository.add("Community Garden Coordinator");
        jobRepository.add("Herbalist");
        jobRepository.add("Plant Breeder");
        jobRepository.add("Garden Center Sales Associate");
        jobRepository.add("Sustainable Landscaper");
        jobRepository.add("Wildlife Habitat Specialist");
        jobRepository.add("Garden Educator");
        jobRepository.add("Flower Shop Assistant");

    }
    private void addCollaborator() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        collaboratorRepository.addCollaborator("Johnny boy", new Date(13,12,2005), new Date(12,4,1995),919919919,"email@this.app",123456789, IdDocType.CC,"123456789",new Address("rua rua",12,"4425-299","City","District"),new Job("Jardineiro"));

    }
//registei manualmente um colaborador para terem uma ideia para a us3: register a collaborator

}