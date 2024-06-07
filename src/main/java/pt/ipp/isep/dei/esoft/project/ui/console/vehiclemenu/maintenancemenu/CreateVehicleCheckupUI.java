package pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu;


import pt.ipp.isep.dei.esoft.project.application.controller.VehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleCheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Scanner;

public class CreateVehicleCheckupUI implements Runnable {
    private final VehicleCheckupController controller;
    private VehicleRepository vehicleRepository;
    public CreateVehicleCheckupUI() {
        controller = new VehicleCheckupController();
    }

    public VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }


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

    private String requestPlateId() {
        Scanner input = new Scanner(System.in);
        System.out.print("Plate Number: ");
        return input.nextLine();
    }

    private Date requestDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        return Utils.dateFromString(date);
    }

    private int requestLastCheckUpKm() {
        Scanner input = new Scanner(System.in);
        System.out.print("Last Check-Up Km: ");
        return input.nextInt();
    }
}