package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillsRepository {

    private final List<Skill> skills;

    public SkillsRepository() {
        this.skills = new ArrayList<>();
    }

    public boolean add(int skillId,String name) {
        if (skills.isEmpty()) {
            skills.add(new Skill(skillId,name)); // if it's the first entry in the list
            return true;
        }

        if (!(exists(name))) {
            skills.add(new Skill(skillId,name));
            return true;
        }
        return false;
    }


    public boolean exists(String name) {
        for (Skill skill : skills) {
            if (skill.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Skill> getSkillsList() {
        return skills;
    }

}