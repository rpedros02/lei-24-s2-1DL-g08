package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a repository for VehicleCheckup objects.
 * It manages the storage and retrieval of VehicleCheckup objects.
 */
public class VehicleCheckupRepository {
    /**
     * The list of VehicleCheckup objects.
     */
    private final List<VehicleCheckup> vehicleCheckups;

    /**
     * Constructs a new VehicleCheckupRepository.
     * It initializes the vehicleCheckups list.
     */
    public VehicleCheckupRepository() {
        vehicleCheckups = new ArrayList<>();
    }

    /**
     * Registers a new VehicleCheckup.
     * It creates a new VehicleCheckup with the specified parameters and adds it to the vehicleCheckups list.
     * If a VehicleCheckup with the same plate and date already exists, it does not add the new VehicleCheckup and returns an empty Optional.
     * Otherwise, it adds the new VehicleCheckup and returns an Optional containing the new VehicleCheckup.
     *
     * @param plate the plate number of the vehicle
     * @param date the date of the check-up
     * @param lastVehicleCheckupKm the last check-up kilometers
     * @return an Optional containing the new VehicleCheckup if it was added successfully, an empty Optional otherwise
     */
    public Optional<VehicleCheckup> registerVehicleCheckup(String plate, Date date, int lastVehicleCheckupKm) {
        VehicleCheckup newVehicleCheckup = new VehicleCheckup(plate, date, lastVehicleCheckupKm);
        for (VehicleCheckup existingVehicleCheckup : vehicleCheckups) {
            if (existingVehicleCheckup.getPlate().equals(plate) && existingVehicleCheckup.getDate().equals(date)) {
                return Optional.empty();
            }
        }
        vehicleCheckups.add(newVehicleCheckup);
        return Optional.of(newVehicleCheckup);
    }

    /**
     * Adds a VehicleCheckup to the vehicleCheckups list.
     *
     * @param vehicleCheckup the VehicleCheckup to add
     */
    public void addVehicleCheckup(VehicleCheckup vehicleCheckup) {
        vehicleCheckups.add(vehicleCheckup);
    }
}



