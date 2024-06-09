package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class represents an Organization in the system.
 * It has several fields including vatNumber, collaborators, tasks, vehicles, agenda, toDoList, name, website, phone, email, and teams.
 */
public class Organization {
    public static Organization instance;
    private final String vatNumber;
    private final List<Collaborator> collaborators;
    private final List<Task> tasks;
    private final VehicleRepository vehicles;
    private final Agenda agenda;
    private final ToDoList toDoList;
    private String name;
    private String website;
    private String phone;
    private String email;
    private final List<Team> teams;

    /**
     * Constructs an Organization object with the specified vatNumber.
     * It also initializes the collaborators, tasks, vehicles, agenda, toDoList, and teams as empty lists.
     *
     * @param vatNumber the vat number of the organization
     */
    public Organization(String vatNumber) {
        this.vatNumber = vatNumber;
        collaborators = new ArrayList<>();
        tasks = new ArrayList<>();
        vehicles = new VehicleRepository();
        agenda = new Agenda();
        toDoList = new ToDoList();
        teams = new ArrayList<>();
    }

    /**
     * Returns the instance of the Organization.
     * If the instance is null, it creates a new Organization with the specified vatNumber.
     *
     * @param vatNumber the vat number of the organization
     * @return the instance of the Organization
     */
    public static Organization getInstance(String vatNumber) {
        if (instance == null) {
            synchronized (Organization.class) {
                instance = new Organization(vatNumber);
            }
        }
        return instance;
    }

    /**
     * Checks if this Organization has the specified collaborator.
     *
     * @param collaborator the collaborator to check
     * @return true if this Organization has the specified collaborator, false otherwise
     */
    public boolean hasCollaborator(Collaborator collaborator) {
        return collaborators.contains(collaborator);
    }

    /**
     * Checks if this Organization has the specified vehicle.
     *
     * @param vehicle the vehicle to check
     * @return true if this Organization has the specified vehicle, false otherwise
     */
    public boolean hasVehicle(Vehicle vehicle) {
        return vehicles.getVehicles().contains(vehicle);
    }

    /**
     * Creates a Task with the specified parameters and adds it to the list of tasks.
     *
     * @param reference the reference of the task
     * @param description the description of the task
     * @param informalDescription the informal description of the task
     * @param technicalDescription the technical description of the task
     * @param duration the duration of the task
     * @param cost the cost of the task
     * @param taskCategory the category of the task
     * @return an Optional containing the created Task if it was added successfully, an empty Optional otherwise
     */
    public Optional<Task> createTask(String reference, String description, String informalDescription,
                                     String technicalDescription, int duration, double cost,
                                     TaskCategory taskCategory) {
        Optional<Task> optionalValue = Optional.empty();

        Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                taskCategory);

        if (addTask(task)) {
            optionalValue = Optional.of(task);
        }
        return optionalValue;
    }

    /**
     * Creates a Vehicle with the specified parameters and adds it to the list of vehicles.
     *
     * @param plateId the plate id of the vehicle
     * @param brand the brand of the vehicle
     * @param model the model of the vehicle
     * @param type the type of the vehicle
     * @param tare the tare of the vehicle
     * @param weight the weight of the vehicle
     * @param mileage the mileage of the vehicle
     * @param lastVehicleCheckupKm the last vehicle checkup km of the vehicle
     * @param register_date the register date of the vehicle
     * @param acquisition_date the acquisition date of the vehicle
     * @param maintenance_frequency the maintenance frequency of the vehicle
     * @return an Optional containing the created Vehicle if it was added successfully, an empty Optional otherwise
     */
    public Optional<Vehicle> createVehicle(String plateId, String brand, String model,
                                           String type, double tare, double weight, int mileage, int lastVehicleCheckupKm,
                                           Date register_date, Date acquisition_date, int maintenance_frequency) {
        Optional<Vehicle> optionalValue = Optional.empty();

        Vehicle vehicle = new Vehicle(plateId, brand, model, type, tare, weight, mileage, lastVehicleCheckupKm, register_date, acquisition_date, maintenance_frequency);
        if (addVehicle(vehicle)) {
            optionalValue = Optional.of(vehicle);
        }
        return optionalValue;
    }

    /**
     * Adds an Entry to the agenda.
     *
     * @param entry the Entry to add
     * @return an Optional containing the added Entry if it was added successfully, an empty Optional otherwise
     */
    public Optional<Entry> addEntryToAgenda(Entry entry) {
        if (agenda.addEntry(entry)) {
            return Optional.of(entry);
        }
        return Optional.empty();
    }

    /**
     * Adds an Entry to the to-do list.
     *
     * @param entry the Entry to add
     * @return an Optional containing the added Entry if it was added successfully, an empty Optional otherwise
     */
    public Optional<Entry> addEntryToToDoList(Entry entry) {
        if (toDoList.addEntry(entry)) {
            return Optional.of(entry);
        }
        return Optional.empty();
    }

    /**
     * Returns the agenda of this Organization.
     *
     * @return the agenda of this Organization
     */
    public Agenda getAgenda() {
        return agenda;
    }

    /**
     * Returns a list of teams in this Organization.
     *
     * @return a list of teams in this Organization
     */
    public List<Team> getTeams() {
        return new ArrayList<>(this.teams);
    }

    /**
     * Returns a list of entries from the to-do list.
     *
     * @return a list of entries from the to-do list
     */
    public List<Entry> getEntriesFromToDoList() {
        return toDoList.getEntries();
    }

    /**
     * Returns an Entry from the to-do list with the specified title.
     *
     * @param entryTitle the title of the Entry
     * @return the Entry with the specified title
     */
    public Entry getEntryFromToDoList(String entryTitle) {
        return toDoList.getEntryByTitle(entryTitle);
    }

    /**
     * Adds a Task to the list of tasks.
     * The Task is validated before being added.
     *
     * @param task the Task to add
     * @return true if the Task was added successfully, false otherwise
     */
    private boolean addTask(Task task) {
        boolean success = false;
        if (validate(task)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = tasks.add(task.clone());
        }
        return success;

    }

    /**
     * Adds a Vehicle to the list of vehicles.
     * The Vehicle is validated before being added.
     *
     * @param vehicle the Vehicle to add
     * @return true if the Vehicle was added successfully, false otherwise
     */
    private boolean addVehicle(Vehicle vehicle) {
        boolean success = false;
        if (validateVehicle(vehicle)) {
            success = vehicles.addVehicle(vehicle.clone());
        }
        return success;

    }

    /**
     * Validates a Task.
     * The Task is valid if it is not already in the list of tasks.
     *
     * @param task the Task to validate
     * @return true if the Task is valid, false otherwise
     */
    private boolean validate(Task task) {
        return tasksDoNotContain(task);
    }

    /**
     * Validates a Vehicle.
     * The Vehicle is valid if it is not already in the list of vehicles.
     *
     * @param vehicle the Vehicle to validate
     * @return true if the Vehicle is valid, false otherwise
     */
    private boolean validateVehicle(Vehicle vehicle) {
        return vehiclesDoNotContain(vehicle);
    }

    /**
     * Checks if a Task is in the list of tasks.
     *
     * @param task the Task to check
     * @return true if the Task is not in the list of tasks, false otherwise
     */
    private boolean tasksDoNotContain(Task task) {
        return !tasks.contains(task);
    }

    /**
     * Checks if a Vehicle is in the list of vehicles.
     *
     * @param vehicle the Vehicle to check
     * @return true if the Vehicle is not in the list of vehicles, false otherwise
     */
    private boolean vehiclesDoNotContain(Vehicle vehicle) {
        return !vehicles.getVehicles().contains(vehicle);
    }

    /**
     * Checks if any collaborator in this Organization has the specified email.
     *
     * @param email the email to check
     * @return true if any collaborator in this Organization has the specified email, false otherwise
     */
    public boolean anyCollaboratorHasEmail(String email) {
        boolean result = false;
        for (Collaborator collaborator : collaborators) {
            if (collaborator.hasEmail(email)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks if this Organization is equal to the specified object.
     * The comparison is based on the vatNumber field.
     *
     * @param o the object to compare with
     * @return true if this Organization is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        Organization that = (Organization) o;
        return vatNumber.equals(that.vatNumber);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(vatNumber);
    }

    /**
     * Adds a Collaborator to the list of collaborators.
     * The Collaborator is validated before being added.
     *
     * @param collaborator the Collaborator to add
     * @return true if the Collaborator was added successfully, false otherwise
     */
    public boolean addCollaborator(Collaborator collaborator) {
        boolean success = false;
        if (validateCollaborator(collaborator)) {
            success = collaborators.add(collaborator);
        }
        return success;
    }

    /**
     * Validates a Collaborator.
     * The Collaborator is valid if it is not already in the list of collaborators.
     *
     * @param collaborator the Collaborator to validate
     * @return true if the Collaborator is valid, false otherwise
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        return collaboratorsDoNotContain(collaborator);
    }

    /**
     * Checks if a Collaborator is in the list of collaborators.
     *
     * @param collaborator the Collaborator to check
     * @return true if the Collaborator is not in the list of collaborators, false otherwise
     */
    private boolean collaboratorsDoNotContain(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * Creates and returns a copy of this Organization.
     * The clone has the same vatNumber, name, website, phone, email, collaborators, and tasks as this Organization.
     *
     * @return a clone of this Organization
     */
    public Organization clone() {
        Organization clone = new Organization(this.vatNumber);
        clone.name = (this.name);
        clone.website = (this.website);
        clone.phone = (this.phone);
        clone.email = (this.email);

        for (Collaborator in : this.collaborators) {
            clone.collaborators.add(in.clone());
        }

        for (Task in : this.tasks) {
            clone.tasks.add(in.clone());
        }

        return clone;
    }

    /**
     * Creates a Collaborator with the specified parameters and adds it to the list of collaborators.
     * If the Collaborator is added successfully, it returns an Optional containing the Collaborator.
     *
     * @param name the name of the Collaborator
     * @param birthDate the birth date of the Collaborator
     * @param admissionDate the admission date of the Collaborator
     * @param mobileNumber the mobile number of the Collaborator
     * @param email the email of the Collaborator
     * @param taxPayerNumber the tax payer number of the Collaborator
     * @param idDocType the id document type of the Collaborator
     * @param idNumber the id number of the Collaborator
     * @param address the address of the Collaborator
     * @param job the job of the Collaborator
     * @param task the task of the Collaborator
     * @return an Optional containing the created Collaborator if it was added successfully, an empty Optional otherwise
     */
    public Optional<Collaborator> createCollaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job, Task task) {
        Optional<Collaborator> optionalValue = Optional.empty();

        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType, idNumber, address, job, task);
        if (addCollaborator(collaborator)) {
            optionalValue = Optional.of(collaborator);
        }
        return optionalValue;
    }

    /**
     * Creates an Entry with the specified parameters and adds it to the to-do list.
     * If the Entry is added successfully, it returns an Optional containing the Entry.
     *
     * @param title the title of the Entry
     * @param description the description of the Entry
     * @param degreeOfUrgency the degree of urgency of the Entry
     * @param dateBegin the date of the beginning of the Entry
     * @param dateEnd the date of the end of the Entry
     * @param status the status of the Entry
     * @param greenSpace the green space of the Entry
     * @param team the team of the Entry
     * @param vehicles the vehicles of the Entry
     * @param task the task of the Entry
     * @return an Optional containing the created Entry if it was added successfully, an empty Optional otherwise
     */
    public Optional<Entry> createEntry(String title, String description, DegreeOfUrgency degreeOfUrgency, Date dateBegin, Date dateEnd, EStatus status, GreenSpace greenSpace, Team team, List<Vehicle> vehicles, Task task) {
        Entry entry = new Entry(title, description, degreeOfUrgency, dateBegin, dateEnd, status, greenSpace, team, vehicles, task);

        if (addEntryToToDoList(entry).isPresent()) {
            return Optional.of(entry);
        }
        return Optional.empty();
    }

    /**
     * Returns a list of Entries from the agenda that are between the specified dates.
     *
     * @param dateBegin the beginning date
     * @param dateEnd the end date
     * @return a list of Entries from the agenda that are between the specified dates
     */
    public List<Entry> getEntriesBetweenDates(Date dateBegin, Date dateEnd) {
        List<Entry> entries = new ArrayList<>();
        for (Entry e : agenda.getEntries()) {
            if (e.getDateBegin().isAfter(dateBegin) || e.getDateBegin().isEqual(dateBegin) && !e.getDateEnd().isAfter(dateEnd) || e.getDateEnd().isEqual(dateEnd)) {
                entries.add(e);
            }
        }
        return entries;
    }

    /**
     * Returns the vat number of this Organization.
     *
     * @return the vat number of this Organization
     */
    public String getVatNumber() {
        return this.vatNumber;
    }

    /**
     * Sets the entries of the agenda to the entries of the specified Agenda.
     *
     * @param agenda1 the Agenda to get the entries from
     */
    public void setAgenda(Agenda agenda1) {
        agenda.setEntries(agenda1.getEntries());
    }

    /**
     * Sets the entries of the to-do list to the entries of the specified ToDoList.
     *
     * @param toDoList the ToDoList to get the entries from
     */
    public void setToDoList(ToDoList toDoList) {
        this.toDoList.setEntries(toDoList.getEntries());
    }

    /**
     * Returns the Collaborator with the specified email.
     * If no Collaborator has the specified email, it returns null.
     *
     * @param mail the email to check
     * @return the Collaborator with the specified email
     */
    public Collaborator getCollaboratorByEmail(String mail) {
        for (Collaborator c : collaborators) {
            if (c.getEmail().equals(mail)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Returns a list of emails of the collaborators in this Organization.
     *
     * @return a list of emails of the collaborators in this Organization
     */
    public List<String> getCollaborators() {
        List<String> collaborators = new ArrayList<>();
        for (Collaborator c : this.collaborators) {
            collaborators.add(c.getEmail());
        }
        return collaborators;
    }

    /**
     * Returns the to-do list of this Organization.
     *
     * @return the to-do list of this Organization
     */
    public ToDoList getTodoList() {
        return toDoList;
    }
}