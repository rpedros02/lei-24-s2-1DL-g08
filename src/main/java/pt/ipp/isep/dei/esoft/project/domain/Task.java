package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {

    private String reference;
    private String description;
    private String informalDescription;
    private String technicalDescription;
    private int duration;
    private double cost;
    private TaskCategory taskCategory;
    private List<Skill> skills;

    public Task(String reference,
                String description,
                String informalDescription,
                String technicalDescription,
                int duration,
                double cost,
                TaskCategory taskCategory) {
        validateReference(reference);
        setReference(reference);
        setDescription(description);
        setInformalDescription(informalDescription);
        setTechnicalDescription(technicalDescription);
        setDuration(duration);
        setCost(cost);
        setTaskCategory(taskCategory);
        setSkills(skills);
    }

    public Task() {
        this.skills = new ArrayList<>();
    }

    public Task(String s) {
        this();
        validateReference(reference);
        setReference(reference);
    }

    // VALIDATIONS

    /**
     * @param reference Reference of the task.
     *                  It cannot be null.
     *                  It must be unique.
     *                  It must be alphanumeric.
     *                  It must be between 4 and 10 characters.
     */
    private void validateReference(String reference) {
        if (reference == null || reference.length() < 4 || reference.length() > 10 || !reference.matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Reference cannot be null or empty.");
        }
    }
    // END VALIDATIONS

    // GETTERS & SETTERS
    public String getReference() {
        return reference;
    }

    private void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformalDescription() {
        return informalDescription;
    }

    public void setInformalDescription(String informalDescription) {
        this.informalDescription = informalDescription;
    }

    public String getTechnicalDescription() {
        return technicalDescription;
    }

    public void setTechnicalDescription(String technicalDescription) {
        this.technicalDescription = technicalDescription;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }

    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    public void setSkills(List<Skill> skills) {
        this.skills = new ArrayList<>(skills); //
    }

    // END GETTERS & SETTERS

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return reference.equals(task.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Task clone() {
        return new Task(this.reference, this.description, this.informalDescription, this.technicalDescription,
                this.duration, this.cost, this.taskCategory);
    }
}