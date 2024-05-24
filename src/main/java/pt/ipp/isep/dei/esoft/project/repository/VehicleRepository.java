package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepository {

    private final List<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    public boolean add(String plateId, String brand, String model, String type, double tare, double weight, int mileage, int lastCheckUpKm, Date register_date, Date acquisition_date, int maintenance_frequency) {
        if (vehicles.isEmpty()) {
            Vehicle newVehicle = new Vehicle(plateId, brand, model, type, tare, weight, mileage,lastCheckUpKm, register_date, acquisition_date, maintenance_frequency);
            vehicles.add(newVehicle);
            return true;
        }
        if (exists(plateId)) return false; // if the plateId already exists in the list
        return vehicles.add(new Vehicle(plateId, brand, model, type, tare, weight, mileage,lastCheckUpKm, register_date, acquisition_date, maintenance_frequency));
    }

    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public boolean updatePlateId(String oldPlateId, String newPlateId) {
        for (Vehicle vehicle : vehicles) {
            if (exists(oldPlateId)) {
                vehicle.setPlateId(newPlateId);
                return true;
            }
        }
        return false;
    }

    public boolean updateBrand(String plateId, String newBrand) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setBrand(newBrand);
                return true;
            }
        }
        return false;
    }

    public boolean updateVehicle(Vehicle updatedVehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).hasPlate(updatedVehicle.getPlateId())) {
                vehicles.set(i, updatedVehicle);
                return true;
            }
        }
        return false;
    }

    public boolean updateModel(String plateId, String newModel) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setModel(newModel);
                return true;
            }
        }
        return false;
    }

    public boolean updateType(String plateId, String newType) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setType(newType);
                return true;
            }
        }
        return false;
    }

    public boolean updateTare(String plateId, double newTare) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setTare(newTare);
                return true;
            }
        }
        return false;
    }

    public boolean updateWeight(String plateId, double newWeight) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setWeight(newWeight);
                return true;
            }
        }
        return false;
    }

    public boolean updateMileage(String plateId, int newMileage) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setMileage(newMileage);
                return true;
            }
        }
        return false;
    }

    public boolean updateRegisterDate(String plateId, Date newRegisterDate) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setRegisterDate(newRegisterDate);
                return true;
            }
        }
        return false;
    }

    public boolean updateAcquisitionDate(String plateId, Date newAcquisitionDate) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setAcquisitionDate(newAcquisitionDate);
                return true;
            }
        }
        return false;
    }

    public boolean updateMaintenanceFrequency(String plateId, int newMaintenanceFrequency) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setMaintenanceFrequency(newMaintenanceFrequency);
                return true;
            }
        }
        return false;
    }

    public boolean remove(String plateId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateId().equals(plateId)) {
                vehicles.remove(vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean exists(String plateId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateId().equals(plateId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return vehicles.isEmpty();
    }

    @Override
    public String toString() {
        for (Vehicle vehicle : vehicles) {
            return vehicle.toString();
        }
        return "\nNo vehicles available\n";
    }

    public Optional<Vehicle> getVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateId().equals(plate)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }
    public boolean addVehicle(Vehicle vehicle) {
        if (validateVehicle(vehicle)) {
            return this.vehicles.add(vehicle);
        }
        return false;
    }
    private boolean validateVehicle(Vehicle vehicle) {
        for (Vehicle existingVehicle : this.vehicles) {
            if (existingVehicle.getBrand().equals(vehicle.getBrand()) && existingVehicle.getModel().equals(vehicle.getModel())) {
                return false;
            }
        }
        return true;
    }
}