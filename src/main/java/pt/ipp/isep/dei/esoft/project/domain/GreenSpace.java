package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;

import java.util.Objects;

/**
 * This class represents a GreenSpace in the system.
 * It has several fields including name, type, area, address, and gsm (Green Space Manager).
 */
public class GreenSpace {
    /**
     * The Green Space Manager assigned to this GreenSpace.
     */
    public Collaborator gsm;

    /**
     * The name of the GreenSpace.
     */
    private String name;

    /**
     * The type of the GreenSpace.
     */
    private GreenSpaceTypeRepository type;

    /**
     * The area of the GreenSpace in square meters.
     */
    private double area;

    /**
     * The address of the GreenSpace.
     */
    private Address address;

    /**
     * Constructs a GreenSpace object with the specified name, type, area, address, and Green Space Manager.
     * If the data is not valid, it throws an IllegalArgumentException.
     *
     * @param name    the name of the GreenSpace
     * @param type    the type of the GreenSpace
     * @param area    the area of the GreenSpace
     * @param address the address of the GreenSpace
     * @param gsm     the Green Space Manager of the GreenSpace
     */
    public GreenSpace(String name, GreenSpaceTypeRepository type, double area, Address address, Collaborator gsm) {
        if (!setData(name, type, area, address, gsm)) {
            throw new IllegalArgumentException("Data is not valid.");
        }
    }

    /**
     * Constructs a GreenSpace object with the specified name.
     *
     * @param name the name of the GreenSpace
     */
    public GreenSpace(String name) {
        this.name = name;
    }

    // Getters & Setters
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
    // End of Getters & Setters

    /**
     * Checks if this GreenSpace is equal to the specified object.
     * The comparison is based on the name, type, area, and address fields.
     *
     * @param o the object to compare with
     * @return true if this GreenSpace is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GreenSpace greenSpace = (GreenSpace) o;
        return this.name.equals(greenSpace.name) && this.type.equals(greenSpace.type) && this.area == greenSpace.area && this.address.equals(greenSpace.address);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, type, area, address);
    }

    /**
     * Sets the data for this GreenSpace.
     * If the data is not valid, it does not set the fields.
     *
     * @param name    the name of the GreenSpace
     * @param type    the type of the GreenSpace
     * @param area    the area of the GreenSpace
     * @param address the address of the GreenSpace
     * @param gsm     the Green Space Manager of the GreenSpace
     * @return true if the data is valid, false otherwise
     */
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

    /**
     * Sets the Green Space Manager for this GreenSpace.
     *
     * @param gsm the Green Space Manager to set
     */
    private void setGSM(Collaborator gsm) {
        this.gsm = gsm;
    }

    /**
     * Checks if the specified name, type, area, address, and Green Space Manager form a valid GreenSpace.
     *
     * @param name    the name of the GreenSpace
     * @param type    the type of the GreenSpace
     * @param area    the area of the GreenSpace
     * @param address the address of the GreenSpace
     * @param gsm     the Green Space Manager of the GreenSpace
     * @return true if the GreenSpace is valid, false otherwise
     */
    public boolean isDataValid(String name, GreenSpaceTypeRepository type, double area, Address address, Collaborator gsm) {
        return isNameValid(name) && isTypeValid(type) && isAreaValid(area) && isAddressValid(address) && isGsmValid(gsm);
    }

    /**
     * Checks if the specified Green Space Manager is valid.
     * The Green Space Manager must not be null.
     *
     * @param gsm the Green Space Manager to check
     * @return true if the Green Space Manager is valid, false otherwise
     */
    private boolean isGsmValid(Collaborator gsm) {
        return gsm != null;
    }

    /**
     * Checks if the specified name is valid.
     * The name must not be null.
     *
     * @param name the name to check
     * @return true if the name is valid, false otherwise
     */
    public boolean isNameValid(String name) {
        return name != null;
    }

    /**
     * Checks if the specified type is valid.
     * The type must not be null.
     *
     * @param type the type to check
     * @return true if the type is valid, false otherwise
     */
    public boolean isTypeValid(GreenSpaceTypeRepository type) {
        return type != null;
    }

    /**
     * Checks if the specified area is valid.
     * The area must be greater than or equal to 0.
     *
     * @param area the area to check
     * @return true if the area is valid, false otherwise
     */
    public boolean isAreaValid(double area) {
        return area >= 0;
    }

    /**
     * Checks if the specified address is valid.
     * The address must not be null.
     *
     * @param address the address to check
     * @return true if the address is valid, false otherwise
     */
    public boolean isAddressValid(Address address) {
        return address != null;
    }

    /**
     * Checks if this GreenSpace is managed by a Green Space Manager.
     *
     * @return true if this GreenSpace is managed by a Green Space Manager, false otherwise
     */
    public boolean isManagedByGSM() {
        return this.gsm != null;
    }

    /**
     * Returns a string representation of this GreenSpace.
     *
     * @return a string representation of this GreenSpace
     */
    @Override
    public String toString() {
        return STR."GreenSpace{name='\{name}\{'\''}, type='\{type.toString()}\{'\''}, area=\{area}hectares\{'\''}, address=\{address}}";
    }

    /**
     * Returns the Green Space Manager of this GreenSpace.
     *
     * @return the Green Space Manager of this GreenSpace
     */
    public Object getGsm() {
        return gsm;
    }
}
