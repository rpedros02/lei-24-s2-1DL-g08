package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {
    int skillId;
    String name;


    public Skill(int skillId, String name) {
        this.skillId = skillId;
        if (name == null || !isValidName(name)) {
            throw new IllegalArgumentException("Name contains special character and/or numbers:\n");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

}