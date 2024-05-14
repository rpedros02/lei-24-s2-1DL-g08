package pt.ipp.isep.dei.esoft.project.domain;

public class VehicleCheckup {
    private String plate;
    private Date date;
    private int lastCheckUpKm;

    public VehicleCheckup(String plate, Date date, int lastCheckUpKm) {
        this.plate = plate;
        this.date = date;
        this.lastCheckUpKm = lastCheckUpKm;
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

    public double getLastCheckUpKm() {
        return lastCheckUpKm;
    }

    public void setLastCheckUpKm(int lastCheckUpKm) {
        this.lastCheckUpKm = lastCheckUpKm;
    }
}