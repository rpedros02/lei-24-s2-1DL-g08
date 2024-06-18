package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;
import java.util.Random;

/**
 * This class is responsible for initializing the application with some default data.
 */
public class Bootstrap implements Runnable {

    /**
     * This method is called when the application starts.
     * It initializes the application with some default data.
     */
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addCollaborator();
        addSkills();
        addJobs();
        addUsers();
        addVehicle();
        addGreenSpaces();
        addAgendaEntries();
    }

    /**
     * This method adds some default vehicles to the application.
     */
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
                    new Date(12, 6, 2024),
                    new Date(12, 6, 2024),
                    i * 5000
            );
            vehicleRepository.addVehicle(vehicle);
            addVehicleMaintenance(vehicle);
        }
    }

    /**
     * This method generates a random plate ID.
     * @param random a Random instance
     * @return a random plate ID
     */
    private String generatePlateId(Random random) {
        return String.format("%c%c-%02d-%02d",
                randomChar(random), randomChar(random),
                random.nextInt(100), random.nextInt(100));
    }

    /**
     * This method adds some default vehicle checkups to the application.
     */
    private void addVehicleMaintenance(Vehicle vehicle) {
        VehicleCheckupRepository checkupRepository = Repositories.getInstance().getVehicleCheckupRepository();
        for (int i = 1; i <= 3; i++) {
            String vehicleId = vehicle.getPlateId();
            VehicleCheckup vehicleCheckup = new VehicleCheckup(
                    vehicleId,
                    new Date(12, 6, 2024),
                    i * 10000
            );
            checkupRepository.addVehicleCheckup(vehicleCheckup);
        }
    }

    /**
     * This method generates a random character.
     * @param random a Random instance
     * @return a random character
     */
    private char randomChar(Random random) {
        return (char) ('A' + random.nextInt(26));
    }

    /**
     * This method adds some default green spaces to the application.
     */
    private void addGreenSpaces() {
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();

        for (int i = 0; i < 10; i++) {
            String name = "GreenSpace " + (i + 1);
            Optional<Organization> optionalOrganization = Repositories.getInstance().getOrganizationRepository().getOrganizationByVatNumber("505244123");
            if (optionalOrganization.isPresent()) {
                Organization organization = optionalOrganization.get();
                GreenSpaceTypeRepository type = GreenSpaceTypeRepository.GARDEN;
                double area = i * 100 + 100;

                Address address = new Address("Rua Green", i + 1, "4100-100", "Porto", "Porto");

                GreenSpace greenSpace = new GreenSpace(name, type, area, address, organization.getCollaboratorByEmail("gsm@this.app"));
                greenSpaceRepository.addGreenSpace(greenSpace);
            }
        }
    }

    /**
     * This method adds some default agenda entries to the application.
     */
    private void addAgendaEntries() {
        ToDoListRepository toDoListRepository = Repositories.getInstance().getToDoListRepository();
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        Optional<Organization> optionalOrganization = Repositories.getInstance().getOrganizationRepository().getOrganizationByVatNumber("505244123");

        Entry entry1 = new Entry("Entry 1", "Description 1", DegreeOfUrgency.HIGH, new Date(12, 6, 2024), new Date(14, 6, 2024), EStatus.PLANNED, greenSpaceRepository.getGreenSpaceByName("GreenSpace 1"));
        Entry entry2 = new Entry("Entry 2", "Description 2", DegreeOfUrgency.MEDIUM, new Date(22, 6, 2024), new Date(27, 6, 2024), EStatus.PLANNED, greenSpaceRepository.getGreenSpaceByName("GreenSpace 2"));
        Entry entry3 = new Entry("Entry 3", "Description 3", DegreeOfUrgency.LOW, new Date(25, 6, 2024), new Date(14, 7, 2024), EStatus.PLANNED, greenSpaceRepository.getGreenSpaceByName("GreenSpace 3"));

        ToDoList toDoList = toDoListRepository.getToDoList();
        toDoList.addEntry(entry1);
        toDoList.addEntry(entry2);
        toDoList.addEntry(entry3);

        Agenda agenda = new Agenda();
        if (optionalOrganization.isPresent()) {
            agenda = optionalOrganization.get().getAgenda();
            if (!agenda.addEntry(entry1) || !agenda.addEntry(entry2) || !agenda.addEntry(entry3)) {
                System.out.println("Bootstrap: Failed to add entries to the agenda");
            }
        }


        if (optionalOrganization.isPresent()) {
            Organization organization = optionalOrganization.get();
            organization.setAgenda(agenda);
            organization.setToDoList(toDoList);
        } else {
            System.out.println("Bootstrap: Organization not found by VAT number");
        }
    }

    /**
     * This method adds a default organization to the application.
     */
    private void addOrganization() {
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("505244123");
        organization.addCollaborator(new Collaborator(new Email("admin@this.app")));
        organization.addCollaborator(new Collaborator("Employee", new Date(12, 4, 2002), new Date(23, 12, 2023), 919919919, "employee@this.app", 123456780, IdDocType.CC, "123456780", new Address("rua rua", 12, "4425-299", "City", "District"), new Job("Employee"), new Task("Task")));
        organization.addCollaborator(new Collaborator("GSM", new Date(12, 4, 2002), new Date(23, 12, 2023), 919919919, "gsm@this.app", 123456781, IdDocType.CC, "123456781", new Address("rua rua", 12, "4425-299", "City", "District"), new Job("GSM"), new Task("Task")));
        organization.addCollaborator(new Collaborator("HRM", new Date(12, 4, 2002), new Date(23, 12, 2023), 919919919, "hrm@this.app", 123456782, IdDocType.CC, "123456782", new Address("rua rua", 12, "4425-299", "City", "District"), new Job("HRM"), new Task("Task")));
        organization.addCollaborator(new Collaborator("VFM", new Date(12, 4, 2002), new Date(23, 12, 2023), 919919919, "vfm@this.app", 123456783, IdDocType.CC, "123456783", new Address("rua rua", 12, "4425-299", "City", "District"), new Job("VFM"), new Task("Task")));
        organizationRepository.add(organization);
    }

    /**
     * This method adds some default task categories to the application.
     */
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

    /**
     * This method adds some default users to the application.
     */
    private void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);

        authenticationRepository.addUserWithRole("Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "hrm",
                AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "vfm",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("GSM", "gsm@this.app", "gsm",
                AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserWithRole("COLLABORATOR", "collaborator@this.app", "collaborator",
                AuthenticationController.ROLE_COLLABORATOR);
    }

    /**
     * This method adds some default skills to the application.
     */
    private void addSkills() {
        SkillsRepository skillRepository = Repositories.getInstance().getSkillsRepository();

        skillRepository.add(1, "Plant Identification");
        skillRepository.add(2, "Botany Basics");
        skillRepository.add(3, "Herbalism Knowledge");
        skillRepository.add(4, "Floral Arrangement");
        skillRepository.add(5, "Ecological Conservation");
        skillRepository.add(6, "Horticulture Techniques");
        skillRepository.add(7, "Wildlife Habitat Restoration");
        skillRepository.add(8, "Gardening Expertise");
        skillRepository.add(9, "Permaculture Principles");
        skillRepository.add(10, "Arboriculture Proficiency");
        skillRepository.add(11, "Native Plant Recognition");
        skillRepository.add(12, "Soil Analysis Skills");
        skillRepository.add(13, "Plant Taxonomy Understanding");
        skillRepository.add(14, "Urban Green Space Design");
        skillRepository.add(15, "Invasive Species Management");
        skillRepository.add(16, "Ethnobotany Insights");
        skillRepository.add(17, "Medicinal Plant Identification");
        skillRepository.add(18, "Plant Physiology Knowledge");
        skillRepository.add(19, "Organic Farming Practices");
        skillRepository.add(20, "Agroecology Mastery");

    }

    /**
     * This method adds some default jobs to the application.
     */
    private void addJobs() {
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

    /**
     * This method adds some default collaborators to the application.
     */
    private void addCollaborator() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        collaboratorRepository.addCollaborator(new Collaborator("Johny boy", new Date(12, 04, 2002), new Date(23, 12, 2023), 919919919, "email@this.app", 123456789, IdDocType.CC, "123456789", new Address("rua rua", 12, "4425-299", "City", "District"), new Job("Gardener"), new Task("Task")));
        collaboratorRepository.addCollaborator(new Collaborator("Johny boy", new Date(12, 04, 2002), new Date(23, 12, 2023), 919919919, "email@this.app", 123456789, IdDocType.CC, "123456789", new Address("rua rua", 12, "4425-299", "City", "District"), new Job("Gardener"), new Task("Task")));
        collaboratorRepository.addCollaborator(new Collaborator("Johny girl", new Date(12, 04, 2002), new Date(23, 12, 2023), 919919919, "email@this.app", 123456789, IdDocType.CC, "123456789", new Address("rua rua", 12, "4425-299", "City", "District"), new Job("Gardener"), new Task("Task")));
        collaboratorRepository.addCollaborator(new Collaborator("Johny girl", new Date(12, 04, 2002), new Date(23, 12, 2023), 919919919, "email@this.app", 123456789, IdDocType.CC, "123456789", new Address("rua rua", 12, "4425-299", "City", "District"), new Job("Gardener"), new Task("Task")));
    }
}
