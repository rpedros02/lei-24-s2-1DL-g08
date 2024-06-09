package mdisc;

public class Aresta implements Comparable<Aresta>{

    private Vertice startVertice;
    private Vertice endVertice;

    private float cost;


    private static final Vertice START_POINT = new Vertice("startVertice");
    private static final Vertice END_POINT = new Vertice("endVertice");
    private static final int K= 0;


    public Aresta() {
        this.startVertice = START_POINT;
        this.endVertice = END_POINT;
        this.cost = K;
    }

    public Aresta(Vertice startVertex, Vertice endVertice, double cost){
        this.startVertice = startVertex;
        this.endVertice = this.endVertice;
        this.cost = (float) cost;
    }

    public String toString() {
        return getStartVertice() + " " + getEndVertice() + " " + getCost();
    }

    public Vertice getStartVertice() {
        return startVertice;
    }

    public void setStartVertice(Vertice startVertice) {
        this.startVertice = startVertice;
    }

    public Vertice getEndVertice() {
        return endVertice;
    }

    public void setEndVertice(Vertice endVertice) {
        this.endVertice = endVertice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = (float) cost;
    }

    public int compareTo(mdisc.Aresta otherAresta) {

        if (this.cost < otherAresta.cost) {
            return -1;
        } else if (this.cost > otherAresta.cost) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean hasVertice(Vertice vertice) {
        return this.startVertice.equals(vertice) || this.endVertice.equals(vertice);
    }
}
