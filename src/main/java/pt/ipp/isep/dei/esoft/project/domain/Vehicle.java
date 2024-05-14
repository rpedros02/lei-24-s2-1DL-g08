package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    public String plateId;
    private String brand;
    private String model;
    private String type;
    private double tare;
    private double weight;
    private int mileage;
    private int lastCheckUpKm;
    private Date registerDate;
    private Date acquisitionDate;
    private int maintenanceFrequency;
    private List<VehicleCheckup> checkups;

    public Vehicle(String plateId, String brand, String model, String type, double tare, double weight, int mileage, int lastCheckUpKm, Date registerDate, Date acquisitionDate, int maintenanceFrequency) {
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
        this.lastCheckUpKm = lastCheckUpKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequency = maintenanceFrequency;
        this.checkups = new ArrayList<>();
    }

    public boolean validateArgs() {
        return plateId != null && brand != null && model != null && type != null && tare != 0 && weight != 0 && mileage != 0 && lastCheckUpKm != 0 && registerDate != null && acquisitionDate != null && maintenanceFrequency != 0;
    }

    public boolean hasPlate(String plate){
        return this.plateId.equals(plate);
    }

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTare() {
        return tare;
    }

    public void setTare(double tare) {
        this.tare = tare;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date register_date) {
        this.registerDate = register_date;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisition_date) {
        this.acquisitionDate = acquisition_date;
    }

    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    public void setMaintenanceFrequency(int maintenance_frequency) {
        this.maintenanceFrequency = maintenance_frequency;
    }

    public Vehicle clone() {
        return new Vehicle(this.plateId, this.brand, this.model, this.type, this.tare, this.weight, this.mileage, this.lastCheckUpKm, this.registerDate.clone(), this.acquisitionDate.clone(), this.maintenanceFrequency);
    }

    @Override

    public String toString() {
        return "\n-----\nID: " + plateId + "\nBrand: " + brand + "\nModel: " + model + "\nType: " + type + "\nTare: " + tare + "\nWeight: " + weight + "\nMileage: " + mileage + "\nLast Check-Up at: " + lastCheckUpKm + " kms\nRegister Date: " + registerDate.toString() + "\nAcquisition Date: " + acquisitionDate.toString() + "\nMaintenance Frequency: " + maintenanceFrequency + "\n-----\n";
    }

    public void updateLastCheckUpKm(int lastCheckUpKm) {
        this.lastCheckUpKm = lastCheckUpKm;
    }

    public int getLastCheckUpKm() {
        return lastCheckUpKm;
    }

}