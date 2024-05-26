package org.example;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class DAMod {
    Hashtable<String, Hashtable<String, Integer>> graphTable;
    Hashtable<String, Double> costs;
    Hashtable<String ,String> parents;

    public DAMod(Hashtable<String, Hashtable<String, Integer>> graphTable,
                 Hashtable<String, Double> costs,
                 Hashtable<String, String> parents) {

        this.graphTable = graphTable;
        this.costs = costs;
        this.parents = parents;

    }
    @Override
    public String toString(){
        return String.format("""
                Стоимости посещения вершин: %s.
                """,
                costs);
    }
    public void Dijcstras(){
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
    private String findLowestCostNode(Hashtable<String, Double> costs, ArrayList<String> processed){
        double min = Double.POSITIVE_INFINITY;
        String key_lowest = null;
        for (Map.Entry<String,Double> entry : costs.entrySet()){
            if(min > entry.getValue() && !processed.contains(entry.getKey()) ){
                min = entry.getValue();
                key_lowest = entry.getKey();
            }
        }
        return key_lowest;
    }

}
