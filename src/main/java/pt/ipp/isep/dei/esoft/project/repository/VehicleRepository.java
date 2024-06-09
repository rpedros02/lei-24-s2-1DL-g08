/**
 * This class represents a repository for vehicles.
 */
package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private final List<Vehicle> vehicles;

    /**
     * Instantiates a new Vehicle repository.
     */
    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    /**
     * Adds a new vehicle to the repository.
     *
     * @param plateId             the plate id
     * @param brand               the brand
     * @param model               the model
     * @param type                the type
     * @param tare                the tare
     * @param weight              the weight
     * @param mileage             the mileage
     * @param lastVehicleCheckupKm the last vehicle checkup km
     * @param register_date       the register date
     * @param acquisition_date    the acquisition date
     * @param maintenance_frequency the maintenance frequency
     * @return true if the vehicle was added successfully, false otherwise
     */
    public boolean add(String plateId, String brand, String model, String type, double tare, double weight, int mileage, int lastVehicleCheckupKm, Date register_date, Date acquisition_date, int maintenance_frequency) {
        if (vehicles.isEmpty()) {
            Vehicle newVehicle = new Vehicle(plateId, brand, model, type, tare, weight, mileage,lastVehicleCheckupKm, register_date, acquisition_date, maintenance_frequency);
            vehicles.add(newVehicle);
            return true;
        }
        if (exists(plateId)) return false; // if the plateId already exists in the list
        return vehicles.add(new Vehicle(plateId, brand, model, type, tare, weight, mileage,lastVehicleCheckupKm, register_date, acquisition_date, maintenance_frequency));
    }

    /**
     * Gets all vehicles in the repository.
     *
     * @return the list of vehicles
     */
    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * Updates the plate id of a vehicle.
     *
     * @param oldPlateId the old plate id
     * @param newPlateId the new plate id
     * @return true if the plate id was updated successfully, false otherwise
     */
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

    /**
     * Updates a vehicle with new information.
     *
     * @param updatedVehicle the updated vehicle
     * @return true if the vehicle was updated successfully, false otherwise
     */
    public boolean updateVehicle(Vehicle updatedVehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).hasPlate(updatedVehicle.getPlateId())) {
                vehicles.set(i, updatedVehicle);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the model of a vehicle.
     *
     * @param plateId the plate id of the vehicle
     * @param newModel the new model of the vehicle
     * @return true if the model was updated successfully, false otherwise
     */
    public boolean updateModel(String plateId, String newModel) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setModel(newModel);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the type of a vehicle.
     *
     * @param plateId the plate id of the vehicle
     * @param newType the new type of the vehicle
     * @return true if the type was updated successfully, false otherwise
     */
    public boolean updateType(String plateId, String newType) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setType(newType);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the tare of a vehicle.
     *
     * @param plateId the plate id of the vehicle
     * @param newTare the new tare of the vehicle
     * @return true if the tare was updated successfully, false otherwise
     */
    public boolean updateTare(String plateId, double newTare) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setTare(newTare);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the weight of a vehicle.
     *
     * @param plateId the plate id of the vehicle
     * @param newWeight the new weight of the vehicle
     * @return true if the weight was updated successfully, false otherwise
     */
    public boolean updateWeight(String plateId, double newWeight) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setWeight(newWeight);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the mileage of a vehicle.
     *
     * @param plateId the plate id of the vehicle
     * @param newMileage the new mileage of the vehicle
     * @return true if the mileage was updated successfully, false otherwise
     */
    public boolean updateMileage(String plateId, int newMileage) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setMileage(newMileage);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the register date of a vehicle.
     *
     * @param plateId the plate id of the vehicle
     * @param newRegisterDate the new register date of the vehicle
     * @return true if the register date was updated successfully, false otherwise
     */
    public boolean updateRegisterDate(String plateId, Date newRegisterDate) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setRegisterDate(newRegisterDate);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the acquisition date of a vehicle.
     *
     * @param plateId the plate id of the vehicle
     * @param newAcquisitionDate the new acquisition date of the vehicle
     * @return true if the acquisition date was updated successfully, false otherwise
     */
    public boolean updateAcquisitionDate(String plateId, Date newAcquisitionDate) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setAcquisitionDate(newAcquisitionDate);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the maintenance frequency of a vehicle.
     *
     * @param plateId the plate id of the vehicle
     * @param newMaintenanceFrequency the new maintenance frequency of the vehicle
     * @return true if the maintenance frequency was updated successfully, false otherwise
     */
    public boolean updateMaintenanceFrequency(String plateId, int newMaintenanceFrequency) {
        for (Vehicle vehicle : vehicles) {
            if (exists(plateId)) {
                vehicle.setMaintenanceFrequency(newMaintenanceFrequency);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a vehicle from the repository.
     *
     * @param plateId the plate id of the vehicle
     * @return true if the vehicle was removed successfully, false otherwise
     */
    public boolean remove(String plateId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateId().equals(plateId)) {
                vehicles.remove(vehicle);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a vehicle exists in the repository.
     *
     * @param plateId the plate id of the vehicle
     * @return true if the vehicle exists, false otherwise
     */
    public boolean exists(String plateId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateId().equals(plateId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the repository is empty.
     *
     * @return true if the repository is empty, false otherwise
     */
    public boolean isEmpty() {
        return vehicles.isEmpty();
    }

    /**
     * Returns a string representation of the vehicles in the repository.
     *
     * @return a string representation of the vehicles
     */
    @Override
    public String toString() {
        for (Vehicle vehicle : vehicles) {
            return vehicle.toString();
        }
        return "\nNo vehicles available\n";
    }

    /**
     * Returns a vehicle by its plate id.
     * If the vehicle does not exist, it returns an empty Optional.
     *
     * @param plate the plate id of the vehicle
     * @return an Optional containing the vehicle if it exists, an empty Optional otherwise
     */
    public Vehicle getVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateId().equals(plate)) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * Adds a vehicle to the repository.
     * It validates the vehicle before adding it.
     * If the vehicle is not valid, it does not add the vehicle and returns false.
     * If the vehicle is valid, it adds the vehicle and returns true.
     *
     * @param vehicle the vehicle to add
     * @return true if the vehicle was added successfully, false otherwise
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (validateVehicle(vehicle)) {
            return this.vehicles.add(vehicle);
        }
        return false;
    }

    /**
     * Validates a vehicle.
     * It checks if a vehicle with the same brand and model already exists in the repository.
     * If such a vehicle exists, it returns false.
     * If such a vehicle does not exist, it returns true.
     *
     * @param vehicle the vehicle to validate
     * @return true if the vehicle is valid, false otherwise
     */
    private boolean validateVehicle(Vehicle vehicle) {
        for (Vehicle existingVehicle : this.vehicles) {
            if (existingVehicle.getBrand().equals(vehicle.getBrand()) && existingVehicle.getModel().equals(vehicle.getModel())) {
                return false;
            }
        }
        return true;
    }
}