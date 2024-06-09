package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

/**
 * The controller for handling vehicle-related operations.
 */
public class VehicleController {
    private VehicleRepository vehicleRepository;

    /**
     * Constructs a new VehicleController.
     */
    public VehicleController() {
        getVehicleRepository();
    }

    public VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

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
