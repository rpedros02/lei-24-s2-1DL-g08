package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

/**
 * Represents a vehicle check-up.
 */
public class VehicleCheckup {
    private String plate;
    private Date date;
    private int lastVehicleCheckupKm;

    /**
     * Constructs a new vehicle check-up.
     *
     * @param plate              the plate number of the vehicle
     * @param date               the date of the check-up
     * @param lastVehicleCheckupKm the last check-up kilometers
     */
    public VehicleCheckup(String plate, Date date, int lastVehicleCheckupKm) {
        this.plate = plate;
        this.date = date;
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLastVehicleCheckupKm() {
        return lastVehicleCheckupKm;
    }

    public void setLastVehicleCheckupKm(int lastVehicleCheckupKm) {
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
    }
}
