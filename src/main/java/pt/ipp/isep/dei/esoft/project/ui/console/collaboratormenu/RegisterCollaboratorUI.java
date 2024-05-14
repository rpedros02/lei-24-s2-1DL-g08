package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Optional;
import java.util.Scanner;

public class RegisterCollaboratorUI implements Runnable {

    private final CreateCollaboratorController controller;
    private String name;
    private Date admissionDate;
    private Date birthDate;
    private Address address;
    private int mobileNumber;
    private String email;
    private int taxPayerNumber;
    private int idNumber;
    private IdDocType idType;
    private Job job;
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
        Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(name, birthDate,admissionDate, mobileNumber, email,taxPayerNumber, idType, idNumber, address, job);
    }


    private void requestData() {
        name = requestName();
        idType = requestIdType();
        idNumber = requestIdNumber();
        taxPayerNumber = requestTaxpayer();
        email = requestEmail();
        birthDate = requestBirthdate();
        address = requestAddress();
        mobileNumber = requestMobileNumber();
        admissionDate = requestAdmissionDate();
    }

    private Job requestJob() {
        Scanner input = new Scanner(System.in);
        System.out.println("Job: ");
        return new Job(input.nextLine());
    }

    private int requestIdNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("ID Doc Number: ");
        return input.nextInt();
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

    private Date requestBirthdate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Birthdate (format: dd-mm-yyyy): ");
        return new Date(input.nextLine());
    }

    private Address requestAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Address ---\n");
        String street = requestStreet();
        int nbr = requestStreetNumber();
        String postalCode = requestPostalCode();
        String city = requestCity();
        String district = requestDistrict();
        return new Address(street, nbr, city, postalCode, district);
    }

    private String requestDistrict() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nDistrict: ");
        return input.nextLine();
    }

    private String requestCity() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nCity: ");
        return input.nextLine();
    }

    private String requestPostalCode() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPostal Code: ");
        return input.nextLine();
    }

    private int requestStreetNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nStreet Number: ");
        return input.nextInt();
    }

    private String requestStreet() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nStreet: ");
        return input.nextLine();
    }

    private Date requestAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Admission Date (format: dd-mm-yyyy): ");
        return new Date(input.nextLine());
    }

    private String requestName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        return input.nextLine();
    }
}