package org.algorithms.Graphs;

import java.util.*;

public class Graph {
    Hashtable<String, Hashtable<String, Integer>> graphTable;
    Hashtable<String, Double> costs;
    Hashtable<String, String> parents;
    int[][] matrix;
    Hashtable<String, String[]> graph;
    ArrayDeque<String> searchQueue = new ArrayDeque<>();
    boolean isSeller;
    int countway = 0;

    public Graph(Hashtable<String, Hashtable<String, Integer>> graphTable,
                 Hashtable<String, Double> costs,
                 Hashtable<String, String> parents) {

        this.graphTable = graphTable;
        this.costs = costs;
        this.parents = parents;

    }

    public Graph(int[][] matrix) {
        this.matrix = matrix;
    }
    public Graph(Hashtable<String, String[]> graph) {
        this.graph = graph;
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
        } else if (graph !=null) {
            return graph.toString();
        } else {
            return String.format("""
                            Вершины: %n%s
                            """,
                    graphTable);
        }

    }
    public ArrayDeque<String> toDecque() {
        searchQueue.clear();
        for (Map.Entry<String, String[]> entry : graph.entrySet()) {
            String[] string = entry.getValue();
            for (int i = 0; i < string.length; i++) {

                searchQueue.add(string[i]);
            }
        }
        return searchQueue;
    }
    public ArrayDeque<String> toDecqueByPoint(String person) {

        for (Map.Entry<String, String[]> entry : graph.entrySet()) {
            if (entry.getKey() == person) {
                String[] string = entry.getValue();

                for (int i = 0; i < string.length; i++) {
                    searchQueue.add(string[i]);
                }
            }
        }
        return searchQueue;
    }
    public boolean searchSeller() {
        ArrayList<String> searched = new ArrayList<>();
        while (!searchQueue.isEmpty()) {
            String person = searchQueue.pollFirst();
            if (!searched.contains(person)) {

                if (personIsSeller(person)) {
                    System.out.printf("""
                                    Продавец манго: %s%n""",
                            person);

                    return true;
                } else {
                    searchQueue = toDecqueByPoint(person);
                    searched.add(person);
                }
            }
        }

        return false;
    }
    public boolean searchTarget() {
        ArrayList<String> searched = new ArrayList<>();
        while (!searchQueue.isEmpty()) {
            String person = searchQueue.pollFirst();
            if (!searched.contains(person)) {
                if (isTarget(person)) {
                    System.out.printf("""
                                    Цель достигнута!: %s%n""",
                            person);

                    return true;
                } else {
                    searchQueue = toDecqueByPoint(person);
                    searched.add(person);
                }
            }
        }

        return false;
    }

    public boolean searchExit() {
        ArrayList<String> searched = new ArrayList<>();
        while (!searchQueue.isEmpty()) {
            String person = searchQueue.pollFirst();
            if (!searched.contains(person)) {
                if (!isBlocked(person)) {
                    System.out.println(person);
                    if (isExit(person)) {
                        System.out.printf("""
                                        Цель достигнута!: %s%n""",
                                person);

                        return true;
                    } else {
                        searchQueue = toDecqueByPoint(person);
                        searched.add(person);
                    }

                }

            }
        }
        return false;
    }

    private boolean personIsSeller(String person) {
        return Objects.equals(person, "jonny");
    }

    private boolean isTarget(String target) {
        return Objects.equals(target, "ДГТУ");
    }

    private boolean isExit(String target) {
        return target == "выход";
    }

    private boolean isBlocked(String point) {
        if (isExit(point)) {
            return false;
        }
        for (Map.Entry<String, String[]> entry : graph.entrySet()) {
            if (entry.getKey() == point) {
                String[] string = entry.getValue();
                if (string.length == 0) {
                    System.out.println("blocked - " + point);
                    return true;
                }
            }
        }
        return false;
    }
    //DijkstrasAlgorithm
    public void Dijkstras() {
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
                        parents.put(j + "", i + "");
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

    public void showMatrix() {
        if (matrix != null) {
            for (int i = 0; i <= matrix.length - 1; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j]);
                }
                System.out.println(" ");
            }
        } else {
            System.out.println("У вас нет матрицы");
        }
    }
}
