package mdisc;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Collections;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> aresta;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.aresta = new ArrayList<>();
    }

    public void addVertice(String verticeName) {
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

        for (int i = 0; i < sortedArestas.size() - 1; i++) {
            for (int j = 0; j < sortedArestas.size() - i - 1; j++) {
                if (sortedArestas.get(j).getWeight() > sortedArestas.get(j + 1).getWeight()) {
                    Aresta temp = sortedArestas.get(j);
                    sortedArestas.set(j, sortedArestas.get(j + 1));
                    sortedArestas.set(j + 1, temp);
                }
            }
        }

        DisjointSet disjointSet = new DisjointSet(this.vertices.size());

        for (Aresta aresta : sortedArestas) {
            Vertice start = aresta.getStart();
            Vertice end = aresta.getEnd();

            int indexStart = vertices.indexOf(start);
            int indexEnd = vertices.indexOf(end);

            if (indexStart != -1 && indexEnd != -1 &&
                    disjointSet.find(vertices.indexOf(start)) !=
                            disjointSet.find(vertices.indexOf(end))) {
                spanningTree.add(aresta);
                totalCost += aresta.getWeight();
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
            if (start.getName().equals(endVerticeName) && end.getName().equals(startVerticeName)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<Vertice, Vertice> dijkstra(Vertice source) {
        HashMap<Vertice, Double> shortestDistances = new HashMap<>();
        HashMap<Vertice, Vertice> previousVertices = new HashMap<>();
        PriorityQueue<Vertice> queue = new PriorityQueue<>(Comparator.comparingDouble(shortestDistances::get));
        shortestDistances.put(source, 0.0);

        while (!queue.isEmpty()) {
            Vertice current = queue.poll();

            for (Aresta edge : aresta) {
                if (edge.getStart().equals(current)) {
                    Vertice neighbor = edge.getEnd();
                    double newDistance = shortestDistances.get(current) + edge.getWeight();

                    if (!shortestDistances.containsKey(neighbor) || newDistance < shortestDistances.get(neighbor)) {
                        shortestDistances.put(neighbor, newDistance);
                        previousVertices.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return previousVertices;
    }

    public HashMap<Vertice, Vertice> dijkstraMultipleSources(List<Vertice> sources) {
        HashMap<Vertice, Double> shortestDistances = new HashMap<>();
        HashMap<Vertice, Vertice> previousVertices = new HashMap<>();
        PriorityQueue<Vertice> queue = new PriorityQueue<>(Comparator.comparingDouble(shortestDistances::get));

        for (Vertice source : sources) {
            shortestDistances.put(source, 0.0);
            queue.add(source);
        }

        while (!queue.isEmpty()) {
            Vertice current = queue.poll();

            for (Aresta edge : aresta) {
                if (edge.getStart().equals(current)) {
                    Vertice neighbor = edge.getEnd();
                    double newDistance = shortestDistances.get(current) + edge.getWeight();

                    if (!shortestDistances.containsKey(neighbor) || newDistance < shortestDistances.get(neighbor)) {
                        shortestDistances.put(neighbor, newDistance);
                        previousVertices.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return previousVertices;
    }

    public List<Vertice> shortestPathToNearestAssemblyPoint(Vertice start, List<Vertice> assemblyPoints) {
        List<Vertice> shortestPath = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Vertice assemblyPoint : assemblyPoints) {
            HashMap<Vertice, Vertice> previousVertices = dijkstra(start);
            List<Vertice> path = reconstructPath(previousVertices, assemblyPoint);
            double distance = calculateDistance(path);

            if (distance < shortestDistance) {
                shortestDistance = distance;
                shortestPath = path;
            }
        }

        return shortestPath;
    }

    public List<Vertice> reconstructPath(HashMap<Vertice, Vertice> previousVertices, Vertice destination) {
        List<Vertice> path = new ArrayList<>();
        Vertice current = destination;

        while (current != null) {
            path.add(current);
            current = previousVertices.get(current);
        }

        Collections.reverse(path);

        return path;
    }

    public double calculateDistance(List<Vertice> path) {
        double distance = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            Vertice v1 = path.get(i);
            Vertice v2 = path.get(i + 1);
            distance += getEdgeWeight(v1, v2);
        }

        return distance;
    }

    public double getEdgeWeight(Vertice v1, Vertice v2) {
        for (Aresta edge : aresta) {
            if ((edge.getStart().equals(v1) && edge.getEnd().equals(v2)) ||
                    (edge.getStart().equals(v2) && edge.getEnd().equals(v1))) {
                return edge.getWeight();
            }
        }
        return 0.0;
    }
}

