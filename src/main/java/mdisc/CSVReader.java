package mdisc;

import java.io.*;
import java.util.ArrayList;
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

    public static void writeCSV(ArrayList<Aresta> tree, String fileName, double minimumCost) {
        try (FileWriter writer = new FileWriter(fileName)) {

            // Write each edge of the tree in CSV format
            for (Aresta aresta : tree) {
                writer.append(aresta.getStart() + ";" + aresta.getEnd() + ";" + aresta.getWeight() + "\n");
            }

            // Write a line with the minimum cost of the minimum spanning tree
            writer.write("\nCost of a minimum spanning tree = " + minimumCost);

            System.out.println("CSV file generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
