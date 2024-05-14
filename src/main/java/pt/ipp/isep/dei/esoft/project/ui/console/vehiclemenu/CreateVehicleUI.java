package pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.VehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.Scanner;

public class CreateVehicleUI implements Runnable {
    private final VehicleController controller;
    private String plateId;
    private String brand;
    private String model;
    private String type;
    private double tare;
    private double weight;
    private int mileage;
    private int lastCheckUpKm;
    private Date registerDate;
    private Date acquisitionDate;
    private int maintenanceFrequency;

    public CreateVehicleUI() {
        controller = new VehicleController();
    }

    private VehicleController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Vehicle ----------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Vehicle vehicle = getController().createVehicle(plateId, brand, model, type, tare, weight, mileage, lastCheckUpKm, registerDate, acquisitionDate, maintenanceFrequency);
        if (vehicle != null && getController().getVehicleRepository().exists(plateId)) {
            System.out.println("\nVehicle successfully created!");
        } else {
            System.out.println("\nVehicle not created!");
        }
    }

    private String requestPlateId() {
        Scanner input = new Scanner(System.in);
        System.out.print("Plate Number: ");
        return input.nextLine();
    }

    private String requestBrand() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Brand: ");
        return input.nextLine();
    }

    private String requestModel() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Model: ");
        return input.nextLine();
    }

    private String requestType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Type: ");
        return input.nextLine();
    }

    private double requestTare() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Tare: ");
        return input.nextDouble();
    }

    private double requestWeight() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Weight: ");
        return input.nextDouble();
    }

    private int requestMileage() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Mileage: ");
        return input.nextInt();
    }

    private int requestLastCheckUpKm() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Last Check Up Km: ");
        return input.nextInt();
    }

    public Date getDateFromString(String date) {
        try {
            String day = date.substring(0, 2);
            String month = date.substring(3, 5);
            String year = date.substring(6, 10);
            return new Date(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
        } catch (Exception e) {
            System.out.println("Invalid date format. Please try again.");
            return requestRegisterDate();
        }
    }

    private Date requestRegisterDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Register Date (dd-mm-yyy): ");
        return getDateFromString(input.nextLine());
    }

    private Date requestAcquisitionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Acquisition Date (dd-mm-yyy): ");
        return getDateFromString(input.nextLine());
    }

    private int requestMaintenanceFrequency() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Maintenance Frequency(Kms): ");
        return input.nextInt();
    }

    private void requestData() {
        this.plateId = requestPlateId();
        this.brand = requestBrand();
        this.model = requestModel();
        this.type = requestType();
        this.tare = requestTare();
        this.weight = requestWeight();
        this.mileage = requestMileage();
        this.lastCheckUpKm = requestLastCheckUpKm();
        this.registerDate = requestRegisterDate();
        this.acquisitionDate = requestAcquisitionDate();
        this.maintenanceFrequency = requestMaintenanceFrequency();
    }


}
