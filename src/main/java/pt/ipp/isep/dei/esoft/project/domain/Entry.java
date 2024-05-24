package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Entry {
    private String title;
    private String description;
    private DegreeOfUrgencyRepository degreeOfUrgency;
    private double duration;
    private String status;
    private GreenSpace greenSpace;

    public Task(String title) {
        this.title = title;
    }

    public Task(String title, String description, DegreeOfUrgencyRepository degreeOfUrgency, double duration, String status, GreenSpace greenSpace) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
        this.status = status;
        this.greenSpace = greenSpace;
    }

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

    public double getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDuration(double duration) {
        this.duration = duration;
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

    public boolean validateTask() {
        return !title.isEmpty() && !description.isEmpty() && duration > 0;
    }

    public boolean addTask() {
        return validateTask();
    }

    @Override
    public Task clone() {
        return new Task(title, description, degreeOfUrgency, duration, status, greenSpace);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Double.compare(task.duration, duration) == 0 &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(degreeOfUrgency, task.degreeOfUrgency) &&
                Objects.equals(status, task.status) &&
                Objects.equals(greenSpace, task.greenSpace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, degreeOfUrgency, duration, status, greenSpace);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", degreeOfUrgency='" + degreeOfUrgency + '\'' +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                ", greenSpace='" + greenSpace + '\'' +
                '}';
    }
}
