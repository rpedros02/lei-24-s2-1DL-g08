package mdisc.sprintb;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Ask for the CSV file name
        System.out.println("Choose an option: ");
        System.out.println("1. Calculate the minimum cost tree");
        System.out.println("2. Calculate execution time for multiple files");
        int option = Integer.parseInt(scanner.nextLine());

        if (option == 1) {
            // Calculate the minimum cost tree for a single file
            calculateMinimumCostTree(scanner);
        } else if (option == 2) {
            calculateExecutionTimeMultipleFiles(scanner);
        } else {
            System.out.println("Invalid option. The program will terminate.");
        }

        // Generate execution time graph
        String dataFilePath = "execution_time.csv";
        String outputFilePath = "execution_time.png"; // Output file name
        GNUPlothGraph.generateGNUPlotGraph(dataFilePath, outputFilePath);
        scanner.close();
    }

    private static void calculateMinimumCostTree(Scanner scanner) {
        System.out.print("Enter the name of the .csv file: ");
        String fileName = scanner.nextLine();

        // Read the file and build the graph
        CSVReader csvReader = new CSVReader();
        ArrayList<Object[]> csvData = csvReader.readCsv(fileName);

        // Build the graph
        Grafo graph = new Grafo();
        for (Object[] row : csvData) {
            String startVerticeName = (String) row[0];
            String endVerticeName = (String) row[1];
            double arestaWeight = (double) row[2];

            // Add vertices and edges to the graph
            graph.addVertice(startVerticeName);
            graph.addVertice(endVerticeName);
            graph.addAresta(startVerticeName, endVerticeName, arestaWeight);
        }

        // Execute Kruskal's algorithm
        Grafo.KruskalResult kruskalResult = graph.kruskalMinSpanningTree();

        // Show information about the subgraph and the minimum cost
        System.out.println("Graph Dimension = " + csvData.size() + " : Graph Order = " + graph.getNumVertices() + " : Cost of a Minimum spanning tree = " + kruskalResult.getTotalCost());

        // Write .csv file for the minimum cost tree
        String minimumCostTreeFileName = "minimum_cost_tree.csv";
        CSVReader.writeCSV(kruskalResult.getSpanningTree(), minimumCostTreeFileName, kruskalResult.getTotalCost());

        // Print the graph
        printGraph(csvData);

        // Print the subgraph
        printSubGraph(kruskalResult.getSpanningTree(), kruskalResult.getTotalCost());
    }


    private static void calculateExecutionTimeMultipleFiles(Scanner scanner) {
        System.out.print("Enter the base name of the .csv files: ");
        String baseFileName = scanner.nextLine();

        List<File> files = searchFilesByBaseName(baseFileName);
        if (files.isEmpty()) {
            System.out.println("No files found with the provided base name.");
            return;
        }

        // List to store execution times
        List<Long> executionTimesList = new ArrayList<>();
        List<Integer> inputSizeList = new ArrayList<>();

        // Read the files
        for (File file : files) {
            long executionTime = readFileTime(file.getPath(), inputSizeList);
            executionTimesList.add(executionTime);
        }

        // Create .csv file with execution times
        String executionTimeFileName = "execution_time.csv";
        writeExecutionTimeCSV(inputSizeList, executionTimesList, executionTimeFileName);
    }

    public static List<File> searchFilesByBaseName(String baseFileName) {
        List<File> files = new ArrayList<>();
        int counter = 1;
        while (true) {
            String fileName = baseFileName + "_" + counter + ".csv";
            File file = new File(fileName);
            if (file.exists()) {
                files.add(file);
                counter++;
            } else {
                break;
            }
        }
        return files;
    }

    public static void readFile(String fileName) {
    }

    public static long readFileTime(String fileName, List<Integer> inputSizeList) {
        Grafo graph = new Grafo();

        // JVM warm-up
        Grafo.KruskalResult minimumTree = graph.kruskalMinSpanningTree();

        // Read the file and calculate execution time
        long startTime = System.nanoTime();
        minimumTree = graph.kruskalMinSpanningTree(); // Call again after warm-up
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Calculate the input size of the file
        int inputSize = calculateInputSize(fileName);
        inputSizeList.add(inputSize);
        return executionTime;
    }


    public static int calculateInputSize(String fileName) {
        int numLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                numLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numLines;
    }

    public static void writeExecutionTimeCSV(List<Integer> inputSizeList, List<Long> executionTimesList, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Input size; Execution Time (ns)");

            for (int i = 0; i < inputSizeList.size(); i++) {
                writer.println(inputSizeList.get(i) + ";" + executionTimesList.get(i));
            }
            System.out.println("Execution time .csv file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printGraph(ArrayList<Object[]> csvData) {
        String graphOutputFileName = "graph"; // PNG file name for the graph
        GraphPrinter gp = new GraphPrinter(graphOutputFileName);

        // Build the graph content in DOT format
        for (Object[] aresta : csvData) {
            gp.addln(aresta[0] + " -- " + aresta[1] + "[label=" + aresta[2] + "]");
        }

        // Print the graph
        gp.print();
    }

    public static void printSubGraph(ArrayList<Aresta> arestas, double minimumCost) {
        String subGraphOutputFileName = "minimum_cost_tree_subgraph"; // PNG file name for the subgraph
        GraphPrinter gp = new GraphPrinter(subGraphOutputFileName);

        // Build the subgraph content in DOT format
        gp.addln("label=\"Cost of a minimum spanning tree = " + minimumCost + "\";");
        for (Aresta aresta : arestas) {
            gp.addln(aresta.getStart() + " -- " + aresta.getEnd() + "[label=" + aresta.getWeight() + "]");
        }

        // Print the graph
        gp.print();
    }

}
