package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

/**
 * This class represents a VehicleCheckup in the system.
 * It manages the information related to a vehicle check-up.
 */
public class VehicleCheckup {
    /**
     * The plate number of the vehicle.
     */
    private String plate;

    /**
     * The date of the check-up.
     */
    private Date date;

    /**
     * The last check-up kilometers.
     */
    private int lastVehicleCheckupKm;

    /**
     * Constructs a new VehicleCheckup object.
     * It initializes the plate, date, and lastVehicleCheckupKm with the specified parameters.
     *
     * @param plate the plate number of the vehicle
     * @param date the date of the check-up
     * @param lastVehicleCheckupKm the last check-up kilometers
     */
    public VehicleCheckup(String plate, Date date, int lastVehicleCheckupKm) {
        this.plate = plate;
        this.date = date;
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
    }

    /**
     * Returns the plate number of the vehicle.
     *
     * @return the plate number of the vehicle
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Sets the plate number of the vehicle.
     *
     * @param plate the plate number of the vehicle
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * Returns the date of the check-up.
     *
     * @return the date of the check-up
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the check-up.
     *
     * @param date the date of the check-up
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the last check-up kilometers.
     *
     * @return the last check-up kilometers
     */
    public int getLastVehicleCheckupKm() {
        return lastVehicleCheckupKm;
    }

    /**
     * Sets the last check-up kilometers.
     *
     * @param lastVehicleCheckupKm the last check-up kilometers
     */
    public void setLastVehicleCheckupKm(int lastVehicleCheckupKm) {
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
    }
}
