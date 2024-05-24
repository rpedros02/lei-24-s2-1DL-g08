package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleCheckupRepository {
    private final List<VehicleCheckup> vehicleCheckups;

    public VehicleCheckupRepository() {
        vehicleCheckups = new ArrayList<>();
    }

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
    public void addVehicleCheckup(VehicleCheckup vehicleCheckup) {
        vehicleCheckups.add(vehicleCheckup);
    }

}