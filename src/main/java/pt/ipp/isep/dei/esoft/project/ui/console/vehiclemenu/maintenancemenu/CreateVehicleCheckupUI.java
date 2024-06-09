package pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu;


import pt.ipp.isep.dei.esoft.project.application.controller.VehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleCheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Scanner;

/**
 * This class provides the user interface for creating a vehicle check-up.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class CreateVehicleCheckupUI implements Runnable {
    // Controller for vehicle check-up
    private final VehicleCheckupController controller;
    // Repository for vehicles
    private VehicleRepository vehicleRepository;

    /**
     * Constructs a new instance of CreateVehicleCheckupUI.
     */
    public CreateVehicleCheckupUI() {
        controller = new VehicleCheckupController();
    }

    /**
     * Returns the vehicle repository.
     * If the vehicle repository is null, it retrieves the vehicle repository from the repositories instance.
     * @return the vehicle repository
     */
    public VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Starts the user interface for creating a vehicle check-up.
     * It prompts the user to input the plate number, date, and last check-up km.
     * It then submits the data to register a vehicle check-up.
     * If the registration is successful, it displays a success message.
     * Otherwise, it displays a failure message.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Register Vehicle Check-Up");
        String plateId = requestPlateId();
        Date date = requestDate();
        int lastCheckUpKm = requestLastCheckUpKm();

        boolean success = controller.registerVehicleCheckup(plateId, date, lastCheckUpKm);

        if (success) {
            System.out.println("Check-Up registered successfully!");
        } else {
            System.out.println("Failed to register Check-Up. Please try again.");
        }
    }

    /**
     * Requests the user to input the plate number.
     * @return the input plate number
     */
    private String requestPlateId() {
        Scanner input = new Scanner(System.in);
        System.out.print("Plate Number: ");
        return input.nextLine();
    }

    /**
     * Requests the user to input the date.
     * @return the input date
     */
    private Date requestDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        return Utils.dateFromString(date);
    }

    /**
     * Requests the user to input the last check-up km.
     * @return the input last check-up km
     */
    private int requestLastCheckUpKm() {
        Scanner input = new Scanner(System.in);
        System.out.print("Last Check-Up Km: ");
        return input.nextInt();
    }
}