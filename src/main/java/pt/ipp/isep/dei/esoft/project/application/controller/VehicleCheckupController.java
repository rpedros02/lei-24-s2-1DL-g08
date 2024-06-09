package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleCheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

/**
 * This class represents a VehicleCheckupController in the system.
 * It manages the operations related to vehicle check-ups.
 */
public class VehicleCheckupController {
    /**
     * The OrganizationRepository object.
     */
    private final OrganizationRepository organizationRepository;
    /**
     * The VehicleCheckupRepository object.
     */
    private final VehicleCheckupRepository vehicleCheckupRepository;
    /**
     * The VehicleRepository object.
     */
    private final VehicleRepository vehicleRepository;

    /**
     * Constructs a VehicleCheckupController object.
     * It initializes the organizationRepository as an instance of OrganizationRepository,
     * the vehicleCheckupRepository as a new VehicleCheckupRepository object,
     * and the vehicleRepository as a new VehicleRepository object.
     */
    public VehicleCheckupController() {
        this.organizationRepository = OrganizationRepository.getInstance();
        this.vehicleCheckupRepository = new VehicleCheckupRepository();
        this.vehicleRepository = new VehicleRepository();
    }

    /**
     * Registers a vehicle check-up.
     * It retrieves the vehicle by its plate from the vehicle repository.
     * If the vehicle is present, it updates the last vehicle check-up km of the vehicle,
     * registers the vehicle check-up in the vehicle check-up repository,
     * and updates the vehicle in the vehicle repository.
     * If the vehicle check-up is registered successfully, it returns true.
     * Otherwise, it returns false.
     *
     * @param plate                the plate of the vehicle
     * @param date                 the date of the vehicle check-up
     * @param lastVehicleCheckupKm the last vehicle check-up km
     * @return true if the vehicle check-up is registered successfully, false otherwise
     */
    public boolean registerVehicleCheckup(String plate, Date date, int lastVehicleCheckupKm) {
        Vehicle vehicle = vehicleRepository.getVehicleByPlate(plate);
        if (vehicle != null) {
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
