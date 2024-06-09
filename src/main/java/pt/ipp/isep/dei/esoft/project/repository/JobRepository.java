package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {
    private final List<Job> jobs;

    public JobRepository() {
        jobs = new ArrayList<>();
    }

    public Job getJobByName(String jobDescription) {
        for (Job job : jobs) {
            if (job.getNameOfJob().equals(jobDescription)) {
                return job;
            }
        }
        return null;
    }

    public Optional<Job> add(String nameOfJob) {
        Job job = new Job(nameOfJob);
        if (addJobIfValid(job)) {
            return Optional.of(job);
        }
        return Optional.empty();
    }

    private boolean addJobIfValid(Job job) {
        if (jobs.contains(job)) {
            return false;
        }
        jobs.add(job);
        return true;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
