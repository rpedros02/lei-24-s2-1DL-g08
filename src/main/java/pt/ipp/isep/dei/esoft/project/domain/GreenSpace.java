package pt.ipp.isep.dei.esoft.project.domain;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;

import java.util.Objects;

public class GreenSpace {
    private String name;
    private GreenSpaceTypeRepository type;
    private double area;
    private Address address;

    public GreenSpace(String name, GreenSpaceTypeRepository type, double area, Address address) {
        this.name = name;
        this.type = type;
        this.area = area;
        this.address = new Address(address);
    }
    public GreenSpace(String testGreenSpace) {
    }
    public String getName() {
        return name;
    }
    public GreenSpaceTypeRepository getType() {
        return type;
    }
    public double getArea() {
        return area;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(GreenSpaceTypeRepository type) {
        this.type = type;
    }
    public void setArea(double area) {
        this.area = area;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GreenSpace greenSpace = (GreenSpace) o;
        return this.name.equals(greenSpace.name) && this.type.equals(greenSpace.type) && this.area == greenSpace.area && this.address.equals(greenSpace.address);
    }

    public int hashCode() {
        return Objects.hash(name, type, area, address);
    }


    public boolean setData(String name, GreenSpaceTypeRepository type, double area, Address address) {
        if (name == null || type == null || area < 0 || address == null) {
            return false;
        }
        this.name = name;
        this.type = type;
        this.area = area;
        this.address = address;
        return true;
    }

    public boolean isDataValid() {
        return this.name != null && this.type != null && this.area >= 0 && this.address != null;
    }

    public boolean isNameValid(String name) {
        return name != null;
    }

    public boolean isTypeValid(String type) {
        return type != null;
    }

    public boolean isAreaValid(double area) {
        return area >= 0;
    }
    public boolean isAddressValid(Address address) {return address != null;}

    public String toString() {
        return "GreenSpace{" +
                "name='" + name + '\'' +
                ", type='" + type.toString() + '\'' +
                ", area=" + area +
                "hectares" + '\'' +
                ", address=" + address+ "}";
    }

}
