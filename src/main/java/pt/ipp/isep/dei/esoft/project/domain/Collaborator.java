package pt.ipp.isep.dei.esoft.project.domain;


import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * The Collaborator class represents a collaborator in a project.
 * It includes properties for the collaborator's name, birth date, admission date, mobile number, email, taxpayer number,
 * identification document type, identification number, address, job, assigned skills, and task.
 * It also includes methods for setting these properties with validation, and for managing the collaborator's skills and task.
 */
public class Collaborator {
    // Regular expressions for validation
    /**
     * Regular expression for validating email addresses.
     */
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    /**
     * Regular expression for validating postal codes.
     */
    private static final String POSTAL_CODE_REGEX = "^\\d{4}-\\d{3}$";
    /**
     * Regular expression for validating names.
     */
    private static final String NAME_REGEX = "^[a-zA-ZÀ-��-]+(?:\\s[a-zA-ZÀ-ÿ-]+)*$";

    // Default values for Collaborator attributes
    /**
     * Default value for the name of a collaborator.
     */
    private final String NAME_BY_OMISSION = "no name";
    /**
     * Default value for the birth date of a collaborator.
     */
    private final Date BIRTH_DATE_BY_OMISSION = new Date(12, 4, 2002);
    /**
     * Default value for the admission date of a collaborator.
     */
    private final Date ADMISSION_DATE_BY_OMISSION = new Date(23, 12, 2023);
    /**
     * Default value for the mobile number of a collaborator.
     */
    private final int MOBILE_NUMBER_BY_OMISSION = 999999999;
    /**
     * Default value for the email of a collaborator.
     */
    private final String EMAIL_BY_OMISSION = "nomail@mail.com";
    /**
     * Default value for the identification document type of a collaborator.
     */
    private final IdDocType ID_DOC_TYPE_BY_OMISSION = IdDocType.OTHER;
    /**
     * Default value for the identification number of a collaborator.
     */
    private final String ID_NUMBER_BY_OMISSION = "11111111";
    /**
     * Default value for the taxpayer number of a collaborator.
     */
    private final int TAX_PAYER_NUMBER_BY_OMISSION = 240726286;
    /**
     * Default value for the address of a collaborator.
     */
    private final Address ADDRESS_BY_OMISSION = new Address("no street", 12, "4000-400", "no city", "no country");
    /**
     * Default value for the job of a collaborator.
     */
    private final Job JOB_BY_OMISSION = new Job("no title");

    // Collaborator attributes
    /**
     * The name of the collaborator.
     */
    private String name;
    /**
     * The birth date of the collaborator.
     */
    private Date birthDate;
    /**
     * The admission date of the collaborator.
     */
    private Date admissionDate;
    /**
     * The mobile number of the collaborator.
     */
    private int mobileNumber;
    /**
     * The email of the collaborator.
     */
    private String email;
    /**
     * The taxpayer number of the collaborator.
     */
    private int taxPayerNumber;
    /**
     * The identification document type of the collaborator.
     */
    private IdDocType idDocType;
    /**
     * The identification number of the collaborator.
     */
    private String idNumber;
    /**
     * The address of the collaborator.
     */
    private Address address;
    /**
     * The job of the collaborator.
     */
    private Job job;
    /**
     * The list of skills assigned to the collaborator.
     */
    private List<Skill> assignedSkills;
    /**
     * The task assigned to the collaborator.
     */
    private Task task;

    /**
     * Retrieves the task assigned to the collaborator.
     *
     * @return The task assigned to the collaborator.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Assigns a task to the collaborator.
     *
     * @param task The task to be assigned.
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Constructs a Collaborator object with specified attributes.
     *
     * @param name           The name of the collaborator.
     * @param birthDate      The birthdate of the collaborator.
     * @param admissionDate  The admission date of the collaborator.
     * @param mobileNumber   The mobile number of the collaborator.
     * @param email          The email address of the collaborator.
     * @param taxPayerNumber The taxpayer number of the collaborator.
     * @param idDocType      The identification document type of the collaborator.
     * @param idNumber       The identification number of the collaborator.
     * @param address        The address of the collaborator.
     * @param job            The job of the collaborator.
     */
    public Collaborator(String name,
                        Date birthDate,
                        Date admissionDate,
                        int mobileNumber,
                        String email,
                        int taxPayerNumber,
                        IdDocType idDocType,
                        String idNumber,
                        Address address,
                        Job job,
                        Task task) {
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

    /**
     * Constructs a Collaborator object with only the taxpayer number specified. Other attributes are set to default values.
     *
     * @param taxPayerNumber The taxpayer number of the collaborator.
     */
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
        this.task = new Task();
    }

    /**
     * Constructs a Collaborator object with only the email specified. Other attributes are set to default values.
     *
     * @param email The email of the collaborator.
     */
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

    /**
     * Retrieves the name of the collaborator.
     *
     * @return The name of the collaborator.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the birth date of the collaborator.
     *
     * @return The birth date of the collaborator.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Retrieves the admission date of the collaborator.
     *
     * @return The admission date of the collaborator.
     */
    public Date getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Retrieves the mobile number of the collaborator.
     *
     * @return The mobile number of the collaborator.
     */
    public int getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Retrieves the email of the collaborator.
     *
     * @return The email of the collaborator.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the taxpayer number of the collaborator.
     *
     * @return The taxpayer number of the collaborator.
     */
    public int getTaxPayerNumber() {
        return taxPayerNumber;
    }

    /**
     * Retrieves the identification document type of the collaborator.
     *
     * @return The identification document type of the collaborator.
     */
    public IdDocType getIdDocType() {
        return idDocType;
    }

    /**
     * Retrieves the identification number of the collaborator.
     *
     * @return The identification number of the collaborator.
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Retrieves the address of the collaborator.
     *
     * @return The address of the collaborator.
     */
    public Address getAddress() {
        return new Address(address);
    }

    /**
     * Retrieves the job of the collaborator.
     *
     * @return The job of the collaborator.
     */
    public Job getJob() {
        return new Job(job);
    }

    /**
     * Sets the job of the collaborator.
     *
     * @param job The job to be set.
     */
    public void setJob(Job job) {
        this.job = new Job(job);
    }

    /**
     * Sets the name of the collaborator after validation.
     * If the name to be set is not valid, throws exception.
     *
     * @param name the name to be set
     * @throws IllegalArgumentException if the name is invalid
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || !isValidName(name)) {
            throw new IllegalArgumentException("Name is invalid");
        }
        this.name = name;
    }

    /**
     * Sets the birth date of the collaborator after validation.
     * If the birth date to be set is not valid, throws exception.
     *
     * @param birthDate the birth date to be set
     * @throws IllegalArgumentException if the birth date is invalid
     */
    public void setBirthDate(Date birthDate) {
        if (birthDate == null || !isAtLeast18YearsOld(birthDate)) {
            throw new IllegalArgumentException("Birth date is invalid");
        }
        this.birthDate = birthDate;
    }

    /**
     * Sets the admission date of the collaborator.
     *
     * @param admissionDate the admission date to be set
     */
    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    /**
     * Sets the mobile number of the collaborator after validation.
     * If the mobile number to be set is not valid, throws exception.
     *
     * @param mobileNumber the mobile number to be set
     * @throws IllegalArgumentException if the mobile number is invalid
     */
    public void setMobileNumber(int mobileNumber) {
        if (mobileNumber < 0 || !isValidMobileNumber(mobileNumber)) {
            throw new IllegalArgumentException("Mobile number is invalid");
        }
        this.mobileNumber = mobileNumber;
    }

    /**
     * Sets the email of the collaborator after validation.
     * If the email to be set is not valid, throws exception.
     *
     * @param email the email to be set
     * @throws IllegalArgumentException if the email is invalid
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            throw new IllegalArgumentException("Email is invalid");
        }
        this.email = email;
    }

    /**
     * Sets the taxpayer number of the collaborator after validation.
     * If the taxpayer number to be set is not valid, throws exception.
     *
     * @param taxPayerNumber the taxpayer number to be set
     * @throws IllegalArgumentException if the taxpayer number is invalid
     */
    public void setTaxPayerNumber(int taxPayerNumber) {
        if (taxPayerNumber < 0 || !isValidTaxPayerNumber(String.valueOf(taxPayerNumber))) {
            throw new IllegalArgumentException("Tax payer number is invalid");
        }
        this.taxPayerNumber = taxPayerNumber;
    }

    /**
     * Sets the identification document type of the collaborator.
     *
     * @param idDocType the identification document type to be set
     */
    public void setIdDocType(IdDocType idDocType) {
        this.idDocType = idDocType;
    }

    /**
     * Sets the identification number of the collaborator.
     *
     * @param idNumber the identification number to be set
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * Sets the assigned skills of the collaborator.
     *
     * @param assignedSkills the list of skills to be assigned
     */
    public void setAssignedSkills(List<Skill> assignedSkills) {
        this.assignedSkills = new ArrayList<>(assignedSkills);
    }

    /**
     * Validates if a given name is in a valid format.
     *
     * @param name The name to validate.
     * @return True if the name is valid, otherwise false.
     */
    public static boolean isValidName(String name) {
        return name.trim().matches(NAME_REGEX);
    }

    /**
     * Checks if a given birthdate corresponds to an age of at least 18 years old.
     *
     * @param birthDate The birthdate to check.
     * @return True if the birthdate corresponds to an age of at least 18 years old, otherwise false.
     */
    public static boolean isAtLeast18YearsOld(Date birthDate) {
        return Calendar.getInstance().get(Calendar.YEAR) - birthDate.getYear() >= 18;
    }

    /**
     * Validates if a given mobile number is in a valid format.
     *
     * @param mobileNumber The mobile number to validate.
     * @return True if the mobile number is valid, otherwise false.
     */
    public static boolean isValidMobileNumber(int mobileNumber) {
        return mobileNumber < 1000000000 && mobileNumber > 99999999;
    }

    /**
     * Retrieves the list of skills assigned to the collaborator.
     *
     * @return The list of skills assigned to the collaborator.
     */
    public List<Skill> getAssignedSkills() {
        return assignedSkills;
    }

    /**
     * Validates if a given email address is in a valid format.
     *
     * @param email The email address to validate.
     * @return True if the email address is valid, otherwise false.
     */
    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    /**
     * Validates if a given taxpayer number is in the NIF format.
     *
     * @param number The taxpayer number to validate.
     * @return True if the taxpayer number is valid, otherwise false.
     */
    public static boolean isValidTaxPayerNumber(String number) {
        final int max = 9;
        return number.matches("[0-9]{9}");
    }

    /**
     * Checks if the collaborator has a specific skill.
     *
     * @param skill The skill to check.
     * @return True if the collaborator has the skill, otherwise false.
     */
    public boolean hasSkill(Skill skill) {
        return this.assignedSkills.contains(skill);
    }

    /**
     * Checks if the collaborator has a specific skill by its name.
     *
     * @param skillName The name of the skill to check.
     * @return True if the collaborator has the skill, otherwise false.
     */
    public boolean hasSkill(String skillName) {
        for (Skill skill : this.assignedSkills) {
            if (skill.getName().equals(skillName)) return true;
        }
        return false;
    }

    /**
     * Validates if a given postal code is in a valid format.
     *
     * @param postalCode The postal code to validate.
     * @return True if the postal code is valid, otherwise false.
     */
    public static boolean isValidPostalCode(String postalCode) {
        return postalCode.matches(POSTAL_CODE_REGEX);
    }

    /**
     * Adds a skill to the collaborator's list of skills if they do not already have it.
     *
     * @param skill The skill to add.
     * @return True if the skill was added, otherwise false.
     */
    public boolean addSkill(Skill skill) {
        if (hasSkill(skill)) {
            return false;
        }
        return this.assignedSkills.add(skill);
    }

    /**
     * Checks if the collaborator is equal to another object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
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

    /**
     * Generates a hash code for the collaborator.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(taxPayerNumber, idNumber);
    }

    /**
     * Creates a clone of the collaborator.
     *
     * @return The clone of the collaborator.
     */
    @Override
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthDate, this.admissionDate, this.mobileNumber, this.email,
                this.taxPayerNumber, this.idDocType, this.idNumber, this.address, this.job, this.task);
    }

    /**
     * Generates a string representation of the collaborator.
     *
     * @return The string representation of the collaborator.
     */
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

    /**
     * Assigns a task to the collaborator.
     *
     * @param task The task to be assigned.
     * @throws IllegalStateException if the collaborator already has a task assigned.
     */
    public void assignTask(Task task) {
        if (this.task != null) {
            throw new IllegalStateException("This collaborator already has a task assigned.");
        }
        this.task = task;
    }

    /**
     * Checks if the collaborator has a specific email.
     *
     * @param email The email to check.
     * @return True if the collaborator has the email, otherwise false.
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * Assigns a list of skills to the collaborator.
     *
     * @param skills The list of skills to be assigned.
     */
    public void assignSkills(List<Skill> skills) {
        this.assignedSkills = new ArrayList<>(skills);
    }
}