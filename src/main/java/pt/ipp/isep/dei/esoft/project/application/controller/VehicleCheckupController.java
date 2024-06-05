package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleCheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Optional;

public class VehicleCheckupController {
    private final OrganizationRepository organizationRepository;
    private final VehicleCheckupRepository checkUpRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleCheckupController() {
        OrganizationRepository organizationRepository = OrganizationRepository.getInstance();
        this.checkUpRepository = organizationRepository.getOrganizationByCollaborator();
        this.vehicleRepository = vehicleRepository;
    }

    public boolean registerCheckUp(String plate, Date date, int lastCheckUpKm) {
        Optional<Vehicle> optVehicle = vehicleRepository.getVehicleByPlate(plate);
        if (optVehicle.isPresent()) {
            Vehicle vehicle = optVehicle.get();
            vehicle.updateLastCheckUpKm(lastCheckUpKm);
            boolean checkUpRegistered = checkUpRepository.registerVehicleCheckup(plate, date, lastCheckUpKm).isPresent();
            if (checkUpRegistered) {
                vehicleRepository.updateVehicle(vehicle);
                return true;
            }
        }
        return false;
    }
}