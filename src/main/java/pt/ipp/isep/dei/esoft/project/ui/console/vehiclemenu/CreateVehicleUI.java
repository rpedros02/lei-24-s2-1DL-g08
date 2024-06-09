package pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.VehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.Scanner;

/**
 * This class provides the user interface for creating a vehicle.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class CreateVehicleUI implements Runnable {
    // Controller for vehicle creation
    private final VehicleController controller;
    // Vehicle attributes
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

    /**
     * Constructs a new instance of CreateVehicleUI.
     */
    public CreateVehicleUI() {
        controller = new VehicleController();
    }

    /**
     * Returns the vehicle controller.
     * @return the vehicle controller
     */
    private VehicleController getController() {
        return controller;
    }

    /**
     * Starts the user interface for creating a vehicle.
     * It prompts the user to input the vehicle data and submits the data to create a vehicle.
     */
    public void run() {
        System.out.println("\n\n--- Create Vehicle ----------------------");

        requestData();

        submitData();
    }

    /**
     * Submits the vehicle data to create a vehicle.
     * If the vehicle is created successfully, it displays a success message.
     * Otherwise, it displays a failure message.
     */
    private void submitData() {
        Vehicle vehicle = getController().createVehicle(plateId, brand, model, type, tare, weight, mileage, lastCheckUpKm, registerDate, acquisitionDate, maintenanceFrequency);
        if (vehicle != null && getController().getVehicleRepository().exists(plateId)) {
            System.out.println("\nVehicle successfully created!");
        } else {
            System.out.println("\nVehicle not created!");
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
     * Requests the user to input the brand.
     * @return the input brand
     */
    private String requestBrand() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Brand: ");
        return input.nextLine();
    }

    /**
     * Requests the user to input the model.
     * @return the input model
     */
    private String requestModel() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Model: ");
        return input.nextLine();
    }

    /**
     * Requests the user to input the type.
     * @return the input type
     */
    private String requestType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Type: ");
        return input.nextLine();
    }

    /**
     * Requests the user to input the tare.
     * @return the input tare
     */
    private double requestTare() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Tare: ");
        return input.nextDouble();
    }

    /**
     * Requests the user to input the weight.
     * @return the input weight
     */
    private double requestWeight() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Weight: ");
        return input.nextDouble();
    }

    /**
     * Requests the user to input the mileage.
     * @return the input mileage
     */
    private int requestMileage() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Mileage: ");
        return input.nextInt();
    }

    /**
     * Requests the user to input the last check-up km.
     * @return the input last check-up km
     */
    private int requestLastCheckUpKm() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Last Check Up Km: ");
        return input.nextInt();
    }

    /**
     * Converts a string to a date.
     * If the string is not in the correct format, it requests the user to input the register date again.
     * @param date the string to convert
     * @return the converted date
     */
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

    /**
     * Requests the user to input the register date.
     * @return the input register date
     */
    private Date requestRegisterDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Register Date (dd-mm-yyy): ");
        return getDateFromString(input.nextLine());
    }

    /**
     * Requests the user to input the acquisition date.
     * @return the input acquisition date
     */
    private Date requestAcquisitionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Acquisition Date (dd-mm-yyy): ");
        return getDateFromString(input.nextLine());
    }

    /**
     * Requests the user to input the maintenance frequency.
     * @return the input maintenance frequency
     */
    private int requestMaintenanceFrequency() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Maintenance Frequency(Kms): ");
        return input.nextInt();
    }

    /**
     * Requests the user to input the vehicle data.
     */
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
