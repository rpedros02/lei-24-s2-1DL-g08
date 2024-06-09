package mdisc.sprintc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class US18 {

    public class Path {
        private final List<String> points;
        private final int distance;

        public Path(List<String> points, int distance) {
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

        String pointsFile = "src/main/java/mdisc/sprintc/datasets/us18_points_names.csv";
        String matrixFile = "src/main/java/mdisc/sprintc/datasets/us18_matrix.csv";
        String outputFile = "src/main/java/mdisc/sprintc/output/us18_allpoints.csv";

        List<String> points = readPoints(pointsFile);
        int[][] matrix = readMatrix(matrixFile, points.size());

        String userPoint = getUserInput();
        if (points.contains(userPoint)) {
            Path shortestPath = calculateShortestPath(points, matrix, userPoint);
            writeShortestPath("src/main/java/mdisc/sprintc/output/us18_output.csv", userPoint, shortestPath);
        } else {
            System.out.println("The point you entered does not exist.");
        }

        Map<String, Path> shortestPaths = new HashMap<>();
        for (String startPoint : points) {
            if (!startPoint.startsWith("AP")) {
                shortestPaths.put(startPoint, calculateShortestPath(points, matrix, startPoint));
            }
        }

        writeShortestPaths(outputFile, shortestPaths);

    }


    private Path calculateShortestPath(List<String> points, int[][] matrix, String start) {
        int size = points.size();
        int startIndex = points.indexOf(start);

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

        int minDistance = Integer.MAX_VALUE;
        String nearestAP = null;
        for (String point : points) {
            if (point.startsWith("AP")) {
                int apIndex = points.indexOf(point);
                if (distances[apIndex] < minDistance) {
                    minDistance = distances[apIndex];
                    nearestAP = point;
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String point = nearestAP; point != null; point = predecessors[points.indexOf(point)]) {
            path.add(0, point);
        }

        return new Path(path, minDistance);
    }

    private static void writeShortestPaths(String filename, Map<String, Path> shortestPaths) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (Map.Entry<String, Path> entry : shortestPaths.entrySet()) {
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

    private static int findMinimumDistance(int[] distances, boolean[] visited) {
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
        System.out.println("Enter the number of the point: ");
        return scanner.nextLine();
    }



    private void writeShortestPath(String filename, String point, Path shortestPath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            String path = shortestPath.getPoints().stream()
                    .map(p -> "Vertex: " + p)
                    .collect(Collectors.joining(", "));
            writer.write(String.format("(%s); Cost: %d%n", path, shortestPath.getDistance()));
        }
    }


}