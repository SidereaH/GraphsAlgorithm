package org.example;

import java.util.Hashtable;

public class DijkstrasAlghoritmMain {
    public static void main(String[] args) {
        //Написать программу, находящую кратчайший путь от стартовой вершины до всех остальных вершин во взвешенном графе с положительными весами ребер.
        Hashtable<String, Hashtable<String, Integer>> graphTable = new Hashtable<>();

        Hashtable<String, Integer> startVal = new Hashtable<>();
        startVal.put("a", 6);
        startVal.put("b", 2);
        graphTable.put("start", startVal);

        Hashtable<String, Integer> aVal = new Hashtable<>();
        aVal.put("fin", 1);
        graphTable.put("a", aVal);

        Hashtable<String, Integer> bVal = new Hashtable<>();
        bVal.put("a", 3);
        bVal.put("fin", 5);
        graphTable.put("b", bVal);
        Hashtable<String, Integer> empty = new Hashtable<>();
        graphTable.put("fin", empty);

        double inf = Double.POSITIVE_INFINITY;
        Hashtable<String, Double> costs = new Hashtable<>();
        costs.put("a", 6d);
        costs.put("b", 2d);
        costs.put("fin", inf);

        Hashtable<String, String> parents = new Hashtable<>();
        parents.put("a", "start");
        parents.put("b", "start");
        parents.put("fin", "");

        GraphDijkstra dijcstrasgraph = new GraphDijkstra(graphTable, costs, parents);
        System.out.println("До работы алгоритма: \n" + dijcstrasgraph);
        dijcstrasgraph.Dijcstras();
        System.out.println("После работы алгоритма: \n" + dijcstrasgraph);
        //Создать функцию, которая определяет кратчайший путь от одной вершины до другой в графе, представленном в виде матрицы смежности.
        System.out.println("Преобразование матрицы");
        int[][] matrix = new int[][]{
                {0,6,2,0},
                {0,0,0,1},
                {0,3,0,5},
                {0,0,0,0}
        };

        GraphDijkstra graphFromMatrix = new GraphDijkstra(matrix);
        graphFromMatrix.showMatrix();
        graphFromMatrix.toGraphs();
        System.out.println("До работы алгоритма: \n" + graphFromMatrix);
        graphFromMatrix.Dijcstras();
        System.out.println("После работы алгоритма: \n" + graphFromMatrix);

    }
}
