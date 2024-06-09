package mdisc.sprintc;
import mdisc.sprintb.GNUPlothGraph;
import mdisc.sprintb.GraphPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class US17 {

    public static class Way {
        private final List<String> points;
        private final int distance;

        public Way(List<String> points, int distance) {
            this.points = points;
            this.distance = distance;
        }

        public List<String> getPoints() {
            return points;
        }

        public int getDistance() {
            return distance;
        }
    }

    private static final String DELIMITER = ";";
    private static final String OUTPUT_FORMAT = "%s;%d%n";


    public void main(String[] args) throws IOException {

        String pointsFile = "src/main/java/mdisc/sprintc/datasets/us17_points_names.csv";
        String matrixFile = "src/main/java/mdisc/sprintc/datasets/us17_matrix.csv";
        String outputFile = "src/main/java/mdisc/sprintc/output/us17_allpoints.csv";
        String endPoint = "AP";

        List<String> points = readPoints(pointsFile);
        int[][] matrix = readMatrix(matrixFile, points.size());

        String userPoint = getUserInput();
        if (points.contains(userPoint)) {
            Way shortestWay = calculateShortestWay(points, matrix, userPoint, endPoint);
            writeShortestWay("src/main/java/mdisc/sprintc/output/us17_output.csv", userPoint, shortestWay);
        } else {
            System.out.println("The point you entered does not exist.");
        }

        Map<String, Way> shortestWays = new HashMap<>();
        for (String startPoint : points) {
            if (!startPoint.equals(endPoint)) {
                shortestWays.put(startPoint, calculateShortestWay(points, matrix, startPoint, endPoint));
            }
        }
        writeShortestWays(outputFile, shortestWays);

    }


    private Way calculateShortestWay(List<String> points, int[][] matrix, String start, String end) {
        int size = points.size();
        int startIndex = points.indexOf(start);
        int endIndex = points.indexOf(end);

        int[] distances = new int[size];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startIndex] = 0;

        boolean[] visited = new boolean[size];
        String[] predecessors = new String[size];

        for (int i = 0; i < size; i++) {
            int u = findMinimumDistance(distances, visited);
            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (!visited[v] && matrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE && distances[u] + matrix[u][v] < distances[v]) {
                    distances[v] = distances[u] + matrix[u][v];
                    predecessors[v] = points.get(u);
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String point = end; point != null; point = predecessors[points.indexOf(point)]) {
            path.add(0, point);
        }

        int totalDistance = distances[endIndex];

        return new Way(path, totalDistance);
    }


    private static void writeShortestWays(String filename, Map<String, Way> shortestWays) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (Map.Entry<String, Way> entry : shortestWays.entrySet()) {
                String path = entry.getValue().getPoints().stream()
                        .map(point -> "Vertex: " + point)
                        .collect(Collectors.joining(", "));
                writer.write(String.format("(%s); Cost: %d%n", path, entry.getValue().getDistance()));
            }
        }
    }


    private static List<String> readPoints(String filename) throws IOException {
        String content = Files.readString(Paths.get(filename));
        if (content.startsWith("\uFEFF")) {
            content = content.substring(1);
        }
        return Arrays.asList(content.split(DELIMITER));
    }

    public static int findMinimumDistance(int[] distances, boolean[] visited) {
        int minIndex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && (minIndex == -1 || distances[i] < distances[minIndex])) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static int[][] readMatrix(String filename, int size) throws IOException {
        int[][] matrix = new int[size][size];
        List<String> lines = Files.readAllLines(Paths.get(filename));

        for (int i = 0; i < size; i++) {
            String[] values = lines.get(i).split(DELIMITER);
            for (int j = 0; j < size; j++) {
                String value = values[j];
                if (value.startsWith("\uFEFF")) {
                    value = value.substring(1);
                }
                matrix[i][j] = Integer.parseInt(value);
            }
        }

        return matrix;
    }

    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the point: ");
        return scanner.nextLine();
    }

    private void writeShortestWay(String filename, String point, Way shortestWay) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            String path = shortestWay.getPoints().stream()
                    .map(p -> "Vertex: " + p)
                    .collect(Collectors.joining(", "));
            writer.write(String.format("(%s); Cost: %d%n", path, shortestWay.getDistance()));
        }
    }

    private static List<String> readData(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }

}