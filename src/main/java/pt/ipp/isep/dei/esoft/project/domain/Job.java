package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * This class represents a Job in the system.
 * It has one field: nameOfJob.
 */
public class Job {

    /**
     * The name of the Job.
     */
    private String nameOfJob;

    /**
     * Constructs a Job object with the specified name.
     * If the name is not valid, it throws an IllegalArgumentException.
     *
     * @param nameOfJob the name of the Job
     */
    public Job(String nameOfJob){
        validateNameJob(nameOfJob);
        this.nameOfJob = nameOfJob;
    }

    /**
     * Constructs a Job object by copying another Job object.
     *
     * @param job the Job to copy
     */
    public Job(Job job) {
        this.nameOfJob = job.nameOfJob;
    }

    /**
     * Returns the name of this Job.
     *
     * @return the name of this Job
     */
    public String getNameOfJob() {
        return nameOfJob;
    }

    /**
     * Sets the name of this Job.
     *
     * @param nameOfJob the name to set
     */
    public void setNameOfJob(String nameOfJob) {
        this.nameOfJob = nameOfJob;
    }

    /**
     * Validates the specified name of a Job.
     * If the name is null or contains special characters or digits, it throws an IllegalArgumentException.
     *
     * @param nameOfJob the name to validate
     */
    private void validateNameJob(String nameOfJob) {
        if (nameOfJob == null){
            throw new IllegalArgumentException("Name of job cannot be null.");
        }else if(!nameOfJob.matches("[a-zA-Z0-9 ]*")) {
            throw new IllegalArgumentException("Name of job cannot have special characters or digits.");
        }
    }

    /**
     * Checks if this Job is equal to the specified object.
     * The comparison is based on the nameOfJob field.
     *
     * @param obj the object to compare with
     * @return true if this Job is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Job)) {
            return false;
        }
        Job that = (Job) obj;
        return nameOfJob.equals(that.nameOfJob);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(nameOfJob);
    }

}