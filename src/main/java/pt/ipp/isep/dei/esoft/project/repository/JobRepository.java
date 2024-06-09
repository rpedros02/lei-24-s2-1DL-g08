package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a JobRepository in the system.
 * It manages a list of Job objects.
 */
public class JobRepository {
    /**
     * The list of Job objects.
     */
    private final List<Job> jobs;

    /**
     * Constructs a JobRepository object.
     * It initializes the jobs list as an empty ArrayList.
     */
    public JobRepository() {
        jobs = new ArrayList<>();
    }

    /**
     * Returns a Job object with the specified job description.
     * If no Job has the specified job description, it returns null.
     *
     * @param jobDescription the job description of the Job to return.
     * @return the Job object with the specified job description.
     */
    public Job getJobByName(String jobDescription) {
        for (Job job : jobs) {
            if (job.getNameOfJob().equals(jobDescription)) {
                return job;
            }
        }
        return null;
    }

    /**
     * Adds a Job to the repository with the specified job name.
     * It creates a new Job with the specified job name and adds it to the list if it is valid.
     *
     * @param nameOfJob the name of the Job to add.
     * @return an Optional containing the added Job if successful, or an empty Optional if not.
     */
    public Optional<Job> add(String nameOfJob) {
        Job job = new Job(nameOfJob);
        if (addJobIfValid(job)) {
            return Optional.of(job);
        }
        return Optional.empty();
    }

    /**
     * Adds a Job to the repository if it is valid.
     * It checks if a Job with the same name already exists in the list before adding.
     *
     * @param job the Job to add.
     * @return true if the Job was added successfully, false otherwise.
     */
    private boolean addJobIfValid(Job job) {
        if (jobs.contains(job)) {
            return false;
        }
        jobs.add(job);
        return true;
    }

    /**
     * Returns the list of all Job objects in the repository.
     *
     * @return the list of all Job objects.
     */
    public List<Job> getJobs() {
        return jobs;
    }
}