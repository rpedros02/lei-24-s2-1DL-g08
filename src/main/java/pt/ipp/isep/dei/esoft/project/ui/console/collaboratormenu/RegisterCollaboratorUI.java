package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;


import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class RegisterCollaboratorUI implements Runnable {

    private final CreateCollaboratorController controller;
    private String name;
    private LocalDate admissionDate;
    private LocalDate birthdate;
    private Address address;
    private int mobileNumber;
    private String email;
    private int taxpayerNumber;
    private String idNumber;
    private IdDocType idType;
    private Job job;
    private Task task;
    public RegisterCollaboratorUI() {
        controller = new CreateCollaboratorController();
    }

    public CreateCollaboratorController getRegisterCollaboratorController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Register new Collaborator ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(name, birthdate, admissionDate, mobileNumber, email, taxpayerNumber, idType, idNumber, address, job, task);
    }


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

    private Job requestJob() {
        Scanner input = new Scanner(System.in);
        System.out.println("Job: ");
        return new Job(input.nextLine());
    }
    private Task requestTask() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task: ");
        return new Task(input.nextLine());
    }
    private String requestIdNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("ID Doc Number: ");
        return input.nextLine();
    }

    private int requestTaxpayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Taxpayer Number: ");
        return input.nextInt();
    }

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

    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Email: ");
        return input.nextLine();
    }

    private int requestMobileNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Mobile Number: ");
        return input.nextInt();
    }

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

    private LocalDate requestAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Admission date (format: dd-mm-yyyy): ");
        return new LocalDate(input.nextLine());
    }

    private LocalDate requestBirthdate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Birthdate (format: dd-mm-yyyy): ");
        return new LocalDate(input.nextLine());
    }

    private String requestName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        return input.nextLine();
    }
}
