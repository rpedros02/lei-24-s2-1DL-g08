package mdisc.sprintc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class GraphGenerator {

    public void generateGraph(String csvFile, String graphTitle) throws IOException, InterruptedException {
        String dotFile = "src/main/java/mdisc/sprintc/graphsAndPng/" + graphTitle + ".dot";
        String pngFile = "src/main/java/mdisc/sprintc/graphsAndPng/" + graphTitle + ".png";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(dotFile))) {

            writer.write("digraph " + graphTitle + " {\n");
            writer.write("rankdir=LR;\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("; Cost: ");
                String path = parts[0].substring(1, parts[0].length() - 1);
                int cost = Integer.parseInt(parts[1]);

                String[] vertices = path.split(", ");
                for (int i = 0; i < vertices.length - 1; i++) {
                    writer.write("\"" + vertices[i] + "\" -> \"" + vertices[i + 1] + "\" [label=\"" + cost + "\"];\n");
                }
            }

            writer.write("}\n");
        }

        Process process = new ProcessBuilder("dot", "-Tpng", dotFile, "-o", pngFile).start();
        process.waitFor();
    }
}

