package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Job {

    private String nameOfJob;
    public Job(String nameOfJob){
        validateNameJob(nameOfJob);
        this.nameOfJob =nameOfJob;
    }

    public Job(Job job) {
        this.nameOfJob = job.nameOfJob;
    }

    public String getNameOfJob() {
        return nameOfJob;
    }

    public void setNameOfJob(String nameOfJob) {
        this.nameOfJob = nameOfJob;
    }

    private void validateNameJob(String nameOfJob) {
        if (nameOfJob == null){
            throw new IllegalArgumentException("Name of job cannot be null.");
        }else if(!nameOfJob.matches("[a-zA-Z0-9 ]*")) {
            throw new IllegalArgumentException("Name of job cannot have special characters or digits.");
        }
    }
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
    @Override
    public int hashCode() {
        return Objects.hash(nameOfJob);
    }

}