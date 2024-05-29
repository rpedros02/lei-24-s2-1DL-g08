package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.DegreeOfUrgencyRepository;

import java.util.List;
import java.util.Objects;

public class Entry {
    private String title;
    private String description;
    private DegreeOfUrgencyRepository degreeOfUrgency;
    private Date dateBegin;
    private Date dateEnd;
    private String status;
    private GreenSpace greenSpace;
    private Team team;
    private List<Vehicle> vehicles;
    private Task task;

    //CONSTRUCTOR(S)
    public Entry(String title) {
        this.title = title;
    }

    public Entry(String title, String description, DegreeOfUrgencyRepository degreeOfUrgency, Date dateBegin, Date dateEnd, String status, GreenSpace greenSpace, Team team, List<Vehicle> vehicles, Task task) {
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
    //END CONSTRUCTOR(S)

    // GETTERS AND SETTERS
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public DegreeOfUrgencyRepository getDegreeOfUrgency() {
        return degreeOfUrgency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDegreeOfUrgency(DegreeOfUrgencyRepository degreeOfUrgency) {
        this.degreeOfUrgency = degreeOfUrgency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //END GETTERS AND SETTERS


    //  VALIDATIONS
    public boolean validateEntry() {
        return !title.isEmpty() && !description.isEmpty();
    }
    //  END VALIDATIONS
    @Override
    public Entry clone() {
        return new Entry(title, description, degreeOfUrgency, dateBegin,dateEnd, status, greenSpace, , , );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return  Objects.equals(title, entry.title) &&
                Objects.equals(description, entry.description) &&
                Objects.equals(degreeOfUrgency, entry.degreeOfUrgency) &&
                Objects.equals(status, entry.status) &&
                Objects.equals(greenSpace, entry.greenSpace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, degreeOfUrgency, dateBegin,dateEnd, status, greenSpace);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", degreeOfUrgency='" + degreeOfUrgency + '\'' +
                ", dateBegin=" + dateBegin.toString() +
                ", dateEnd=" + dateEnd.toString() +
                ", status='" + status + '\'' +
                ", greenSpace='" + greenSpace + '\'' +
                '}';
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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
}
