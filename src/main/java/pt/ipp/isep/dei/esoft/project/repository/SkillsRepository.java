package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a SkillsRepository in the system.
 * It manages a list of Skill objects and provides methods to manipulate and retrieve them.
 */
public class SkillsRepository {

    /**
     * The list of Skill objects.
     */
    private final List<Skill> skills;

    /**
     * Constructs a SkillsRepository object.
     * It initializes the skills list as an empty ArrayList.
     */
    public SkillsRepository() {
        this.skills = new ArrayList<>();
    }

    /**
     * Adds a Skill to the repository with the specified skill ID and name.
     * If the skills list is empty or a Skill with the same name does not exist, it adds the Skill and returns true.
     * Otherwise, it returns false.
     *
     * @param skillId the ID of the Skill to add
     * @param name the name of the Skill to add
     * @return true if the Skill was added successfully, false otherwise
     */
    public boolean add(int skillId, String name) {
        if (skills.isEmpty()) {
            skills.add(new Skill(skillId, name));
            return true;
        }
        if (!(exists(name))) {
            skills.add(new Skill(skillId, name));
            return true;
        }
        return false;
    }

    /**
     * Checks if a Skill with the specified ID exists in the repository.
     *
     * @param skillId the ID of the Skill to check
     * @return true if the Skill exists, false otherwise
     */
    public boolean exists(int skillId) {
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a Skill with the specified name exists in the repository.
     *
     * @param name the name of the Skill to check
     * @return true if the Skill exists, false otherwise
     */
    public boolean exists(String name) {
        for (Skill skill : skills) {
            if (skill.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the list of all Skill objects in the repository.
     *
     * @return the list of all Skill objects
     */
    public List<Skill> getSkillsList() {
        return skills;
    }

    /**
     * Returns the list of all Skill objects in the repository.
     *
     * @return the list of all Skill objects
     */
    public List<Skill> getAllSkills() {
        return this.skills;
    }

    /**
     * Returns a Skill object with the specified name.
     * If no Skill has the specified name, it returns null.
     *
     * @param name the name of the Skill to return
     * @return the Skill object with the specified name
     */
    public Skill getSkillByName(String name) {
        if(exists(name)){
            for (Skill skill : skills) {
                if (skill.getName().equals(name)) {
                    return skill;
                }
            }
        }else{
            return null;
        }
        return null;
    }

    /**
     * Returns the next available ID for a Skill.
     * If the skills list is empty, it returns 1.
     * Otherwise, it returns the ID of the last Skill in the list plus 1.
     *
     * @return the next available ID for a Skill
     */
    public int getNextId() {
        if (skills.isEmpty()) {
            return 1;
        }
        return skills.get(skills.size() - 1).getId() + 1;
    }
}