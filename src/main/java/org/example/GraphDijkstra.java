package org.example;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class GraphDijkstra {
    Hashtable<String, Hashtable<String, Integer>> graphTable;
    Hashtable<String, Double> costs;
    Hashtable<String, String> parents;
    int[][] matrix;

    public GraphDijkstra(Hashtable<String, Hashtable<String, Integer>> graphTable,
                         Hashtable<String, Double> costs,
                         Hashtable<String, String> parents) {

        this.graphTable = graphTable;
        this.costs = costs;
        this.parents = parents;

    }

    public GraphDijkstra(int[][] matrix) {
        this.matrix = matrix;
    }


    @Override
    public String toString() {
//        for(Map.Entry<String, Hashtable<String, Integer>> entry: graphTable.entrySet()){
//            System.out.println("Ключ:" + entry.getKey());
//            for (Map.Entry<String, Integer> entryV : entry.getValue().entrySet()){
//                System.out.println(entryV.getKey() + " " + entryV.getValue());
//            }
//        }
        if (costs != null) {
            return String.format("""
                            Стоимости посещения вершин: %s,
                            Родители вершин: %s.
                            """,
                    costs,
                    parents);
        } else {
            return String.format("""
                            Вершины: %n%s
                            """,
                    graphTable);
        }

    }

    public void Dijcstras() {
        ArrayList<String> processed = new ArrayList<>();
        String node = findLowestCostNode(costs, processed);
        while (node != null) {
            double cost = costs.get(node);
            Hashtable<String, Integer> neighbors = graphTable.get(node);
            for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
                double new_cost = cost + entry.getValue();
                if (costs.get(entry.getKey()) > new_cost) {
                    costs.put(entry.getKey(), new_cost);
                    parents.put(entry.getKey(), node);
                }
            }
            processed.addLast(node);

            node = findLowestCostNode(costs, processed);
        }
    }

    private String findLowestCostNode(Hashtable<String, Double> costs, ArrayList<String> processed) {
        double min = Double.POSITIVE_INFINITY;
        String key_lowest = null;
        for (Map.Entry<String, Double> entry : costs.entrySet()) {
            if (min > entry.getValue() && !processed.contains(entry.getKey())) {
                min = entry.getValue();
                key_lowest = entry.getKey();
            }
        }
        return key_lowest;
    }

    public void toGraphs() {
        double inf = Double.POSITIVE_INFINITY;
        graphTable = new Hashtable<>();
        parents = new Hashtable<>();
        costs = new Hashtable<>();
        for (int i = 0; i <= matrix.length - 1; i++) {
            Hashtable<String, Integer> values = new Hashtable<>();
            for (int j = 0; j <= matrix[i].length - 1; j++) {

                if (matrix[i][j] != 0) {
                    if (i == 0) {
                        parents.put(j+"", i+"");
                        parents.put("fin", "");
                        costs.put(j + "", (double) matrix[i][j]);
                        costs.put("fin", inf);

                    } else if (j == matrix.length - 1) {
                        costs.put("fin", inf);
                    }
                    if (matrix[i].length - 1 == j) {
                        values.put("fin", matrix[i][j]);

                    } else {
                        values.put(j + "", matrix[i][j]);

                    }
                }
            }
            if (i == 0) {
                graphTable.put("start", values);
            } else if (i == matrix.length - 1) {
                graphTable.put("fin", values);
            } else {
                graphTable.put(i + "", values);
            }
        }

    }
    public void showMatrix(){
        if (matrix !=null){
            for (int i =0; i<= matrix.length-1;i++){
                for (int j =0; j<matrix[i].length; j++){
                    System.out.print(matrix[i][j]);
                }
                System.out.println(" ");
            }
        }
        else {
            System.out.println("У вас нет матрицы");
        }

    }


}
