package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {
    String name;


    public Skill(String name) {
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