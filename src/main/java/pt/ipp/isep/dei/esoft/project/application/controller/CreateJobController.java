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

    public boolean generateJob(String jobName) {

        boolean isCreated = true;

        Job job = new Job(jobName);
        if(jobRepository.add(jobName).equals(Optional.empty()))
            isCreated = false;

        return isCreated;
    }

}

