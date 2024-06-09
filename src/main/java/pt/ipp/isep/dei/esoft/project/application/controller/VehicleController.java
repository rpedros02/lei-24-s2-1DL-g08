package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

/**
 * The VehicleController class is responsible for handling operations related to vehicles.
 * It interacts with the VehicleRepository to perform operations such as creating a vehicle.
 */
public class VehicleController {
    /**
     * The VehicleRepository instance.
     * This instance is used to interact with the vehicle repository,
     * allowing the controller to perform operations related to vehicles.
     */
    private VehicleRepository vehicleRepository;

    /**
     * The default constructor for the VehicleController.
     * It initializes the VehicleRepository instance.
     */
    public VehicleController() {
        getVehicleRepository();
    }

    /**
     * Retrieves the VehicleRepository instance.
     * If the VehicleRepository instance is null, it retrieves the instance from the Repositories singleton and assigns it to the vehicleRepository variable.
     *
     * @return The VehicleRepository instance.
     */
    public VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Creates a vehicle.
     * It creates a new Vehicle object with the provided parameters and adds it to the vehicle repository.
     * If the vehicle is successfully added to the repository, it returns the new Vehicle object.
     * Otherwise, it returns null.
     *
     * @param plateId The plate ID of the vehicle.
     * @param brand The brand of the vehicle.
     * @param model The model of the vehicle.
     * @param type The type of the vehicle.
     * @param tare The tare of the vehicle.
     * @param weight The weight of the vehicle.
     * @param mileage The mileage of the vehicle.
     * @param lastCheckUpKm The last checkup km of the vehicle.
     * @param register_date The register date of the vehicle.
     * @param acquisition_date The acquisition date of the vehicle.
     * @param maintenance_frequency The maintenance frequency of the vehicle.
     * @return The new Vehicle object if it was successfully added to the repository, or null otherwise.
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

}