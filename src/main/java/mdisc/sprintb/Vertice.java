package mdisc.sprintb;

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
