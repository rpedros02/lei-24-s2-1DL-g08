package mdisc;
import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private List<List<Integer>> matrix;

    public Matrix(){
        matrix = new ArrayList<>();
    }

    public void add(List<Integer> row){
        matrix.add(row);
    }

    public List<List<Integer>> getMatrix() {
        return matrix;
    }

    public List<Integer> getRow(int index) {
        return matrix.get(index);
    }

    public void dijkstraAlgorithm(int startIndex, List<Integer> endIndex){
        List<DijkstraAlgorithm> dijkstraEntries = new ArrayList<>();
        List<Integer> asterisk = new ArrayList<>();

        //Simulation of the first line of the Dijkstra Algorithm solution

        int i = 0;
        for (List<Integer> pointCosts : matrix){
            if (i == startIndex) {
                dijkstraEntries.add(new DijkstraAlgorithm(0, startIndex));
            } else {
                dijkstraEntries.add(new DijkstraAlgorithm(Integer.MAX_VALUE));
            }
            i++;
        }

        // ---------------------------

        int size = i;
        int selectedPoint = startIndex;
        asterisk.add(selectedPoint);

        // Dijkstra's algorithm

        for (i = 0; i < size - 1; i++) {
            int minCost = Integer.MAX_VALUE;
            int minCostIndex = -1;


            for (int j = 0; j < size; j++) {
                if (!asterisk.contains(j) && matrix.get(selectedPoint).get(j) != 0) {
                    int newCost = matrix.get(selectedPoint).get(j) + dijkstraEntries.get(selectedPoint).getTotalCost();
                    if (newCost < dijkstraEntries.get(j).getTotalCost()) {
                        dijkstraEntries.get(j).setTotalCost(newCost);
                        dijkstraEntries.get(j).setVertices(new ArrayList<>(dijkstraEntries.get(selectedPoint).getVertices()));
                        dijkstraEntries.get(j).addVertex(j);
                    }

                    if (dijkstraEntries.get(j).getTotalCost() < minCost) {
                        minCost = dijkstraEntries.get(j).getTotalCost();
                        minCostIndex = j;
                    }
                }
            }

            // Check if minCostIndex is valid
            if (minCostIndex != -1) {
                asterisk.add(minCostIndex);
                selectedPoint = minCostIndex;

                if (endIndex.contains(selectedPoint)) {
                    i = size;
                }
            }
        }

        System.out.println(dijkstraEntries.get(selectedPoint).getVertices());
        System.out.println(dijkstraEntries.get(selectedPoint).getTotalCost());

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> row : matrix) {
            sb.append(row).append("\n");
        }
        return sb.toString();
    }
}
