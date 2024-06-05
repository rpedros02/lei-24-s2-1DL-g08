package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Random;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addCollaborator();
        addSkills();
        addJobs();
        addUsers();
        addVehicle();
        addVehicleMaintenance();
        addGreenSpaces();
        addAgendaEntries();
    }
    private void addVehicle() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            String plateId = generatePlateId(random);
            Vehicle vehicle = new Vehicle(
                    plateId,
                    "Brand" + i,
                    "Model" + i,
                    "Type" + i,
                    i * 1000,
                    i * 2000,
                    i * 10000,
                    i * 5000,
                    new Date(12,6,2024),
                    new Date(12,6,2024),
                    i * 5000
            );
            vehicleRepository.addVehicle(vehicle);
        }
    }
    private String generatePlateId(Random random) {
        return String.format("%c%c-%02d-%02d",
                randomChar(random), randomChar(random),
                random.nextInt(100), random.nextInt(100));
    }
    private void addVehicleMaintenance() {
        VehicleCheckupRepository checkupRepository = Repositories.getInstance().getVehicleCheckupRepository();
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            String vehicleId = generatePlateId(random);
            VehicleCheckup vehicleCheckup = new VehicleCheckup(
                    vehicleId,
                    new Date(12,6,2024),
                    i * 10000
            );
            checkupRepository.addVehicleCheckup(vehicleCheckup);
        }
    }
    private char randomChar(Random random) {
        return (char) ('A' + random.nextInt(26));
    }
    private void addGreenSpaces() {
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();

        for (int i = 0; i < 10; i++) {
            String name = "GreenSpace " + (i + 1);
            GreenSpaceTypeRepository type = GreenSpaceTypeRepository.GARDEN;
            Double area = (double) (i*100 + 100);

            Address address = new Address("Rua Green", i, "4100-100", "Porto", "Porto");

            GreenSpace greenSpace = new GreenSpace(name, type, area, address);
            greenSpaceRepository.addGreenSpace(greenSpace);
        }
    }
    private void addAgendaEntries() {
        ToDoListRepository toDoListRepository = Repositories.getInstance().getToDoListRepository();
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();

        Entry entry1 = new Entry("Entry 1", "Description 1", DegreeOfUrgency.HIGH, new Date(12,6,2024),new Date(14,6,2024), EStatus.PENDING, greenSpaceRepository.getGreenSpaceByName("GreenSpace 1"));
        Entry entry2 = new Entry("Entry 2", "Description 2", DegreeOfUrgency.MEDIUM,  new Date(22,6,2024),new Date(27,6,2024), EStatus.PENDING, greenSpaceRepository.getGreenSpaceByName("GreenSpace 2"));
        Entry entry3 = new Entry("Entry 3", "Description 3", DegreeOfUrgency.LOW,  new Date(25,6,2024),new Date(14,7,2024), EStatus.FINISHED, greenSpaceRepository.getGreenSpaceByName("GreenSpace 3"));

        ToDoList toDoList = new ToDoList();
        toDoList.addEntry(entry1);
        toDoList.addEntry(entry2);
        toDoList.addEntry(entry3);

        // Create a few agenda entries
        Agenda agenda1 = new Agenda();
        // Add the agenda entries to the AgendaRepository
        if(!agenda1.addEntry(entry1) ||!agenda1.addEntry(entry2) || !agenda1.addEntry(entry3)) {
            System.out.println("Bootstrap: Failed to add entries to the agenda");
        }
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = organizationRepository.getOrganizationByName("505244123");
        organization.setAgenda(agenda1);
        organization.setToDoList(toDoList);
    }

    private void addOrganization() {
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("505244123");
        organization.addCollaborator(new Collaborator(new Email("admin@this.app")));
        organization.addCollaborator(new Collaborator("Employee", new Date(13,12,2005), new Date(12,4,1995),919919919,"employee@this.app",123456780, IdDocType.CC,"123456780",new Address("rua rua",12,"4425-299","City","District"),new Job("Employee")));
        organization.addCollaborator(new Collaborator("GSM", new Date(13,12,2005), new Date(12,4,1995),919919919,"gsm@this.app",123456781, IdDocType.CC,"123456781",new Address("rua rua",12,"4425-299","City","District"),new Job("GSM")));
        organization.addCollaborator(new Collaborator("HRM", new Date(13,12,2005), new Date(12,4,1995),919919919,"hrm@this.app",123456782, IdDocType.CC,"123456782",new Address("rua rua",12,"4425-299","City","District"),new Job("HRM")));
        organizationRepository.add(organization);
    }

    private void addTaskCategories() {
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
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("US12 Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "hrm",
                AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "vfm",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("GSM", "gsm@this.app", "gsm",
                AuthenticationController.ROLE_GSM);


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
        collaboratorRepository.addCollaborator("Johnny boy", new Date(13,12,2005), new Date(12,4,1995),919919919,"email@this.app",123456789, IdDocType.CC,"123456789",new Address("rua rua",12,"4425-299","City","District"),new Job("Gardener"));
        collaboratorRepository.addCollaborator("Johnny boy", new Date(13,12,2005), new Date(12,4,1995),919919919,"email@this.app",123456789, IdDocType.CC,"123456789",new Address("rua rua",12,"4425-299","City","District"),new Job("Gardener"));

    }


}