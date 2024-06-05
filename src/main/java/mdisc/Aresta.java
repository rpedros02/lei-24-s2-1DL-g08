package pt.ipp.isep.dei.esoft.project.mdisc;

public class Aresta {
    private final Vertice start;
    private final Vertice end;
    private final double weight;

    public Aresta(Vertice start, Vertice end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertice getStart() {
        return start;
    }

    public Vertice getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }
}
