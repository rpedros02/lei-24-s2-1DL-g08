package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import static org.junit.jupiter.api.Assertions.*;

class

VehicleTest {

    @Test
    void ensureVehicleIsCreatedSuccessfully() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Brand", "Model", "Type", 1.0, 2.0, 1000,100, new Date(1, 1, 2001), new Date(1, 1, 2001), 12);
        assertNotNull(vehicle);
    }

    @Test
    void ensureVehicleLicensePlateIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(null, "Brand", "Model", "Type", 1.0, 2.0, 1000,200, new Date(1, 1, 2001), new Date(1, 1, 2001), 12));
    }

    @Test
    void ensureVehicleRepositoryIsNotEmpty() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        assertFalse(vehicleRepository.isEmpty());
    }

}
