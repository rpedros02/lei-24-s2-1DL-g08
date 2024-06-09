package mdisc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CSVReader {
    public ArrayList<Object[]> readCsv(String fileName) {
        ArrayList<Object[]> data = new ArrayList<>();

        while (true) {
            File file = new File(fileName);

            if (file.exists() && file.isFile()) {
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {


                    String line;
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        line = line.replaceAll("\\p{C}", "");

                        String[] fields = line.split(";");
                        Object[] objFields = new Object[3];
                        objFields[0] = fields[0].trim(); // start vertex
                        objFields[1] = fields[1].trim(); // end vertex
                        objFields[2] = Double.parseDouble(fields[2].trim()); // edge weight (Double)
                        data.add(objFields);
                    }
                    break;
                } catch (IOException e) {
                    System.out.println("ERROR FILE.");
                } catch (NumberFormatException e) {
                    System.out.println("Error converting to Double.");
                }
            } else {
                System.out.println("File not found. Check the name and try again.");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the name of the .csv file: ");
                fileName = scanner.nextLine();
            }
        }
        return data;
    }

    public List<Aresta> importEdges(String csvFilePath) throws FileNotFoundException {
        List<Aresta> arestas = new ArrayList<>();
        File file = new File(csvFilePath);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] column = line.split(";");
            String startVertice1 = column[0];
            String endVertice2 = column[1];
            Float cost = Float.parseFloat(column[2]);

            Vertice startVertice = new Vertice(startVertice1);
            Vertice endVertice = new Vertice(endVertice2);
            Aresta aresta = new Aresta(startVertice, endVertice, cost);
            arestas.add(aresta);
        }

        scanner.close();
        return arestas;
    }


    public List<String> importNames(String csvFilePath) throws FileNotFoundException {
        List<String> rowData = new ArrayList<>();
        File file = new File(csvFilePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] columns = line.split(";");

            for (String column : columns) {
                rowData.add(column.trim());
            }
        }

        scanner.close();
        return rowData;
    }

    public static void writeCSV(ArrayList<Aresta> tree, String fileName, double minimumCost) {
        try (FileWriter writer = new FileWriter(fileName)) {


            for (Aresta aresta : tree) {
                writer.append(aresta.getStartVertice() + ";" + aresta.getEndVertice() + ";" + aresta.getCost() + "\n");
            }

            writer.write("\nCost of a minimum spanning tree = " + minimumCost);

            System.out.println("CSV file generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Matrix importMatrix(String csvFilePath) throws FileNotFoundException {
        Matrix matrix = new Matrix();
        File file = new File(csvFilePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            line = line.replaceAll("^[^0-9]+", "");

            String[] values = line.split(";");
            List<Integer> row = new ArrayList<>();
            for (String value : values) {
                try {
                    row.add(Integer.parseInt(value.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number: " + value);
                }
            }
            matrix.add(row);
        }

        scanner.close();
        return matrix;
    }



    public static void writePathsCSV(List<List<Vertice>> paths, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {

            for (List<Vertice> path : paths) {
                for (int i = 0; i < path.size(); i++) {
                    writer.append(path.get(i).getVertice());
                    if (i < path.size() - 1) {
                        writer.append(";");
                    }
                }
                writer.append("\n");
            }

            System.out.println("CSV file generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



