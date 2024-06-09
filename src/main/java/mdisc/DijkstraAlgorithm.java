package mdisc;

import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithm {
    private List<Integer> vertices;
    private int totalCost;

    public DijkstraAlgorithm(int totalCost, List<Integer> vertices){
        this.totalCost = totalCost;
        this.vertices = vertices;
    }

    public DijkstraAlgorithm(int totalCost){
        this.totalCost = totalCost;
        vertices = new ArrayList<>();
    }

    public DijkstraAlgorithm(int totalCost, int vertex){
        this.totalCost = totalCost;
        this.vertices = new ArrayList<>();
        this.vertices.add(vertex);
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void addVertex(int vertex){
        this.vertices.add(vertex);
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        return getTotalCost() + " " + getVertices();
    }
}
