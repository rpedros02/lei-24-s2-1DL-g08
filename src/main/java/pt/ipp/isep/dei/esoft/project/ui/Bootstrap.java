package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
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
                    LocalDate.now().minusYears(i),
                    LocalDate.now().minusYears(i + 5),
                    i * 5000,
                    i * 1000
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
                    LocalDate.now().minusMonths(i),
                    i * 10000
            );
            VehicleCheckupRepository.addVehicleCheckup(vehicleCheckup);
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
            GreenSpace greenSpace = new GreenSpace(name, type, area);
            greenSpaceRepository.addGreenSpace(greenSpace);
        }
    }
    private void addAgendaEntries() {
        ToDoListRepository toDoListRepository = Repositories.getInstance().getToDoListRepository();
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        AgendaRepository agendaRepository = Repositories.getInstance().getAgendaEntryRepository();

        Entry entry1 = new Entry("Entry 1", "Description 1", DegreeOfUrgencyRepository.HIGH, new Date(12,06,2024),new Date(14,06,2024), "In Progress", greenSpaceRepository.getGreenSpaceByName("GreenSpace 1"), , , );
        Entry entry2 = new Entry("Entry 2", "Description 2", DegreeOfUrgencyRepository.MEDIUM,  new Date(22,06,2024),new Date(27,06,2024), "Not Started", greenSpaceRepository.getGreenSpaceByName("GreenSpace 2"), , , );
        Entry entry3 = new Entry("Entry 3", "Description 3", DegreeOfUrgencyRepository.LOW,  new Date(31,06,2024),new Date(14,07,2024), "Completed", greenSpaceRepository.getGreenSpaceByName("GreenSpace 3"), , , );

        ToDoList toDoList = new ToDoList();
        toDoList.addEntry(entry1);
        toDoList.addEntry(entry2);
        toDoList.addEntry(entry3);

        // Add the ToDoList to the ToDoListRepository
        toDoListRepository.addEntryToToDoList(entry1);
        toDoListRepository.addEntryToToDoList(entry2);
        toDoListRepository.addEntryToToDoList(entry3);

        // Create a few agenda entries
        Agenda agenda1 = new Agenda(entry1, null, null);
        Agenda agenda2 = new Agenda(entry2, null, null);
        Agenda agenda3 = new Agenda(entry3, null, null);

        // Add the agenda entries to the AgendaRepository
        agendaRepository.addAgendaEntry(agenda1);
        agendaRepository.addAgendaEntry(agenda2);
        agendaRepository.addAgendaEntry(agenda3);
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