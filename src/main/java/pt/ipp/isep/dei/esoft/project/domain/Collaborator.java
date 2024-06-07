package pt.ipp.isep.dei.esoft.project.domain;


import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Collaborator in the system.
 */
public class Collaborator {
    // Regular expressions for validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String POSTAL_CODE_REGEX = "^\\d{4}-\\d{3}$";
    private static final String NAME_REGEX = "^[a-zA-ZÀ-ÿ-]+(?:\\s[a-zA-ZÀ-ÿ-]+)*$";

    // Default values for Collaborator attributes
    private final String NAME_BY_OMISSION = "no name";
    private final LocalDate BIRTH_DATE_BY_OMISSION = new LocalDate(12, 3, 1975);
    private final LocalDate ADMISSION_DATE_BY_OMISSION = new LocalDate(10,10,2002);
    private final int MOBILE_NUMBER_BY_OMISSION = 999999999;
    private final String EMAIL_BY_OMISSION = "nomail@mail.com";
    private final IdDocType ID_DOC_TYPE_BY_OMISSION = IdDocType.OTHER;
    private final String ID_NUMBER_BY_OMISSION = "11111111";
    private final int TAX_PAYER_NUMBER_BY_OMISSION = 240726286;
    private final Address ADDRESS_BY_OMISSION = new Address("no street", 12,"4000-400","no city", "no country");
    private final Job JOB_BY_OMISSION = new Job("no title");

    // Collaborator attributes
    private String name;
    private LocalDate birthDate;
    private LocalDate admissionDate;
    private int mobileNumber;
    private String email;
    private int taxPayerNumber;
    private IdDocType idDocType;
    private String idNumber;
    private Address address;
    private Job job;
    private List<Skill> assignedSkills;
    private Task task;

    public Task getTask() {
        return task;
    }

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
                        LocalDate birthDate,
                        LocalDate admissionDate,
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getAdmissionDate() {
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

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null || !isAtLeast18YearsOld(birthDate)) {
            throw new IllegalArgumentException("Birth date is invalid");
        }
        this.birthDate = birthDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
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
    public static boolean isAtLeast18YearsOld(LocalDate birthDate) {
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
    // https://pt.wikipedia.org/wiki/N%C3%BAmero_de_identifica%C3%A7%C3%A3o_fiscal#Exemplo_de_valida%C3%A7%C3%A3o_em_Java[8]
    public static boolean isValidTaxPayerNumber(String number) {
        final int max=9;
        return number.matches("[0-9]{9}");
     /*   int checkSum=0;
        //calculate checkSum
        for (int i=0; i<max-1; i++){
            checkSum+=(number.charAt(i)-'0')*(max-i);
        }
        int checkDigit=11-(checkSum % 11);
        //if checkDigit is higher than 9 set it to zero
        if (checkDigit>9) checkDigit=0;
        //compare checkDigit with the last number of NIF
        int a = number.charAt(max-1)-'0';
        return checkDigit==number.charAt(max-1)-'0';*/
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

    /**
     * Validates if a given postal code is in a valid format.
     *
     * @param postalCode The postal code to validate.
     * @return True if the postal code is valid, otherwise false.
     */
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
                this.taxPayerNumber, this.idDocType, this.idNumber, this.address, this.job, this.task);
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
    public void assignTask(Task task) {
        if (this.task != null) {
            throw new IllegalStateException("This collaborator already has a task assigned.");
        }
        this.task = task;
    }


    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }
}
