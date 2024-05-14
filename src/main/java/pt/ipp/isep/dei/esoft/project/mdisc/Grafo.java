package pt.ipp.isep.dei.esoft.project.mdisc;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> aresta;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.aresta = new ArrayList<>();
    }

    public void addVertice(String verticeName) {
        // Checks if the vertex already exists before adding it
        if (!verticeExists(verticeName)) {
            vertices.add(new Vertice(verticeName));
        }
    }

    public void addAresta(String startVerticeName, String endVerticeName, double weight) {
        Vertice start = findVertice(startVerticeName);
        Vertice end = findVertice(endVerticeName);
        if (start != null && end != null) {
            aresta.add(new Aresta(start, end, weight));
        }
    }

    public boolean verticeExists(String verticeName) {
        for (Vertice vertice : vertices) {
            if (vertice.getName().equals(verticeName)) {
                return true;
            }
        }
        return false;
    }

    public Vertice findVertice(String verticeName) {
        for (Vertice vertice : vertices) {
            if (vertice.getName().equals(verticeName)) {
                return vertice;
            }
        }
        return null;
    }

    public int getNumVertices() {
        return vertices.size();
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public ArrayList<Aresta> getAresta() {
        return aresta;
    }

    public class KruskalResult {
        private ArrayList<Aresta> spanningTree;
        private double totalCost;

        public KruskalResult(ArrayList<Aresta> spanningTree, double totalCost) {
            this.spanningTree = spanningTree;
            this.totalCost = totalCost;
        }

        public ArrayList<Aresta> getSpanningTree() {
            return spanningTree;
        }

        public double getTotalCost() {
            return totalCost;
        }
    }

    public KruskalResult kruskalMinSpanningTree() {
        ArrayList<Aresta> spanningTree = new ArrayList<>();
        ArrayList<Aresta> sortedArestas = new ArrayList<>(this.aresta);
        double totalCost = 0.0;

        // Manual sorting of edges by weight
        for (int i = 0; i < sortedArestas.size() - 1; i++) {
            for (int j = 0; j < sortedArestas.size() - i - 1; j++) {
                if (sortedArestas.get(j).getWeight() > sortedArestas.get(j + 1).getWeight()) {
                    // Swap edges positions
                    Aresta temp = sortedArestas.get(j);
                    sortedArestas.set(j, sortedArestas.get(j + 1));
                    sortedArestas.set(j + 1, temp);
                }
            }
        }

        // Set to store visited vertices
        DisjointSet disjointSet = new DisjointSet(this.vertices.size());

        for (Aresta edge : sortedArestas) {
            Vertice start = aresta.getStart();
            Vertice end = aresta.getEnd();

            // Check that adding this edge doesn't create a cycle
            int indexStart = vertices.indexOf(start);
            int indexEnd = vertices.indexOf(end);

            if (indexStart != -1 && indexEnd != -1 &&
                    disjointSet.find(vertices.indexOf(start)) !=
                            disjointSet.find(vertices.indexOf(end))) {
                spanningTree.add(aresta);
                totalCost += edge.getWeight();
                disjointSet.union(vertices.indexOf(start), vertices.indexOf(end));
            }
        }

        return new KruskalResult(spanningTree, totalCost);
    }

    public boolean arestaExists(String startVerticeName, String endVerticeName) {
        for (Aresta aresta : aresta) {
            Vertice start = aresta.getStart();
            Vertice end = aresta.getEnd();
            if (start.getName().equals(startVerticeName) && end.getName().equals(endVerticeName)) {
                return true;
            }
            // If the edges are bidirectional (origin->destination and destination->origin), we can also check if the aresta exists in the opposite direction.
            if (start.getName().equals(endVerticeName) && end.getName().equals(startVerticeName)) {
                return true;
            }
        }
        return false;
    }
}