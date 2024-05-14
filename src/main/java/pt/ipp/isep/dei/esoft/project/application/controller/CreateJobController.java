package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class CreateJobController {
    private JobRepository jobRepository;

    public CreateJobController(){
        getJobRepository();
    }

    public CreateJobController(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository ==null){
            Repositories repositories= Repositories.getInstance();

            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

}

