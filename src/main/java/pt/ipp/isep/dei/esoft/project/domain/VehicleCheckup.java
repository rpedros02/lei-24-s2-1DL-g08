package pt.ipp.isep.dei.esoft.project.domain;

public class VehicleCheckup {
    private String plate;
    private Date date;
    private int lastVehicleCheckupKm;

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

    public double getLastVehicleCheckupKm() {
        return lastVehicleCheckupKm;
    }

    public void setLastVehicleCheckupKm(int lastCheckUpKm) {
        this.lastVehicleCheckupKm = lastVehicleCheckupKm;
    }
}