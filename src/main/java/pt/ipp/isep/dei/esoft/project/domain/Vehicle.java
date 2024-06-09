package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Vehicle in the system.
 * It has several fields including plateId, brand, model, type, tare, weight, mileage, lastVehicleCheckupKm, registerDate, acquisitionDate, maintenanceFrequency, and vehicleCheckups.
 */
public class Vehicle {
    /**
     * The plate id of the Vehicle.
     */
    public String plateId;

    /**
     * The brand of the Vehicle.
     */
    private String brand;

    /**
     * The model of the Vehicle.
     */
    private String model;

    /**
     * The type of the Vehicle.
     */
    private String type;

    /**
     * The tare of the Vehicle.
     */
    private double tare;

    /**
     * The weight of the Vehicle.
     */
    private double weight;

    /**
     * The mileage of the Vehicle.
     */
    private int mileage;

    /**
     * The last vehicle checkup km of the Vehicle.
     */
    private int lastVehicleCheckupKm;

    /**
     * The register date of the Vehicle.
     */
    private Date registerDate;

    /**
     * The acquisition date of the Vehicle.
     */
    private Date acquisitionDate;

    /**
     * The maintenance frequency of the Vehicle.
     */
    private int maintenanceFrequency;

    /**
     * The vehicle checkups of the Vehicle.
     */
    private List<VehicleCheckup> vehicleCheckups;

    /**
     * Constructs a Vehicle object with the specified plateId, brand, model, type, tare, weight, mileage, lastVehicleCheckupKm, registerDate, acquisitionDate, and maintenanceFrequency.
     * It initializes the vehicleCheckups as an empty ArrayList.
     * If the arguments are not valid, it throws an IllegalArgumentException.
     *
     * @param plateId the plate id of the Vehicle
     * @param brand the brand of the Vehicle
     * @param model the model of the Vehicle
     * @param type the type of the Vehicle
     * @param tare the tare of the Vehicle
     * @param weight the weight of the Vehicle
     * @param mileage the mileage of the Vehicle
     * @param lastVehicleCheckupKm the last vehicle checkup km of the Vehicle
     * @param registerDate the register date of the Vehicle
     * @param acquisitionDate the acquisition date of the Vehicle
     * @param maintenanceFrequency the maintenance frequency of the Vehicle
     */
    public Vehicle(String plateId, String brand, String model, String type, double tare, double weight, int mileage, int lastVehicleCheckupKm, Date registerDate, Date acquisitionDate, int maintenanceFrequency) {
        if (validateArgs()) {
            throw new IllegalArgumentException("Null arguments: \n");
        }
        this.plateId = plateId;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.tare = tare;
        this.weight = weight;
        this.mileage = mileage;
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequency = maintenanceFrequency;
        this.vehicleCheckups = new ArrayList<>();
    }

    /**
     * Validates the arguments of the Vehicle.
     * The arguments are valid if none of them are null and the tare, weight, mileage, lastVehicleCheckupKm, and maintenanceFrequency are not 0.
     *
     * @return true if the arguments are valid, false otherwise
     */
    public boolean validateArgs() {
        return plateId != null && brand != null && model != null && type != null && tare != 0 && weight != 0 && mileage != 0 && lastVehicleCheckupKm != 0 && registerDate != null && acquisitionDate != null && maintenanceFrequency != 0;
    }

    /**
     * Checks if this Vehicle has the specified plate.
     *
     * @param plate the plate to check
     * @return true if this Vehicle has the specified plate, false otherwise
     */
    public boolean hasPlate(String plate){
        return this.plateId.equals(plate);
    }

    // GETTERS & SETTERS

    /**
     * Returns the plate id of this Vehicle.
     *
     * @return the plate id of this Vehicle
     */
    public String getPlateId() {
        return plateId;
    }

    /**
     * Sets the plate id of this Vehicle.
     *
     * @param plateId the plate id to set
     */
    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    /**
     * Returns the brand of this Vehicle.
     *
     * @return the brand of this Vehicle
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of this Vehicle.
     *
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the model of this Vehicle.
     *
     * @return the model of this Vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of this Vehicle.
     *
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the type of this Vehicle.
     *
     * @return the type of this Vehicle
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of this Vehicle.
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the tare of this Vehicle.
     *
     * @return the tare of this Vehicle
     */
    public double getTare() {
        return tare;
    }

    /**
     * Sets the tare of this Vehicle.
     *
     * @param tare the tare to set
     */
    public void setTare(double tare) {
        this.tare = tare;
    }

    /**
     * Returns the weight of this Vehicle.
     *
     * @return the weight of this Vehicle
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of this Vehicle.
     *
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns the mileage of this Vehicle.
     *
     * @return the mileage of this Vehicle
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Sets the mileage of this Vehicle.
     *
     * @param mileage the mileage to set
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * Returns the register date of this Vehicle.
     *
     * @return the register date of this Vehicle
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * Sets the register date of this Vehicle.
     *
     * @param register_date the register date to set
     */
    public void setRegisterDate(Date register_date) {
        this.registerDate = register_date;
    }

    /**
     * Returns the acquisition date of this Vehicle.
     *
     * @return the acquisition date of this Vehicle
     */
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Sets the acquisition date of this Vehicle.
     *
     * @param acquisition_date the acquisition date to set
     */
    public void setAcquisitionDate(Date acquisition_date) {
        this.acquisitionDate = acquisition_date;
    }

    /**
     * Returns the maintenance frequency of this Vehicle.
     *
     * @return the maintenance frequency of this Vehicle
     */
    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    /**
     * Sets the maintenance frequency of this Vehicle.
     *
     * @param maintenance_frequency the maintenance frequency to set
     */
    public void setMaintenanceFrequency(int maintenance_frequency) {
        this.maintenanceFrequency = maintenance_frequency;
    }

    /**
     * Creates and returns a copy of this Vehicle.
     * The clone has the same plateId, brand, model, type, tare, weight, mileage, lastVehicleCheckupKm, registerDate, acquisitionDate, and maintenanceFrequency as this Vehicle.
     *
     * @return a clone of this Vehicle
     */
    public Vehicle clone() {
        return new Vehicle(this.plateId, this.brand, this.model, this.type, this.tare, this.weight, this.mileage, this.lastVehicleCheckupKm, this.registerDate.clone(), this.acquisitionDate.clone(), this.maintenanceFrequency);
    }

    /**
     * Returns a string representation of this Vehicle.
     * The string representation includes the plateId, brand, model, type, tare, weight, mileage, lastVehicleCheckupKm, registerDate, acquisitionDate, and maintenanceFrequency.
     *
     * @return a string representation of this Vehicle
     */
    @Override
    public String toString() {
        return "\n-----\nID: " + plateId + "\nBrand: " + brand + "\nModel: " + model + "\nType: " + type + "\nTare: " + tare + "\nWeight: " + weight + "\nMileage: " + mileage + "\nLast Check-Up at: " + lastVehicleCheckupKm + " kms\nRegister Date: " + registerDate.toString() + "\nAcquisition Date: " + acquisitionDate.toString() + "\nMaintenance Frequency: " + maintenanceFrequency + "\n-----\n";
    }

    /**
     * Updates the last vehicle checkup km of this Vehicle.
     *
     * @param lastVehicleCheckupKm the last vehicle checkup km to set
     */
    public void updateLastVehicleCheckupKm(int lastVehicleCheckupKm) {
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
    }

    /**
     * Returns the last vehicle checkup km of this Vehicle.
     *
     * @return the last vehicle checkup km of this Vehicle
     */
    public int getLastVehicleCheckupKm() {
        return lastVehicleCheckupKm;
    }

    public boolean isInNeedOfCheckUp() {
        return (mileage - lastVehicleCheckupKm) >= maintenanceFrequency;
    }
}