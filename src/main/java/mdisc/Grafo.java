package mdisc;
import java.io.FileWriter;
import java.io.IOException;
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
            if (vertice.getVertice().equals(verticeName)) {
                return true;
            }
        }
        return false;
    }

    public Vertice findVertice(String verticeName) {
        for (Vertice vertice : vertices) {
            if (vertice.getVertice().equals(verticeName)) {
                return vertice;
            }
        }
        return null;
    }

    public void sort(List<Aresta> arestas) {
        int n = arestas.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arestas.get(j).compareTo(arestas.get(j + 1)) > 0) {
                    Aresta temp = arestas.get(j);
                    arestas.set(j, arestas.get(j + 1));
                    arestas.set(j + 1, temp);
                }
            }
        }
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
                if (sortedArestas.get(j).getCost() > sortedArestas.get(j + 1).getCost()) {
                    Aresta temp = sortedArestas.get(j);
                    sortedArestas.set(j, sortedArestas.get(j + 1));
                    sortedArestas.set(j + 1, temp);
                }
            }
        }

        DisjointSet disjointSet = new DisjointSet(this.vertices.size());

        for (Aresta aresta : sortedArestas) {
            Vertice start = aresta.getStartVertice();
            Vertice end = aresta.getEndVertice();

            int indexStart = vertices.indexOf(start);
            int indexEnd = vertices.indexOf(end);

            if (indexStart != -1 && indexEnd != -1 &&
                    disjointSet.find(vertices.indexOf(start)) !=
                            disjointSet.find(vertices.indexOf(end))) {
                spanningTree.add(aresta);
                totalCost += aresta.getCost();
                disjointSet.union(vertices.indexOf(start), vertices.indexOf(end));
            }
        }

        return new KruskalResult(spanningTree, totalCost);
    }




    public boolean arestaExists(String startVerticeName, String endVerticeName) {
        for (Aresta aresta : aresta) {
            Vertice start = aresta.getStartVertice();
            Vertice end = aresta.getEndVertice();
            if (start.getVertice().equals(startVerticeName) && end.getVertice().equals(endVerticeName)) {
                return true;
            }
            if (start.getVertice().equals(endVerticeName) && end.getVertice().equals(startVerticeName)) {
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
                if (edge.getStartVertice().equals(current)) {
                    Vertice neighbor = edge.getEndVertice();
                    double newDistance = shortestDistances.get(current) + edge.getCost();

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


    public ArrayList<Vertice> shortestPathToNearestAssemblyPoint(Vertice start, List<Vertice> assemblyPoints) {
        ArrayList<Vertice> shortestPath = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Vertice assemblyPoint : assemblyPoints) {
            HashMap<Vertice, Vertice> previousVertices = dijkstra(start);
            List<Vertice> path = reconstructPath(previousVertices, assemblyPoint);
            double distance = calculateDistance(path);

            if (distance < shortestDistance) {
                shortestDistance = distance;
                shortestPath = new ArrayList<>(path);
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
            if ((edge.getStartVertice().equals(v1) && edge.getEndVertice().equals(v2)) ||
                    (edge.getStartVertice().equals(v2) && edge.getEndVertice().equals(v1))) {
                return edge.getCost();
            }
        }
        return 0.0;
    }

    public void writeDotFile(String filename) {
        StringBuilder dotContent = new StringBuilder();

        dotContent.append("graph Graph1 {\n");
        dotContent.append("  fontname=\"Helvetica,Arial,sans-serif\"\n");
        dotContent.append("  node [fontname=\"Helvetica,Arial,sans-serif\"]\n");
        dotContent.append("  edge [fontname=\"Helvetica,Arial,sans-serif\"]\n");
        dotContent.append("  layout=neato\n");

        for (Aresta aresta : this.aresta) {
            dotContent.append("  ").append(aresta.getStartVertice()).append(" -- ").append(aresta.getEndVertice()).append(" [label=").append(aresta.getCost()).append("]\n");
        }

        dotContent.append("}\n");

        String outputFilePath = "./scr/main/java/mdisc/us17" + filename + ".dot";

        try (FileWriter writer = new FileWriter(outputFilePath)) {
            writer.write(dotContent.toString());
            System.out.println("Dot file written successfully!");
        } catch (IOException e) {
            System.err.println("Error writing dot file: " + e.getMessage());
        }

        graphPng(filename);
    }


    public void graphPng(String filename) {
        try {
            String command =  "dot -Tpng ./scr/main/java/mdisc/us17" + filename + ".dot -o ./scr/main/java/mdisc/us17" + filename + ".png";
            Process process = Runtime.getRuntime().exec(command);

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("New PNG file added");
            } else {
                System.out.println("Error executing the command. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred while executing the command: " + e.getMessage());
        }
    }

}

