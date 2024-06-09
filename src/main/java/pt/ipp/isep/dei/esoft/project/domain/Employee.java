package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents an Employee in the system.
 * It has five fields: email, name, position, phone, and skills.
 */
public class Employee {
    /**
     * The email of the employee. This field is final and can't be changed after the object is created.
     */
    private final String email;

    /**
     * The name of the employee.
     */
    private String name;

    /**
     * The position of the employee.
     */
    private String position;

    /**
     * The phone number of the employee.
     */
    private String phone;

    /**
     * The list of skills that the employee has.
     */
    private List<Skill> skills;

    /**
     * Constructs an Employee object with the specified email.
     * It also initializes the skills list as an empty ArrayList.
     *
     * @param email the email of the employee
     */
    public Employee(String email) {
        this.email = email;
        this.skills = new ArrayList<>();
    }

    /**
     * Checks if this Employee is equal to the specified object.
     * The comparison is based on the email field.
     *
     * @param o the object to compare with
     * @return true if this Employee is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return email.equals(employee.email);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Checks if this Employee has the specified email.
     *
     * @param email the email to check
     * @return true if this Employee has the specified email, false otherwise
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * Returns a clone of this Employee.
     * The clone has the same email as this Employee.
     *
     * @return a clone of this Employee
     */
    public Employee clone() {
        return new Employee(this.email);
    }
}