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
    public void countComp (){
        System.out.println(countConnectedComponents(graph));

////        int[] arr = toIntArrFromQueue();
////        quickSort(arr, 0, arr.length-1);
////        System.out.println(Arrays.toString(arr));
//          int count = 0;
//          HashSet<String> keys = new HashSet<>();
//        for (Map.Entry<String, String[]> entry : graph.entrySet()) {
//            int innercount = 0;
//            for (int i = 0; i< entry.getValue().length; i++){
//                if (graph.get(entry.getValue()[i]).length == 0){
//                    innercount++;
//
//                }
//
//            }
//            System.out.println(innercount);
//            if (innercount == entry.getValue().length){
//                keys.add(entry.getKey());
//            }
//
//
//        }
////        for (int i=getMin(arr); i< arr.length; i++){
////            if (arr[i] == i && arr[i+1] != i+1){
////                count++;
////            } else if (arr[i] != i) {
////                count++;
////            }
////            else if (arr[i+1] != i+1){
////                count++;
////            }
////        }
//          System.out.println(count + " " + keys);
//          return count;
    }
    public static int countConnectedComponents(Hashtable<String, String[]> graph) {
        HashSet<String> visited = new HashSet<>();
        int count = 0;
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                bfsForCount(graph, node, visited);
                count++;
            }
        }
        return count;
    }

    private static void bfsForCount(Hashtable<String, String[]> graph, String start, HashSet<String> visited) {
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            String[] neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
    private int[] toIntArrFromQueue(){
        int[] arr = new int[searchQueue.size()];
        int i =0;
        for (String entry : searchQueue){
            arr[i] = Integer.valueOf(entry);
            i++;
        }
        return arr;
    }
    private int[] toIntArrFromStr(String[] arrS){
        int[] arr = new int[arrS.length];
        for (int i =0; i< arr.length;i++){
            arr[i] = Integer.valueOf(arrS[i]);
        }
        return arr;
    }
    public static int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = pivot(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
        return arr;
    }
    private static int pivot(int[] arr, int less, int greater) {
        int middle = less + (greater - less) / 2;
        int pivot = arr[middle];

        // Обмен опорного элемента с последним
        int temp = arr[middle];
        arr[middle] = arr[greater];
        arr[greater] = temp;
        int i = (less - 1);
        for (int j = less; j < greater; j++) {
            if (arr[j] < pivot) {
                i++;

                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i + 1];
        arr[i + 1] = arr[greater];
        arr[greater] = temp;
        return i + 1;
    }
    private int getMin(int[] arr){
        int min = 100000;
        for (int i=0; i<arr.length;i++){
            if (arr[i]<min ) {
                min =arr[i];
            }
        }
        System.out.println(min);
        return min;
    }
    public String[] quickSortString(String[] arr, int low, int high){
        if (low < high) {
            int pi = pivotString(arr, low, high);

            quickSortString(arr, low, pi - 1);
            quickSortString(arr, pi + 1, high);
        }
        return arr;
    }
    private int pivotString(String[] arr, int less, int greater){
        int middle = less + (greater - less) / 2;
        String pivot = arr[middle];

        // Обмен опорного элемента с последним, чтобы использовать существующую логику
        String temp = arr[middle];
        arr[middle] = arr[greater];
        arr[greater] = temp;
        int i = (less - 1);
        for (int j = less; j < greater; j++) {
            if (arr[j].compareToIgnoreCase(pivot) < 0) {
                i++;

                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i + 1];
        arr[i + 1] = arr[greater];
        arr[greater] = temp;
        return i + 1;
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
    public void DijkstrasMinus() {
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
