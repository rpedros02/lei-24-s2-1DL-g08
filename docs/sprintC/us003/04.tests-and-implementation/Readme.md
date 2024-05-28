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

### Class CreateCollaboratorController 

```java
public class CreateCollaboratorController {

    private CollaboratorRepository collaboratorRepository;

    public CreateCollaboratorController(){
        getCollaboratorRepository();
    }
    public CreateCollaboratorController(CollaboratorRepository collaboratorRepository){
        this.collaboratorRepository = collaboratorRepository;
    }
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository ==null){
            Repositories repositories= Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }


    public Optional<Collaborator> createCollaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job) {
        return Organization.getInstance("000000000").createCollaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType, idNumber, address, job);
    }
}

```

### Class Collaborator

```java
public class Collaborator {
    // Regular expressions for validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String POSTAL_CODE_REGEX = "^\\d{4}-\\d{3}$";
    private static final String NAME_REGEX = "^[a-zA-ZÀ-ÿ-]+(?:\\s[a-zA-ZÀ-ÿ-]+)*$";

    // Default values for Collaborator attributes
    private final String NAME_BY_OMISSION = "no name";
    private final Date BIRTH_DATE_BY_OMISSION = new Date(12, 3, 1975);
    private final Date ADMISSION_DATE_BY_OMISSION = new Date(10,10,2002);
    private final int MOBILE_NUMBER_BY_OMISSION = 999999999;
    private final String EMAIL_BY_OMISSION = "nomail@mail.com";
    private final IdDocType ID_DOC_TYPE_BY_OMISSION = IdDocType.OTHER;
    private final String ID_NUMBER_BY_OMISSION = "11111111";
    private final int TAX_PAYER_NUMBER_BY_OMISSION = 240726286;
    private final Address ADDRESS_BY_OMISSION = new Address("no street", 12,"4000-400","no city", "no country");
    private final Job JOB_BY_OMISSION = new Job("no title");

    // Collaborator attributes
    private String name;
    private Date birthDate;
    private Date admissionDate;
    private int mobileNumber;
    private String email;
    private int taxPayerNumber;
    private IdDocType idDocType;
    private String idNumber;
    private Address address;
    private Job job;
    private List<Skill> assignedSkills;
    
    public Collaborator(String name,
                        Date birthDate,
                        Date admissionDate,
                        int mobileNumber,
                        String email,
                        int taxPayerNumber,
                        IdDocType idDocType,
                        String idNumber,
                        Address address,
                        Job job) {
        setName(name);
        setBirthDate(birthDate);
        setAdmissionDate(admissionDate);
        setMobileNumber(mobileNumber);
        setEmail(email);
        setTaxPayerNumber(taxPayerNumber);
        setIdDocType(idDocType);
        setIdNumber(idNumber);
        this.address = new Address(address);
        this.job = new Job(job);
        this.assignedSkills = new ArrayList<>();
    }

    public Collaborator(int taxPayerNumber) {
        setName(NAME_BY_OMISSION);
        setBirthDate(BIRTH_DATE_BY_OMISSION);
        setAdmissionDate(ADMISSION_DATE_BY_OMISSION);
        setMobileNumber(MOBILE_NUMBER_BY_OMISSION);
        setEmail(EMAIL_BY_OMISSION);
        setTaxPayerNumber(taxPayerNumber);
        setIdDocType(ID_DOC_TYPE_BY_OMISSION);
        setIdNumber(ID_NUMBER_BY_OMISSION);
        this.address = new Address(ADDRESS_BY_OMISSION);
        this.job = new Job(JOB_BY_OMISSION);
        this.assignedSkills = new ArrayList<>();
    }

    public Collaborator(Email email) {
        setName(NAME_BY_OMISSION);
        setBirthDate(BIRTH_DATE_BY_OMISSION);
        setAdmissionDate(ADMISSION_DATE_BY_OMISSION);
        setMobileNumber(MOBILE_NUMBER_BY_OMISSION);
        setEmail(email.getEmail());
        setTaxPayerNumber(TAX_PAYER_NUMBER_BY_OMISSION);
        setIdDocType(ID_DOC_TYPE_BY_OMISSION);
        setIdNumber(ID_NUMBER_BY_OMISSION);
        this.address = new Address(ADDRESS_BY_OMISSION);
        this.job = new Job(JOB_BY_OMISSION);
        this.assignedSkills = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() { return email; }

    public int getTaxPayerNumber() {
        return taxPayerNumber;
    }

    public IdDocType getIdDocType() {
        return idDocType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Address getAddress() {
        return new Address(address);
    }

    public Job getJob() {
        return new Job(job);
    }

    public void setJob(Job job) {
        this.job = new Job(job);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || !isValidName(name)) {
            throw new IllegalArgumentException("Name is invalid");
        }
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate == null || !isAtLeast18YearsOld(birthDate)) {
            throw new IllegalArgumentException("Birth date is invalid");
        }
        this.birthDate = birthDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setMobileNumber(int mobileNumber) {
        if (mobileNumber < 0 || !isValidMobileNumber(mobileNumber)) {
            throw new IllegalArgumentException("Mobile number is invalid");
        }
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            throw new IllegalArgumentException("Email is invalid");
        }
        this.email = email;
    }

    public void setTaxPayerNumber(int taxPayerNumber) {
        if (taxPayerNumber < 0 || !isValidTaxPayerNumber(String.valueOf(taxPayerNumber))) {
            throw new IllegalArgumentException("Tax payer number is invalid");
        }
        this.taxPayerNumber = taxPayerNumber;
    }

    public void setIdDocType(IdDocType idDocType) {
        this.idDocType = idDocType;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setAssignedSkills(List<Skill> assignedSkills) {
        this.assignedSkills = new ArrayList<>(assignedSkills);
    }
    
    public static boolean isValidName(String name) {
        return name.trim().matches(NAME_REGEX);
    }

    public static boolean isAtLeast18YearsOld(Date birthDate) {
        return Calendar.getInstance().get(Calendar.YEAR) - birthDate.getYear() >= 18;
    }
    
    public static boolean isValidMobileNumber(int mobileNumber) {
        return mobileNumber < 1000000000 && mobileNumber > 99999999;
    }
    
    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
    
    public static boolean isValidTaxPayerNumber(String number) {
        final int max=9;
        //check if is numeric and has 9 numbers
        if (!number.matches("[0-9]+") || number.length()!=max) return false;
        int checkSum=0;
        //calculate checkSum
        for (int i=0; i<max-1; i++){
            checkSum+=(number.charAt(i)-'0')*(max-i);
        }
        int checkDigit=11-(checkSum % 11);
        //if checkDigit is higher than 9 set it to zero
        if (checkDigit>9) checkDigit=0;
        //compare checkDigit with the last number of NIF
        return checkDigit==number.charAt(max-1)-'0';
    }

    public boolean hasSkill(Skill skill) {
        return this.assignedSkills.contains(skill);
    }

    public boolean hasSkill(String skillName) {
        for (Skill skill : this.assignedSkills) {
            if (skill.getName().equals(skillName)) return true;
        }
        return false;
    }
    public static boolean isValidPostalCode(String postalCode) {
        return postalCode.matches(POSTAL_CODE_REGEX);
    }

    public boolean addSkill(Skill skill) {
        if (hasSkill(skill)) {
            return false;
        }
        return this.assignedSkills.add(skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Collaborator)) {
            return false;
        }
        Collaborator collaborator = (Collaborator) o;
        return getTaxPayerNumber() == collaborator.getTaxPayerNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxPayerNumber, idNumber);
    }

    @Override
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthDate, this.admissionDate, this.mobileNumber, this.email,
                this.taxPayerNumber, this.idDocType, this.idNumber, this.address, this.job);

    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + getName() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", admissionDate=" + getAdmissionDate() +
                ", mobileNumber=" + getMobileNumber() +
                ", email='" + getEmail() + '\'' +
                ", taxPayerNumber=" + getTaxPayerNumber() +
                ", idDocType=" + getIdDocType() +
                ", idNumber=" + getIdNumber() +
                ", address=" + getAddress() +
                ", job=" + getJob() +
                '}';
    }


    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }
}
```

### Class CollaboratorRepository

```java
public class CollaboratorRepository {

    private final List<Collaborator> collaborator;

    public CollaboratorRepository() {
        this.collaborator = new ArrayList<>();
    }

    public boolean addCollaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job) {
        if (collaborator.isEmpty()) {
            collaborator.add(new Collaborator(name,birthDate,admissionDate,mobileNumber,email,taxPayerNumber,idDocType,idNumber,address,job));
            return true;
        }

        if (!checkIfTheCollaboratorExists(idNumber)) {
            collaborator.add(new Collaborator(name,birthDate,admissionDate,mobileNumber,email,taxPayerNumber,idDocType,idNumber,address,job));
            return true;
        }
        return false;
    }

    public boolean checkIfTheCollaboratorExists(String idNumber) {
        for (Collaborator collaborator : this.collaborator) {
            if (collaborator.getIdNumber().equals(idNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean assignSkills(Collaborator collaborator, List<Skill> skills) {
        boolean success = false;
        for (Skill s : skills) {
            success = collaborator.addSkill(s);
            if (!success) {
                return false;
            }
        }
        return success;
    }

    public List<Collaborator> getCollaboratorList() {
        return collaborator;
    }
}
```

## 6. Integration and Demo 

* A new option on the menu options was added.

* The user can now register a collaborator.


## 7. Observations

n/a