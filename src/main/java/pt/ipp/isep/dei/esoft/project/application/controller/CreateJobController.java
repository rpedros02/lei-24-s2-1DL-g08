package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The CreateJobController class is responsible for handling operations related to creating jobs.
 * It interacts with the JobRepository to perform operations such as retrieving the job repository and generating jobs.
 */
public class CreateJobController {
    /**
     * The JobRepository instance.
     * This instance is used to interact with the job repository,
     * allowing the controller to perform operations related to jobs.
     */
    private JobRepository jobRepository;

    /**
     * The default constructor for the CreateJobController.
     * It initializes the JobRepository instance.
     */
    public CreateJobController(){
        getJobRepository();
    }

    /**
     * The constructor for the CreateJobController with a JobRepository parameter.
     * It initializes the JobRepository instance with the provided JobRepository.
     *
     * @param jobRepository The JobRepository object.
     */
    public CreateJobController(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    /**
     * Retrieves the job repository.
     * If the job repository is null, it retrieves the job repository from the repositories.
     *
     * @return The JobRepository object.
     */
    private JobRepository getJobRepository() {
        if (jobRepository ==null){
            Repositories repositories= Repositories.getInstance();

            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    /**
     * Generates a job.
     * It creates a new Job object with the provided job name and adds it to the job repository.
     * If the job is successfully added to the job repository, it returns true. Otherwise, it returns false.
     *
     * @param jobName The name of the job.
     * @return A boolean indicating the success of the operation.
     */
    public boolean generateJob(String jobName) {

        boolean isCreated = true;

        Job job = new Job(jobName);
        if(jobRepository.add(jobName).equals(Optional.empty()))
            isCreated = false;

        return isCreated;
    }

}

