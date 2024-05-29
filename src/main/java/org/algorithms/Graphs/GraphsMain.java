package org.algorithms.Graphs;

import org.algorithms.Greedy.GreedyMain;

import java.util.*;

import static org.algorithms.Graphs.BellmanFord.belmanFordShortest;

public class GraphsMain {
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

        Graph dijcstrasgraph = new Graph(graphTable, costs, parents);
        System.out.println("До работы алгоритма: \n" + dijcstrasgraph);
        dijcstrasgraph.Dijkstras();
        System.out.println("После работы алгоритма: \n" + dijcstrasgraph);
        //Создать функцию, которая определяет кратчайший путь от одной вершины до другой в графе, представленном в виде матрицы смежности.
        System.out.println("Преобразование матрицы");
        int[][] matrix = new int[][]{
                {0,6,2,0},
                {0,0,0,1},
                {0,3,0,5},
                {0,0,0,0}
        };

        Graph graphFromMatrix = new Graph(matrix);
        graphFromMatrix.showMatrix();
        graphFromMatrix.toGraphs();
        System.out.println("До работы алгоритма: \n" + graphFromMatrix);
        graphFromMatrix.Dijkstras();
        System.out.println("После работы алгоритма: \n" + graphFromMatrix);
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("C", "D"));
        graph.put("C", Arrays.asList("D", "E"));
        graph.put("D", Arrays.asList("E"));
        graph.put("E", Collections.emptyList());

        Map<String, Map<String, Integer>> weights = new HashMap<>();
        weights.put("A", new HashMap<>());
        weights.get("A").put("B", 3);
        weights.get("A").put("C", 8);
        weights.put("B", new HashMap<>());
        weights.get("B").put("C", 1);
        weights.get("B").put("D", 7);
        weights.put("C", new HashMap<>());
        weights.get("C").put("D", 4);
        weights.get("C").put("E", -5);
        weights.put("D", new HashMap<>());
        weights.get("D").put("E", 6);
        weights.put("E", new HashMap<>());
        List<String> shortestPath = belmanFordShortest(graph, weights, "A", "E");
        System.out.println("Кратчайший путь: " + shortestPath);

        //в ширину
        //Написать программу, которая осуществляет поиск в ширину в графе и находит кратчайший путь от стартовой вершины до целевой вершины.
        Hashtable<String, String[]> breathOne = new Hashtable<>();
        String[] my = new String[] {"alice", "bob", "claire"};
        breathOne.put("you", my);
        String[] bob = new String[] {"anuj", "peggy"};
        breathOne.put("bob", bob);
        String[] alice = new String[] {"peggy"};
        breathOne.put("alice", alice);
        String[] anuj = new String[] {"thom","jonny"};
        breathOne.put("claire", anuj);
        String[] emptys = new String[] {};
        breathOne.put("anuj", emptys);
        breathOne.put("peggy", emptys);
        breathOne.put("thom", emptys);
        breathOne.put("jonny", emptys);
        Graph breath = new Graph(breathOne);
        breath.toDecque();
        breath.searchSeller();
        //- Реализовать алгоритм поиска в ширину для поиска кратчайшего пути в лабиринте, где некоторые клетки являются препятствиями.
        Hashtable<String, String[]> way = new Hashtable<>();
        String[] me = new String[] {"2", "3"};
        way.put("you", me);
        way.put("2", new String[]{"4","5"});
        way.put("3", new String[]{});
        way.put("4", new String[]{"6"});
        way.put("5", new String[]{"7"});
        way.put("6", new String[]{"выход"});
        way.put("7", new String[]{"8"});
        way.put("8", new String[]{"9"});
        Graph ways = new Graph(way);
        ways.toDecque();
        ways.searchExit();
        //Написать программу, которая осуществляет поиск в ширину в графе и находит кратчайший путь от стартовой вершины до целевой вершины.
        Hashtable<String, String[]> dstu = new Hashtable<>();

        dstu.put("you", new String[] {"82", "63"});
        dstu.put("82", new String[]{"93"});
        dstu.put("93", new String[]{"ДГТУ"});
        dstu.put("63", new String[]{"дгту"});
        Graph wayToDstu = new Graph(dstu);
        wayToDstu.toDecque();
        wayToDstu.searchTarget();
        //- Создать функцию, которая определяет количество компонент связности графа с помощью поиска в ширину.
        Hashtable<String, String[]> graphCounter = new Hashtable<>();
        graphCounter.put("you", new String[] {"2", "3"});
        graphCounter.put("2", new String[] {"you"});
        graphCounter.put("3", new String[] {"you"});
        graphCounter.put("4", new String[] {"5", "6"});
        graphCounter.put("5", new String[] {"4"});
        graphCounter.put("6", new String[] {"4"});
        graphCounter.put("10", new String[] {"89", "145"});
        graphCounter.put("89", new String[] {"10"});
        graphCounter.put("145", new String[] {"10"});
        Graph counter = new Graph(graphCounter);
        counter.toDecque();
        counter.countComp();

    }
}
