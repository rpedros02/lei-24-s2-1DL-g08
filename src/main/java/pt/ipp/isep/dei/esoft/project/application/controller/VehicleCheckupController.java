package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleCheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Optional;

public class VehicleCheckupController {
    private final OrganizationRepository organizationRepository;
    private final VehicleCheckupRepository vehicleCheckupRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleCheckupController() {
        this.organizationRepository = OrganizationRepository.getInstance();
        this.vehicleCheckupRepository = new VehicleCheckupRepository();
        this.vehicleRepository = new VehicleRepository();
    }

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