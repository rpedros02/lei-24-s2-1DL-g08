# US003 - Register a Collaborator

## 4. Tests 

**Test 1:** Check that it is not possible to register a collaborator if the name is not filled in

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameCanNotBeNull() {
        CollaboratorRegistration.registerCollaborator("", "example@example.com");
    }

**Test 2:** Check that it is not possible to register a collaborator if the birthdate is not filled in

    @Test(expected = IllegalArgumentException.class)
    public void ensureBirthdateCanNotBeNull() {
        CollaboratorRegistration.registerCollaborator("John Doe", "example@example.com", null);
    }

**Test 3:** Check that it is not possible to register a collaborator if the admission date is not filled in

    @Test(expected = IllegalArgumentException.class)
    public void ensureAdmissionDateCanNotBeNull() {
        CollaboratorRegistration.registerCollaborator("John Doe", "example@example.com", "01/01/1990", null);
    }

**Test 4:** Check that it is not possible to register a collaborator if the address is not filled in

	@Test(expected = IllegalArgumentException.class)
    public void ensureAddressCanNotBeNull() {
        CollaboratorRegistration.registerCollaborator("John Doe", "example@example.com", "01/01/1990", "AdmissionDate", null);
    }

**Test 5:** Check that it is not possible to register a collaborator if the mobile number is not filled in

	@Test(expected = IllegalArgumentException.class)
    public void ensureMobileNumberCanNotBeNull() {
        CollaboratorRegistration.registerCollaborator("John Doe", "example@example.com", "01/01/1990", "AdmissionDate", "Address", null);
    }

**Test 6:** Check that it is not possible to register a collaborator if the email is not filled in

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailCanNotBeNull() {
        CollaboratorRegistration.registerCollaborator("John Doe", null, "01/01/1990", "AdmissionDate", "Address", "MobileNumber");
    }

**Test 7:** Check that it is not possible to register a collaborator if the taxpayer number is not filled in

	@Test(expected = IllegalArgumentException.class)
    public void ensureTaxpayerNumberCanNotBeNull() {
		...
	}

**Test 8:** Check that it is not possible to register a collaborator if the ID doctype is not filled in

	@Test(expected = IllegalArgumentException.class)
    public void ensureTaxpayerNumberCanNotBeNull() {
        CollaboratorRegistration.registerCollaborator("John Doe", "example@example.com", "01/01/1990", "AdmissionDate", "Address", "MobileNumber", null);
    }

**Test 9:** Check that it is not possible to register a collaborator if the ID number is not filled in

	@Test(expected = IllegalArgumentException.class)
    public void ensureIdNumberCanNotBeNull() {
        CollaboratorRegistration.registerCollaborator("John Doe", "example@example.com", "01/01/1990", "AdmissionDate", "Address", "MobileNumber", "TaxpayerNumber", null);
    }

**Test 10:** Check that it is not possible to register a collaborator with an ID that has already been used - AC3.
**Test 11:** Check that it is not possible to register a collaborator with a  taxpayer that has already been used - AC4.

**Test 12:** Check that it is not possible to register a collaborator with a name of less than 6 words - AC6. 

	@Test(expected = IllegalArgumentException.class)
    public void validName(String name){
        String[] words = name.trim().split("\\s+");
        if (words.length>6){
            throw  new IllegalArgumentException("name cannot have more than six words");
        }
    }

**Test 13:** Check that it is not possible to register a collaborator under the age of 18 - AC7.

    @Test(expected = IllegalArgumentException.class)
    public void ensureAgeMeetsAC7() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthdate = dateFormat.parse("01/01/2006"); // Colaborador com menos de 18 anos

        CollaboratorRegistration.registerCollaborator("John Doe", "example@example.com", birthdate, "AdmissionDate", "Address", "MobileNumber", "TaxpayerNumber", "12345");
    }

**Test 14:** Check that it is not possible to register a collaborator with a mobile number that contains a number of digits other than 9 - AC8.
    
    @Test(expected = IllegalArgumentException.class)
    public void validPhoneNumber(int phoneNumber){
        String phoneString = String.valueOf(phoneNumber);
        if(phoneString.length() != 9){
            throw  new IllegalArgumentException("phone number should have 9 digits");
        }
    }

**Test 15:** Check that it is possible to register a collaborator with all this correct information.

    @Test
    public void ensureCollaboratorRegistration() {
        // Dados corretos para registro de colaborador
        String name = "John Doe";
        String email = "example@example.com";
        String birthdate = "01/01/1990";
        String admissionDate = "01/01/2022";
        String address = "123 Main Street";
        String mobileNumber = "123456789";
        String taxpayerNumber = "1234567890";
        String idNumber = "54321";

        CollaboratorRegistration.registerCollaborator(name, email, birthdate, admissionDate, address, mobileNumber, taxpayerNumber, idNumber);

    }

_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class CreateTaskController 

```java
public Task createTask(String reference, String description, String informalDescription, String technicalDescription,
                       Integer duration, Double cost, String taskCategoryDescription) {

	TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

	Employee employee = getEmployeeFromSession();
	Organization organization = getOrganizationRepository().getOrganizationByEmployee(employee);

	newTask = organization.createTask(reference, description, informalDescription, technicalDescription, duration,
                                      cost,taskCategory, employee);
    
	return newTask;
}
```

### Class Organization

```java
public Optional<Task> createTask(String reference, String description, String informalDescription,
                                 String technicalDescription, Integer duration, Double cost, TaskCategory taskCategory,
                                 Employee employee) {
    
    Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                         taskCategory, employee);

    addTask(task);
        
    return task;
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a