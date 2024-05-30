package org.algorithms.Greedy;

public class Item {
    int weight;
    int value;
    double valueDensity;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valueDensity = (double) value / weight;
    }
}

