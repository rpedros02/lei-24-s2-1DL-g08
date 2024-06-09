package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a VehicleRepository in the system.
 * It manages a list of Vehicle objects and provides methods to manipulate and retrieve them.
 */
public class VehicleRepository {

    /**
     * The list of Vehicle objects.
     */
    private final List<Vehicle> vehicles;

    /**
     * Constructs a VehicleRepository object.
     * It initializes the vehicles list as an empty ArrayList.
     */
    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    /**
     * Adds a Vehicle to the repository with the specified parameters.
     * If the vehicles list is empty or a Vehicle with the same plateId does not exist, it adds the Vehicle and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle to add
     * @param brand the brand of the Vehicle to add
     * @param model the model of the Vehicle to add
     * @param type the type of the Vehicle to add
     * @param tare the tare of the Vehicle to add
     * @param weight the weight of the Vehicle to add
     * @param mileage the mileage of the Vehicle to add
     * @param lastVehicleCheckupKm the lastVehicleCheckupKm of the Vehicle to add
     * @param register_date the register_date of the Vehicle to add
     * @param acquisition_date the acquisition_date of the Vehicle to add
     * @param maintenance_frequency the maintenance_frequency of the Vehicle to add
     * @return true if the Vehicle was added successfully, false otherwise
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
     * Returns the list of all Vehicle objects in the repository.
     *
     * @return the list of all Vehicle objects
     */
    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * Updates the plateId of a Vehicle in the repository.
     * If a Vehicle with the oldPlateId exists, it updates the plateId and returns true.
     * Otherwise, it returns false.
     *
     * @param oldPlateId the old plateId of the Vehicle
     * @param newPlateId the new plateId of the Vehicle
     * @return true if the plateId was updated successfully, false otherwise
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

    /**
     * Updates the brand of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the brand and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newBrand the new brand of the Vehicle
     * @return true if the brand was updated successfully, false otherwise
     */
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
     * Updates a Vehicle in the repository.
     * If a Vehicle with the same plateId as the updatedVehicle exists, it updates the Vehicle and returns true.
     * Otherwise, it returns false.
     *
     * @param updatedVehicle the updated Vehicle
     * @return true if the Vehicle was updated successfully, false otherwise
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
     * Updates the model of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the model and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newModel the new model of the Vehicle
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
     * Updates the type of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the type and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newType the new type of the Vehicle
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
     * Updates the tare of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the tare and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newTare the new tare of the Vehicle
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
     * Updates the weight of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the weight and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newWeight the new weight of the Vehicle
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
     * Updates the mileage of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the mileage and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newMileage the new mileage of the Vehicle
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
     * Updates the registerDate of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the registerDate and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newRegisterDate the new registerDate of the Vehicle
     * @return true if the registerDate was updated successfully, false otherwise
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
     * Updates the acquisitionDate of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the acquisitionDate and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newAcquisitionDate the new acquisitionDate of the Vehicle
     * @return true if the acquisitionDate was updated successfully, false otherwise
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
     * Updates the maintenanceFrequency of a Vehicle in the repository.
     * If a Vehicle with the plateId exists, it updates the maintenanceFrequency and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle
     * @param newMaintenanceFrequency the new maintenanceFrequency of the Vehicle
     * @return true if the maintenanceFrequency was updated successfully, false otherwise
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
     * Removes a Vehicle from the repository.
     * If a Vehicle with the plateId exists, it removes the Vehicle and returns true.
     * Otherwise, it returns false.
     *
     * @param plateId the plateId of the Vehicle to remove
     * @return true if the Vehicle was removed successfully, false otherwise
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
     * Checks if a Vehicle with the specified plateId exists in the repository.
     *
     * @param plateId the plateId of the Vehicle to check
     * @return true if the Vehicle exists, false otherwise
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
     * Checks if the vehicles list is empty.
     *
     * @return true if the vehicles list is empty, false otherwise
     */
    public boolean isEmpty() {
        return vehicles.isEmpty();
    }

    /**
     * Returns a string representation of the VehicleRepository.
     * If the vehicles list is not empty, it returns the string representation of each Vehicle in the list.
     * Otherwise, it returns a message indicating that no vehicles are available.
     *
     * @return a string representation of the VehicleRepository
     */
    @Override
    public String toString() {
        for (Vehicle vehicle : vehicles) {
            return vehicle.toString();
        }
        return "\nNo vehicles available\n";
    }

    /**
     * Returns a Vehicle with the specified plate.
     * If a Vehicle with the plate exists, it returns an Optional containing the Vehicle.
     * Otherwise, it returns an empty Optional.
     *
     * @param plate the plate of the Vehicle to return
     * @return an Optional containing the Vehicle if it exists, an empty Optional otherwise
     */
    public Optional<Vehicle> getVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateId().equals(plate)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    /**
     * Adds a Vehicle to the repository if it is valid.
     * If the Vehicle is valid (does not exist in the list), it adds the Vehicle and returns true.
     * Otherwise, it returns false.
     *
     * @param vehicle the Vehicle to add
     * @return true if the Vehicle was added successfully, false otherwise
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (validateVehicle(vehicle)) {
            return this.vehicles.add(vehicle);
        }
        return false;
    }

    /**
     * Validates a Vehicle.
     * It checks if a Vehicle with the same brand and model already exists in the list.
     *
     * @param vehicle the Vehicle to validate
     * @return true if the Vehicle is valid (does not exist in the list), false otherwise
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