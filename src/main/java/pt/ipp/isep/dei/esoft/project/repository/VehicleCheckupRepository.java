package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a VehicleCheckupRepository in the system.
 * It manages a list of VehicleCheckup objects and provides methods to manipulate and retrieve them.
 */
public class VehicleCheckupRepository {
    /**
     * The list of VehicleCheckup objects.
     */
    private final List<VehicleCheckup> vehicleCheckups;

    /**
     * Constructs a VehicleCheckupRepository object.
     * It initializes the vehicleCheckups list as an empty ArrayList.
     */
    public VehicleCheckupRepository() {
        vehicleCheckups = new ArrayList<>();
    }

    /**
     * Registers a VehicleCheckup in the repository.
     * It creates a new VehicleCheckup with the specified plate, date, and lastVehicleCheckupKm.
     * If a VehicleCheckup with the same plate and date already exists in the repository, it returns an empty Optional.
     * Otherwise, it adds the new VehicleCheckup to the repository and returns an Optional containing the VehicleCheckup.
     *
     * @param plate the plate of the VehicleCheckup to register
     * @param date the date of the VehicleCheckup to register
     * @param lastVehicleCheckupKm the lastVehicleCheckupKm of the VehicleCheckup to register
     * @return an Optional containing the registered VehicleCheckup if successful, or an empty Optional if not
     */
    public Optional<VehicleCheckup> registerVehicleCheckup(String plate, Date date, int lastVehicleCheckupKm) {
        VehicleCheckup newVehicleCheckup = new VehicleCheckup(plate, date, lastVehicleCheckupKm);
        for (VehicleCheckup existingVehicleCheckup : vehicleCheckups) {
            if (existingVehicleCheckup.getPlate().equals(plate) && existingVehicleCheckup.getDate().isEqual(date)) {
                return Optional.empty();
            }
        }
        vehicleCheckups.add(newVehicleCheckup);
        return Optional.of(newVehicleCheckup);
    }

    /**
     * Adds a VehicleCheckup to the repository.
     *
     * @param vehicleCheckup the VehicleCheckup to add
     */
    public void addVehicleCheckup(VehicleCheckup vehicleCheckup) {
        vehicleCheckups.add(vehicleCheckup);
    }
}