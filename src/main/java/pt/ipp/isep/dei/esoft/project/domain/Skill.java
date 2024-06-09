package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * This class represents a Skill in the system.
 * It has two fields: skillId and name.
 */
public class Skill {
    /**
     * The id of the Skill.
     */
    int skillId;

    /**
     * The name of the Skill.
     */
    String name;

    /**
     * Constructs a Skill object with the specified skillId and name.
     * If the name is null or contains special characters or numbers, it throws an IllegalArgumentException.
     *
     * @param skillId the id of the Skill
     * @param name the name of the Skill
     */
    public Skill(int skillId, String name) {
        this.skillId = skillId;
        if (name == null || !isValidName(name)) {
            throw new IllegalArgumentException("Name contains special character and/or numbers:\n");
        }
        this.name = name;
    }

    /**
     * Returns the name of this Skill.
     *
     * @return the name of this Skill
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the id of this Skill.
     *
     * @return the id of this Skill
     */
    public int getId() {
        return skillId;
    }

    /**
     * Checks if the specified name is valid.
     * The name must contain only letters and spaces.
     *
     * @param name the name to check
     * @return true if the name is valid, false otherwise
     */
    public boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    /**
     * Returns a string representation of this Skill.
     *
     * @return a string representation of this Skill
     */
    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", name='" + name + '\'' +
                '}';
    }
}