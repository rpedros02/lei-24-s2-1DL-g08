package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

/**
 * This class represents a VehicleController in the system.
 * It manages the operations related to vehicles.
 */
public class VehicleController {
    /**
     * The VehicleRepository object.
     */
    private final VehicleRepository vehicleRepository;

    /**
     * Constructs a new VehicleController.
     * It initializes the vehicleRepository by calling the getVehicleRepository method.
     */
    public VehicleController() {
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }


    /**
     * Creates a new Vehicle and adds it to the VehicleRepository.
     * It creates a new Vehicle with the specified parameters and adds it to the VehicleRepository.
     * If the Vehicle is added successfully, it returns the Vehicle.
     * Otherwise, it returns null.
     *
     * @param plateId the plate ID of the Vehicle
     * @param brand the brand of the Vehicle
     * @param model the model of the Vehicle
     * @param type the type of the Vehicle
     * @param tare the tare of the Vehicle
     * @param weight the weight of the Vehicle
     * @param mileage the mileage of the Vehicle
     * @param lastCheckUpKm the last check-up km of the Vehicle
     * @param register_date the register date of the Vehicle
     * @param acquisition_date the acquisition date of the Vehicle
     * @param maintenance_frequency the maintenance frequency of the Vehicle
     * @return the created Vehicle if it was added successfully, null otherwise
     */
    public Vehicle createVehicle(String plateId, String brand, String model,
                                 String type, double tare, double weight, int mileage, int lastCheckUpKm,
                                 Date register_date, Date acquisition_date, int maintenance_frequency) {

        if (vehicleRepository.add(plateId, brand, model, type, tare, weight, mileage,lastCheckUpKm, register_date, acquisition_date, maintenance_frequency)) {
            return new Vehicle(plateId, brand, model, type, tare, weight, mileage,lastCheckUpKm, register_date, acquisition_date, maintenance_frequency);
        } else {
            return null;
        }
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }
}

