package mdisc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class GNUPlothGraph {

    public static void generateGNUPlotGraph(String dataFilePath, String outputFilePath) {
        try {
            // Create GNUPplot file
            File scriptFile = new File("plot_script.gnu");
            FileWriter scriptWriter = new FileWriter(scriptFile);
            scriptWriter.write("set terminal png size 800,600\n");
            scriptWriter.write("set output '" + outputFilePath + "'\n");
            scriptWriter.write("set datafile separator ';'\n");
            scriptWriter.write("set xlabel 'Input size'\n");
            scriptWriter.write("set ylabel 'Execution Time (ns)'\n");
            scriptWriter.write("set title 'Execution Time vs Input Size'\n");
            scriptWriter.write("plot '" + dataFilePath + "' using 1:2 with points\n");
            scriptWriter.write("set xrange [0:*]\n");
            scriptWriter.write("set yrange [0:*]\n");
            scriptWriter.close();

            // Execution of the GNUPplot script
            ProcessBuilder pb = new ProcessBuilder("gnuplot", "plot_script.gnu");
            pb.directory(new File(System.getProperty("user.dir"))); // Define the working directory
            Process process = pb.start();
            process.waitFor();

            // Check if the execution was successful
            if (process.exitValue() == 0) {
                System.out.println("Graph generated successfully!");
            } else {
                System.err.println("Error generating the graph.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


