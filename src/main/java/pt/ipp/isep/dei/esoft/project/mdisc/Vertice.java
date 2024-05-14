package pt.ipp.isep.dei.esoft.project.mdisc;

import java.util.Objects;

public class Vertice {
    private final String name;

    public Vertice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}