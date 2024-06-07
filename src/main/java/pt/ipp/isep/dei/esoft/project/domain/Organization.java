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

    /**
     * This method is the constructor of the organization.
     *
     * @param vatNumber The vat number of the organization. This is the identity of the organization, therefore it
     *                  cannot be changed.
     */
    public Organization(String vatNumber) {
        this.vatNumber = vatNumber;
        collaborators = new ArrayList<>();
        tasks = new ArrayList<>();
        vehicles = new VehicleRepository();
        agenda = new Agenda();
        toDoList = new ToDoList();
    }

    public static Organization getInstance(String vatNumber) {
        if (instance == null) {
            synchronized (Organization.class) {
                instance = new Organization(vatNumber);
            }
        }
        return instance;
    }


    /**
     * This method checks if an collaborator works for the organization.
     *
     * @param collaborator The collaborator to be checked.
     * @return True if the collaborator works for the organization.
     */
    public boolean hasCollaborator(Collaborator collaborator) {
        return collaborators.contains(collaborator);
    }

    public boolean hasVehicle(Vehicle vehicle) {
        return vehicles.getVehicles().contains(vehicle);
    }


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

    public Optional<Entry> addEntryToAgenda(Entry entry) {
        if (agenda.addEntry(entry)) {
            return Optional.of(entry);
        }
        return Optional.empty();
    }

    public Optional<Entry> addEntryToToDoList(Entry entry) {
        if (toDoList.addEntry(entry)) {
            return Optional.of(entry);
        }
        return Optional.empty();
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public List<Entry> getEntriesFromToDoList() {
        return toDoList.getEntries();
    }

    /**
     * @param entryTitle The title of the entry to be retrieved.
     *                   This method retrieves an entry from the to do list.
     *                   The entry is identified by its title.
     *                   The entry is retrieved from the to do list.
     *                   The entry is returned.
     * @return The entry that was retrieved.
     */
    public Entry getEntryFromToDoList(String entryTitle) {
        return toDoList.getEntryByTitle(entryTitle);
    }

    /**
     * This method adds a task to the list of tasks.
     *
     * @param task The task to be added.
     * @return True if the task was added successfully.
     */
    private boolean addTask(Task task) {
        boolean success = false;
        if (validate(task)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = tasks.add(task.clone());
        }
        return success;

    }

    private boolean addVehicle(Vehicle vehicle) {
        boolean success = false;
        if (validateVehicle(vehicle)) {
            success = vehicles.addVehicle(vehicle.clone());
        }
        return success;

    }


    /**
     * This method validates the task, checking for duplicates.
     *
     * @param task The task to be validated.
     * @return True if the task is valid.
     */
    private boolean validate(Task task) {
        return tasksDoNotContain(task);
    }

    private boolean validateVehicle(Vehicle vehicle) {
        return vehiclesDoNotContain(vehicle);
    }

    /**
     * This method checks if the task is already in the list of tasks.
     *
     * @param task The task to be checked.
     * @return True if the task is not in the list of tasks.
     */
    private boolean tasksDoNotContain(Task task) {
        return !tasks.contains(task);
    }

    private boolean vehiclesDoNotContain(Vehicle vehicle) {
        return !vehicles.getVehicles().contains(vehicle);
    }

    /**
     * This methos checks if the organization has an collaborator with the given email.
     *
     * @param email The email to be checked.
     * @return True if the organization has an employee with the given email.
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

    @Override
    public int hashCode() {
        return Objects.hash(vatNumber);
    }

    //add employee to organization
    public boolean addCollaborator(Collaborator collaborator) {
        boolean success = false;
        if (validateCollaborator(collaborator)) {
            success = collaborators.add(collaborator);
        }
        return success;
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        return collaboratorsDoNotContain(collaborator);
    }

    private boolean collaboratorsDoNotContain(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    //Clone organization
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

    public Optional<Collaborator> createCollaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job, Task task) {
        Optional<Collaborator> optionalValue = Optional.empty();

        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType, idNumber, address, job, task);
        if (addCollaborator(collaborator)) {
            optionalValue = Optional.of(collaborator);
        }
        return optionalValue;
    }

    public Optional<Entry> createEntry(String title, String description, DegreeOfUrgency degreeOfUrgency, Date dateBegin, Date dateEnd, EStatus status, GreenSpace greenSpace, Team team, List<Vehicle> vehicles, Task task) {
        Entry entry = new Entry(title, description, degreeOfUrgency, dateBegin, dateEnd, status, greenSpace, team, vehicles, task);

        if (addEntryToToDoList(entry).isPresent()) {
            return Optional.of(entry);
        }
        return Optional.empty();
    }

    public List<Entry> getEntriesBetweenDates(Date dateBegin, Date dateEnd) {
        List<Entry> entries = new ArrayList<>();
        for (Entry e : agenda.getEntries()) {
            if (e.getDateBegin().isAfter(dateBegin) || e.getDateBegin().isEqual(dateBegin) && !e.getDateEnd().isAfter(dateEnd) || e.getDateEnd().isEqual(dateEnd)) {
                entries.add(e);
            }
        }
        return entries;
    }

    public String getVatNumber() {
        return this.vatNumber;
    }

    public void setAgenda(Agenda agenda1) {
        agenda.setEntries(agenda1.getEntries());
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList.setEntries(toDoList.getEntries());
    }

    public Collaborator getCollaboratorByEmail(String mail) {
        for (Collaborator c : collaborators) {
            if (c.getEmail().equals(mail)) {
                return c;
            }
        }
        return null;
    }

    public List<String> getCollaborators() {
        List<String> collaborators = new ArrayList<>();
        for (Collaborator c : this.collaborators) {
            collaborators.add(c.getEmail());
        }
        return collaborators;
    }
}