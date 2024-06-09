package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;


import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

/**
 * This class provides the user interface for registering a collaborator.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class RegisterCollaboratorUI implements Runnable {

    /**
     * The controller that handles the collaborator registration process.
     */
    private final CreateCollaboratorController controller;

    /**
     * The name of the collaborator to be registered.
     */
    private String name;

    /**
     * The admission date of the collaborator to be registered.
     */
    private Date admissionDate;

    /**
     * The birthdate of the collaborator to be registered.
     */
    private Date birthdate;

    /**
     * The address of the collaborator to be registered.
     */
    private Address address;

    /**
     * The mobile number of the collaborator to be registered.
     */
    private int mobileNumber;

    /**
     * The email of the collaborator to be registered.
     */
    private String email;

    /**
     * The taxpayer number of the collaborator to be registered.
     */
    private int taxpayerNumber;

    /**
     * The ID number of the collaborator to be registered.
     */
    private String idNumber;

    /**
     * The ID document type of the collaborator to be registered.
     */
    private IdDocType idType;

    /**
     * The job of the collaborator to be registered.
     */
    private Job job;

    /**
     * The task of the collaborator to be registered.
     */
    private Task task;

    /**
     * Constructs a new RegisterCollaboratorUI.
     * It initializes the controller.
     */
    public RegisterCollaboratorUI() {
        controller = new CreateCollaboratorController();
    }

    /**
     * Returns the controller that handles the collaborator registration process.
     *
     * @return the controller that handles the collaborator registration process
     */
    public CreateCollaboratorController getRegisterCollaboratorController() {
        return controller;
    }

    /**
     * Starts the collaborator registration process.
     * It first requests the data of the collaborator from the user.
     * It then attempts to register the collaborator with the entered data.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Register new Collaborator ------------------------");

        requestData();

        submitData();
    }

    /**
     * Attempts to register the collaborator with the entered data.
     * If the collaborator registration is successful, it prints a success message.
     * If the collaborator registration is not successful, it prints an error message.
     */
    private void submitData() {
        Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(name, birthdate, admissionDate, mobileNumber, email, taxpayerNumber, idType, idNumber, address, job, task);
    }

    /**
     * Requests the data of the collaborator from the user.
     * It prompts the user to enter the name, ID document type, ID number, taxpayer number, email, birthdate, address, mobile number, admission date, job, and task of the collaborator.
     */
    private void requestData() {
        name = requestName();
        idType = requestIdType();
        idNumber = requestIdNumber();
        taxpayerNumber = requestTaxpayer();
        email = requestEmail();
        birthdate = requestBirthdate();
        address = requestAddress();
        mobileNumber = requestMobileNumber();
        admissionDate = requestAdmissionDate();
        job = requestJob();
        task = requestTask();
    }

    /**
     * Requests the job of the collaborator from the user.
     *
     * @return the job of the collaborator
     */
    private Job requestJob() {
        Scanner input = new Scanner(System.in);
        System.out.println("Job: ");
        return new Job(input.nextLine());
    }

    /**
     * Requests the task of the collaborator from the user.
     *
     * @return the task of the collaborator
     */
    private Task requestTask() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task: ");
        return new Task(input.nextLine());
    }

    /**
     * Requests the ID number of the collaborator from the user.
     *
     * @return the ID number of the collaborator
     */
    private String requestIdNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("ID Doc Number: ");
        return input.nextLine();
    }

    /**
     * Requests the taxpayer number of the collaborator from the user.
     *
     * @return the taxpayer number of the collaborator
     */
    private int requestTaxpayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Taxpayer Number: ");
        return input.nextInt();
    }

    /**
     * Requests the ID document type of the collaborator from the user.
     * It prompts the user to select an ID document type from a list of options.
     *
     * @return the ID document type of the collaborator
     */
    private IdDocType requestIdType() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select ID Document Type:");
        System.out.println("1. CC");
        System.out.println("2. BI");
        System.out.println("3. Passport");
        int choice = input.nextInt();

        boolean inputValid = true;
        do {
            switch (choice) {
                case 1:
                    return IdDocType.CC;
                case 2:
                    return IdDocType.BI;
                case 3:
                    return IdDocType.PASSPORT;
                default:
                    inputValid = false;
                    System.out.println("Please select a valid option.");
            }
        } while (!inputValid);

        return null;
    }

    /**
     * Requests the email of the collaborator from the user.
     *
     * @return the email of the collaborator
     */
    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Email: ");
        return input.nextLine();
    }

    /**
     * Requests the mobile number of the collaborator from the user.
     *
     * @return the mobile number of the collaborator
     */
    private int requestMobileNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Mobile Number: ");
        return input.nextInt();
    }

    /**
     * Requests the address of the collaborator from the user.
     * It prompts the user to enter the street, door number, postal code, city, and district of the address.
     *
     * @return the address of the collaborator
     */
    private Address requestAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Address--\n");
        System.out.println("Street: ");
        String street = input.nextLine();
        System.out.println("\nDoor Number: ");
        int doorNumber = input.nextInt();
        input.nextLine();
        System.out.println("\nPostal Code: ");
        String postalCode = input.nextLine();
        System.out.println("\nCity: ");
        String city = input.nextLine();
        System.out.println("\nDistrict: ");
        String district = input.nextLine();
        return new Address(street, doorNumber, postalCode, city, district);
    }

    /**
     * Requests the admission date of the collaborator from the user.
     *
     * @return the admission date of the collaborator
     */
    private Date requestAdmissionDate() {
        return Utils.readDateFromConsole("Admission Date (format: dd-mm-yyyy): ");
    }

    /**
     * Requests the birthdate of the collaborator from the user.
     *
     * @return the birthdate of the collaborator
     */
    private Date requestBirthdate() {
        return Utils.readDateFromConsole("Birthdate (format: dd-mm-yyyy): ");
    }

    /**
     * Requests the name of the collaborator from the user.
     *
     * @return the name of the collaborator
     */
    private String requestName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        return input.nextLine();
    }
}
