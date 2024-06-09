package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents an Entry in the system.
 * It has several fields including title, description, degreeOfUrgency, dateBegin, dateEnd, status, greenSpace, team, vehicles, task, and assignedTeam.
 */
public class Entry {
    private String title;
    private String description;
    private DegreeOfUrgency degreeOfUrgency;
    private Date dateBegin;
    private Date dateEnd;
    private EStatus status;
    private GreenSpace greenSpace;
    private Team team;
    private List<Vehicle> vehicles;
    private Task task;
    private Team assignedTeam;

    /**
     * Constructs an Entry object with the specified title.
     *
     * @param title the title of the entry
     */
    public Entry(String title) {
        this.title = title;
    }

    /**
     * Constructs an Entry object with the specified title, description, degreeOfUrgency, dateBegin, dateEnd,
     * status, greenSpace, team, vehicles, and task.
     * If the entry is not valid, it does not set the fields.
     *
     * @param title           the title of the entry
     * @param description     the description of the entry
     * @param degreeOfUrgency the degree of urgency of the entry
     * @param dateBegin       the date of the beginning of the entry
     * @param dateEnd         the date of the end of the entry
     * @param status          the status of the entry
     * @param greenSpace      the green space of the entry
     * @param team            the team of the entry
     * @param vehicles        the vehicles of the entry
     * @param task            the task of the entry
     */
    public Entry(String title, String description, DegreeOfUrgency degreeOfUrgency, Date dateBegin, Date dateEnd,
                 EStatus status, GreenSpace greenSpace, Team team, List<Vehicle> vehicles, Task task) {
        if (isValid(title, description, degreeOfUrgency, dateBegin, dateEnd, status, greenSpace, team, vehicles, task)) {
            setTitle(title);
            setDescription(description);
            setDegreeOfUrgency(degreeOfUrgency);
            setDateBegin(dateBegin);
            setDateEnd(dateEnd);
            setStatus(status);
            setGreenSpace(greenSpace);
            setTeam(team);
            setVehicles(vehicles);
            setTask(task);
        }
    }

    /**
     * Constructs an Entry object with the specified title, description, degreeOfUrgency, dateBegin, dateEnd,
     * status, and greenSpace.
     * If the entry is not valid, it throws an IllegalArgumentException.
     *
     * @param title                  the title of the entry
     * @param description            the description of the entry
     * @param degreeOfUrgency        the degree of urgency of the entry
     * @param dateBegin              the date of the beginning of the entry
     * @param dateEnd                the date of the end of the entry
     * @param status                 the status of the entry
     * @param greenSpace             the green space of the entry
     */
    public Entry(String title, String description, DegreeOfUrgency degreeOfUrgencyRepository, Date dateBegin, Date dateEnd,
                 EStatus status, GreenSpace greenSpace) {
        boolean valid = isValid(title, description, degreeOfUrgencyRepository, dateBegin, dateEnd, status, greenSpace, null, null, null);
        if (!valid) {
            throw new IllegalArgumentException("Invalid Entry");
        }
        setTitle(title);
        setDescription(description);
        setDegreeOfUrgency(degreeOfUrgencyRepository);
        setDateBegin(dateBegin);
        setDateEnd(dateEnd);
        setStatus(status);
        setGreenSpace(greenSpace);
        this.team = null;
        this.vehicles = new ArrayList<>();
        this.task = null;
    }

    /**
     * Returns the team assigned to this Entry.
     *
     * @return the team assigned to this Entry
     */
    public Team getAssignedTeam() {
        return assignedTeam;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DegreeOfUrgency getDegreeOfUrgency() {
        return degreeOfUrgency;
    }

    public void setDegreeOfUrgency(DegreeOfUrgency degreeOfUrgency) {
        this.degreeOfUrgency = degreeOfUrgency;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin != null ? dateBegin.clone() : null;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd != null ? dateEnd.clone() : null;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public void setAssignedTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public List<Skill> getSkills() {
        if (this.task != null) {
            if (this.task.getSkills() != null) {
                return this.task.getSkills();
            } else {
                throw new IllegalStateException("Entry does not have any skills associated with its task.");
            }
        } else {
            throw new IllegalStateException("Entry does not have a task associated with it.");
        }
    }
    public void updateDates(Date newBeginDate, Date newEndDate) {
        this.dateBegin = newBeginDate;
        this.dateEnd = newEndDate;
    }
    //END GETTERS AND SETTERS


    // VALIDATIONS
    /**
     * Checks if the specified title, description, degreeOfUrgency, dateBegin, dateEnd, status, greenSpace, team, vehicles, and task form a valid Entry.
     *
     * @param title           the title of the entry
     * @param description     the description of the entry
     * @param degreeOfUrgency the degree of urgency of the entry
     * @param dateBegin       the date of the beginning of the entry
     * @param dateEnd         the date of the end of the entry
     * @param status          the status of the entry
     * @param greenSpace      the green space of the entry
     * @param team            the team of the entry
     * @param vehicles        the vehicles of the entry
     * @param task            the task of the entry
     * @return true if the entry is valid, false otherwise
     */
    private boolean isValid(String title, String description, DegreeOfUrgency degreeOfUrgency, Date dateBegin, Date dateEnd,
                            EStatus status, GreenSpace greenSpace, Team team, List<Vehicle> vehicles, Task task) {
        boolean validTitle = validateTitle(title);
        boolean validDescription = validateDescription(description);
        boolean validDegreeOfUrgency = validateDegreeOfUrgency(degreeOfUrgency);
        boolean validDateBegin = dateBegin != null;
        boolean validDateEnd = dateEnd != null;
        boolean validStatus = status != null;
        boolean validGreenSpace = greenSpace != null;
        return validTitle && validDescription && validDegreeOfUrgency && validDateBegin && validDateEnd && validStatus && validGreenSpace;
    }

    /**
     * Checks if the specified title is valid.
     * The title must contain only letters and spaces and have a maximum length of 25 characters.
     *
     * @param title the title of the entry
     * @return true if the title is valid, false otherwise
     */
    private boolean validateTitle(String title) {
        return title.length() < 25;
    }

    /**
     * Checks if the specified description is valid.
     * The description must contain only letters and spaces and have a maximum length of 255 characters.
     *
     * @param description the description of the entry
     * @return true if the description is valid, false otherwise
     */
    private boolean validateDescription(String description) {
        return description != null && description.length() <= 255;
    }


    /**
     * Checks if the specified degreeOfUrgency is valid.
     * The degreeOfUrgency must not be null.
     *
     * @param degreeOfUrgency the degree of urgency of the entry
     * @return true if the degree of urgency is valid, false otherwise
     */
    private boolean validateDegreeOfUrgency(DegreeOfUrgency degreeOfUrgency) {
        return degreeOfUrgency != null;
    }
    // END VALIDATIONS

    /**
     * Creates and returns a copy of this Entry.
     *
     * @return a clone of this instance
     */
    @Override
    public Entry clone() {
        return new Entry(this.title, this.description, this.degreeOfUrgency, this.dateBegin, this.dateEnd, this.status,
                this.greenSpace, this.team, this.vehicles, this.task);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Objects.equals(title, entry.title) &&
                Objects.equals(description, entry.description) &&
                Objects.equals(degreeOfUrgency, entry.degreeOfUrgency) &&
                Objects.equals(status, entry.status) &&
                Objects.equals(greenSpace, entry.greenSpace);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, description, degreeOfUrgency, dateBegin, dateEnd, status, greenSpace);
    }

    /**
     * Postpones the end date of this Entry.
     *
     * @param newDate the new end date
     */
    public void postponeEntry(Date newDate) {
        if (newDate == null) {
            return;
        }
        this.dateEnd = newDate;
    }

    /**
     * Adds a vehicle to this Entry.
     *
     * @param vehicle the vehicle to be added
     */
    public void addVehicle(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return STR."Entry{title='\{title}', description='\{description}', degreeOfUrgency=\{degreeOfUrgency.toString()}, dateBegin=\{dateBegin.toString()}, dateEnd=\{dateEnd.toString()}, status=\{status.toString()}, greenSpace=\{greenSpace.toString()}}";
    }
}