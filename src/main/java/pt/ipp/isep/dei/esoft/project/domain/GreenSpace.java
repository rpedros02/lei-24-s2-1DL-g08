package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;

import java.util.Objects;

public class GreenSpace {
    public Collaborator gsm;
    private String name;
    private GreenSpaceTypeRepository type;
    private double area;
    private Address address;

    public GreenSpace(String name, GreenSpaceTypeRepository type, double area, Address address, Collaborator gsm) {
        if (!setData(name, type, area, address, gsm)) {
            throw new IllegalArgumentException("Data is not valid.");
        }

    }

    public GreenSpace(String name) {
        this.name = name;
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


    public boolean setData(String name, GreenSpaceTypeRepository type, double area, Address address, Collaborator gsm) {
        if (!isDataValid(name, type, area, address, gsm)) {
            return false;
        }
        setName(name);
        setType(type);
        setArea(area);
        setAddress(address);
        setGSM(gsm);
        return true;
    }

    private void setGSM(Collaborator gsm) {
        this.gsm = gsm;
    }

    public boolean isDataValid(String name, GreenSpaceTypeRepository type, double area, Address address, Collaborator gsm) {
        return isNameValid(name) && isTypeValid(type) && isAreaValid(area) && isAddressValid(address) && isGsmValid(gsm);
    }

    private boolean isGsmValid(Collaborator gsm) {
        return gsm != null;
    }

    public boolean isNameValid(String name) {
        return name != null;
    }

    public boolean isTypeValid(GreenSpaceTypeRepository type) {
        return type != null;
    }

    public boolean isAreaValid(double area) {
        return area >= 0;
    }

    public boolean isAddressValid(Address address) {
        return address != null;
    }

    public boolean isManagedByGSM() {
        return this.gsm != null;
    }

    public String toString() {
        return STR."GreenSpace{name='\{name}\{'\''}, type='\{type.toString()}\{'\''}, area=\{area}hectares\{'\''}, address=\{address}}";
    }

    public Collaborator getGsm() {
        return gsm;
    }
}
