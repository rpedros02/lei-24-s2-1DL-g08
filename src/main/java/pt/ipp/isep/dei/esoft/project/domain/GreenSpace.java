package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpace {
    private String name;
    private int locationCoordinates;
    private double area;
    public GreenSpace(String name, int locationCoordinates, double area) {
        this.name = name;
        this.locationCoordinates = locationCoordinates;
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(int locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
