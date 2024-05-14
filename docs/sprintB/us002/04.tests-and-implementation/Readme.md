# US006 - Create a Task 

## 4. Tests 

**Test 1:** Ensure Job Is Created Successfully. 

	@Test 
     void ensureJobIsCreatedSuccessfully() {
     Job job = new Job("name");
    }
	

**Test 2:** Check that it is not possible to create an instance of the Job class with null values.

	@Test(expected = IllegalArgumentException.class)
      public void ensureNullIsNotAllowed() {
		Job instance = new Job(null);
	}

**Test 3:** Check that it is not possible to register a job with special characters or numbers.

    @Test(expected = IllegalArgumentException.class)
     public void ensureReferenceMeetsAC1() {
     Job instance = new Job("g$%");
    }


## 5. Construction (Implementation)

### Class CreateJobController 

```java

public Optional<Job> createJob(String jobName) {
        Optional<Job> newJob = Optional.empty();
        boolean operationSuccess = false;

        if (Job.validateNameOfJob(jobName)) {
           newJob = jobRepository.createJob(new Job(jobName));
           if (newJob.isPresent()) {
              System.out.println("Job \""  + jobName + "\" registered successfully.");
           } else {
             System.out.println("Error: Failed to register job.");
           }
        }else {
           System.out.println("Error: Job name can't have special characters or digits.");
        }

        return newJob;
}
```

### Class JobRepository

```java
public Optional<Job> createJob(Job job) {
        Optional<Job> newJob = Optional.empty();
        if (validateJob(job)) {
           newJob = Optional.of(job);
           jobs.add(job);
        }
        return newJob;
}
```


## 6. Integration and Demo 

* A new option on the HRM UI menu options was added to allow registering a job.


## 7. Observations

n/a