package mdisc.sprintb;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GraphPrinter {
    private StringBuilder graphBuilder = new StringBuilder();
    private String filePrefix;
    public GraphPrinter() {
    }
    /**
     * @param filePrefix Only put filename prefix E.g. File1, File2, MyNewFile, myfile etc. It will use the filePrefix to create output dot file and png file with same name.
     */
    public GraphPrinter(String filePrefix) {
        this.filePrefix = filePrefix;
    }
    /**
     * @param line line contains a valid dot file graphviz text
     */
    public void addln(String line) {
        graphBuilder.append(line).append("\n");
    }
    public void add(String text) {
        graphBuilder.append(text);
    }

    /**
     * Creates an output dot file and uses that to create graphviz png output using following command
     * dot -Tpng <filePrefix>.dot -o <filePrefix>.png
     * If you want to change the certain format, change below.
     */
    public void print() {
        try {
            if (filePrefix == null || filePrefix.isEmpty()) {
                filePrefix = "output";
            }
            graphBuilder.insert(0, "graph G {");
            graphBuilder.append("\n}").append("\n");
            writeTextToFile(filePrefix + ".dot", graphBuilder.toString());
            String dotFilePath = filePrefix + ".dot";
            String pngFilePath = filePrefix + ".png";
            StringBuilder command = new StringBuilder();
            command.append("dot -Tpng ")   // output type
                    .append(dotFilePath).append(" ")   // input dot file
                    .append("-o ").append(pngFilePath);  // output image
            executeCommand(command.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void executeCommand(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        if (exitCode == 0) {
        } else {
            throw new Exception("Error executing Graphviz command. Exit code: " + exitCode);
        }
    }
    private void writeTextToFile(String fileName, String text) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(text.getBytes());
        outputStream.close();
    }
}
