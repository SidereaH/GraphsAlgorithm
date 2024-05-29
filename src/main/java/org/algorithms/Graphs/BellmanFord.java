package org.algorithms.Graphs;

import java.util.*;

public class BellmanFord {
    private static final int INF = Integer.MAX_VALUE;

    public static List<String> belmanFordShortest(Map<String, List<String>> graph, Map<String, Map<String, Integer>> weights, String start, String end) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        //расстояния
        for (String node : graph.keySet()) {
            dist.put(node, INF);
        }
        dist.put(start, 0);

        // Релаксация ребер (n-1) раз
        int n = graph.size();
        for (int i = 0; i < n - 1; i++) {
            for (String u : graph.keySet()) {
                for (String v : graph.get(u)) {
                    int weight = weights.get(u).get(v);
                    if (dist.get(u) != INF && dist.get(u) + weight < dist.get(v)) {
                        dist.put(v, dist.get(u) + weight);
                        parent.put(v, u);
                    }
                }
            }
        }

        // Проверка на отрицательные циклы
        for (String u : graph.keySet()) {
            for (String v : graph.get(u)) {
                int weight = weights.get(u).get(v);
                if (dist.get(u) != INF && dist.get(u) + weight < dist.get(v)) {
                    System.out.println("Граф содержит отрицательный цикл");
                    return new ArrayList<>();
                }
            }
        }

        // Восстановление кратчайшего пути
        LinkedList<String> path = new LinkedList<>();
        String current = end;
        while (current != null) {
            path.addFirst(current);
            current = parent.get(current);
        }

        return path;
    }
}
