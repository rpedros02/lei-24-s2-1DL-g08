package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleCheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Optional;

/**
 * The VehicleCheckupController class is responsible for handling operations related to vehicle checkups.
 * It interacts with the OrganizationRepository, VehicleCheckupRepository, and VehicleRepository to perform operations such as registering a vehicle checkup.
 */
public class VehicleCheckupController {
    /**
     * The OrganizationRepository instance.
     * This instance is used to interact with the organization repository,
     * allowing the controller to perform operations related to organizations.
     */
    private final OrganizationRepository organizationRepository;

    /**
     * The VehicleCheckupRepository instance.
     * This instance is used to interact with the vehicle checkup repository,
     * allowing the controller to perform operations related to vehicle checkups.
     */
    private final VehicleCheckupRepository vehicleCheckupRepository;

    /**
     * The VehicleRepository instance.
     * This instance is used to interact with the vehicle repository,
     * allowing the controller to perform operations related to vehicles.
     */
    private final VehicleRepository vehicleRepository;

    /**
     * The default constructor for the VehicleCheckupController.
     * It initializes the OrganizationRepository, VehicleCheckupRepository, and VehicleRepository instances.
     */
    public VehicleCheckupController() {
        this.organizationRepository = OrganizationRepository.getInstance();
        this.vehicleCheckupRepository = new VehicleCheckupRepository();
        this.vehicleRepository = new VehicleRepository();
    }

    /**
     * Registers a vehicle checkup.
     * It retrieves a Vehicle object by its plate from the vehicle repository. If the vehicle is found, it updates the last vehicle checkup km of the vehicle, registers a vehicle checkup with the provided parameters, updates the vehicle in the vehicle repository, and returns true.
     * If the vehicle is not found, it returns false.
     *
     * @param plate The plate of the vehicle.
     * @param date The date of the vehicle checkup.
     * @param lastVehicleCheckupKm The last vehicle checkup km of the vehicle.
     * @return A boolean indicating the success of the operation.
     */
    public boolean registerVehicleCheckup(String plate, Date date, int lastVehicleCheckupKm) {
        Optional<Vehicle> optVehicle = vehicleRepository.getVehicleByPlate(plate);
        if (optVehicle.isPresent()) {
            Vehicle vehicle = optVehicle.get();
            vehicle.updateLastVehicleCheckupKm(lastVehicleCheckupKm);
            boolean vehicleCheckupRegistered = vehicleCheckupRepository.registerVehicleCheckup(plate, date, lastVehicleCheckupKm).isPresent();
            if (vehicleCheckupRegistered) {
                vehicleRepository.updateVehicle(vehicle);
                return true;
            }
        }
        return false;
    }
}