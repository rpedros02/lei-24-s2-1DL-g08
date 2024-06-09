package pt.ipp.isep.dei.esoft.project.domain;

/**
 * This class represents a VehicleCheckup in the system.
 * It has several fields including plate, date, and lastVehicleCheckupKm.
 */
public class VehicleCheckup {
    /**
     * The plate of the VehicleCheckup.
     */
    private String plate;

    /**
     * The date of the VehicleCheckup.
     */
    private Date date;

    /**
     * The last vehicle checkup km of the VehicleCheckup.
     */
    private int lastVehicleCheckupKm;

    /**
     * Constructs a VehicleCheckup object with the specified plate, date, and lastVehicleCheckupKm.
     *
     * @param plate the plate of the VehicleCheckup
     * @param date the date of the VehicleCheckup
     * @param lastVehicleCheckupKm the last vehicle checkup km of the VehicleCheckup
     */
    public VehicleCheckup(String plate, Date date, int lastVehicleCheckupKm) {
        this.plate = plate;
        this.date = date;
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
    }

    /**
     * Returns the plate of this VehicleCheckup.
     *
     * @return the plate of this VehicleCheckup
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Sets the plate of this VehicleCheckup.
     *
     * @param plate the plate to set
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * Returns the date of this VehicleCheckup.
     *
     * @return the date of this VehicleCheckup
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of this VehicleCheckup.
     *
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the last vehicle checkup km of this VehicleCheckup.
     *
     * @return the last vehicle checkup km of this VehicleCheckup
     */
    public double getLastVehicleCheckupKm() {
        return lastVehicleCheckupKm;
    }

    /**
     * Sets the last vehicle checkup km of this VehicleCheckup.
     *
     * @param lastCheckUpKm the last vehicle checkup km to set
     */
    public void setLastVehicleCheckupKm(int lastCheckUpKm) {
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
    }
}