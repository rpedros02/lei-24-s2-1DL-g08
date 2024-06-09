package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.List;
import java.util.Objects;

/**
 * This class represents a Task in the system.
 * It has several fields including reference, description, informalDescription, technicalDescription, duration, cost, taskCategory, and skills.
 */
public class Task {

    /**
     * The reference of the Task.
     */
    private String reference;

    /**
     * The description of the Task.
     */
    private String description;

    /**
     * The informal description of the Task.
     */
    private String informalDescription;

    /**
     * The technical description of the Task.
     */
    private String technicalDescription;

    /**
     * The duration of the Task.
     */
    private int duration;

    /**
     * The cost of the Task.
     */
    private double cost;

    /**
     * The category of the Task.
     */
    private TaskCategory taskCategory;

    /**
     * The skills required for the Task.
     */
    private List<Skill> skills;

    /**
     * Constructs a Task object with the specified reference, description, informalDescription, technicalDescription, duration, cost, and taskCategory.
     * It validates the reference before setting it.
     *
     * @param reference the reference of the Task
     * @param description the description of the Task
     * @param informalDescription the informal description of the Task
     * @param technicalDescription the technical description of the Task
     * @param duration the duration of the Task
     * @param cost the cost of the Task
     * @param taskCategory the category of the Task
     */
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
    }

    /**
     * Constructs an empty Task object.
     */
    public Task() {}

    /**
     * Constructs a Task object with the specified reference.
     *
     * @param s the reference of the Task
     */
    public Task(String s) {}

    /**
     * Validates the specified reference.
     * If the reference is null, less than 4 characters, more than 10 characters, or contains characters other than alphanumeric characters, it throws an IllegalArgumentException.
     *
     * @param reference the reference to validate
     */
    private void validateReference(String reference) {
        if (reference == null || reference.length() < 4 || reference.length() > 10 || !reference.matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Reference cannot be null or empty.");
        }
    }

    // GETTERS & SETTERS

    /**
     * Returns the reference of this Task.
     *
     * @return the reference of this Task
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the reference of this Task.
     *
     * @param reference the reference to set
     */
    private void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Returns the description of this Task.
     *
     * @return the description of this Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this Task.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the informal description of this Task.
     *
     * @return the informal description of this Task
     */
    public String getInformalDescription() {
        return informalDescription;
    }

    /**
     * Sets the informal description of this Task.
     *
     * @param informalDescription the informal description to set
     */
    public void setInformalDescription(String informalDescription) {
        this.informalDescription = informalDescription;
    }

    /**
     * Returns the technical description of this Task.
     *
     * @return the technical description of this Task
     */
    public String getTechnicalDescription() {
        return technicalDescription;
    }

    /**
     * Sets the technical description of this Task.
     *
     * @param technicalDescription the technical description to set
     */
    public void setTechnicalDescription(String technicalDescription) {
        this.technicalDescription = technicalDescription;
    }

    /**
     * Returns the duration of this Task.
     *
     * @return the duration of this Task
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of this Task.
     *
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns the cost of this Task.
     *
     * @return the cost of this Task
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost of this Task.
     *
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Returns the category of this Task.
     *
     * @return the category of this Task
     */
    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    /**
     * Sets the category of this Task.
     *
     * @param taskCategory the category to set
     */
    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }

    /**
     * Returns the skills required for this Task.
     *
     * @return the skills required for this Task
     */
    public List<Skill> getSkills() {
        return this.skills;
    }

    /**
     * Sets the skills required for this Task.
     *
     * @param skills the skills to set
     */
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Checks if this Task is equal to the specified object.
     * The comparison is based on the reference field.
     *
     * @param o the object to compare with
     * @return true if this Task is equal to the specified object, false otherwise
     */
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

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    /**
     * Creates and returns a copy of this Task.
     * The clone has the same reference, description, informalDescription, technicalDescription, duration, cost, and taskCategory as this Task.
     *
     * @return a clone of this Task
     */
    public Task clone() {
        return new Task(this.reference, this.description, this.informalDescription, this.technicalDescription,
                this.duration, this.cost, this.taskCategory);
    }
}
