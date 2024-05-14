package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleCheckupRepository {
    private final List<VehicleCheckup> checkUps;

    public VehicleCheckupRepository() {
        checkUps = new ArrayList<>();
    }

    public Optional<VehicleCheckup> registerCheckUp(String plate, Date date, int lastCheckUpKm) {
        VehicleCheckup newCheckUp = new VehicleCheckup(plate, date, lastCheckUpKm);
        for (VehicleCheckup existingCheckUp : checkUps) {
            if (existingCheckUp.getPlate().equals(plate) && existingCheckUp.getDate().isEqual(date)) {
                return Optional.empty();
            }
        }
        checkUps.add(newCheckUp);
        return Optional.of(newCheckUp);
    }

}