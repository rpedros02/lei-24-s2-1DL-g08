package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * This class represents a TaskCategory in the system.
 * It has one field: description.
 */
public class TaskCategory {

    /**
     * The description of the TaskCategory.
     */
    private final String description;

    /**
     * Constructs a TaskCategory object with the specified description.
     *
     * @param description the description of the TaskCategory
     */
    public TaskCategory(String description) {
        this.description = description;
    }

    /**
     * Checks if this TaskCategory is equal to the specified object.
     * The comparison is based on the description field.
     *
     * @param o the object to compare with
     * @return true if this TaskCategory is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskCategory)) {
            return false;
        }
        TaskCategory that = (TaskCategory) o;
        return description.equals(that.description);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * Returns the description of this TaskCategory.
     *
     * @return the description of this TaskCategory
     */
    public String getDescription() {
        return description;
    }

    /**
     * Creates and returns a copy of this TaskCategory.
     * The clone has the same description as this TaskCategory.
     *
     * @return a clone of this TaskCategory
     */
    public TaskCategory clone() {
        return new TaskCategory(this.description);
    }
}