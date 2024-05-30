package org.algorithms.Greedy;

import java.time.temporal.ValueRange;
import java.util.*;

public class Greedy {
    HashSet<String> states_needed;
    HashSet<String> final_stations = new HashSet<>();
    Hashtable<String, HashSet<String>> stations;
    String bestStation;
    ValueRange mainRange;
    HashSet<ValueRange> ranges = new HashSet<>();
    HashSet<Integer> rangeSetMain = new HashSet<>();
    HashSet<String> finalRanges = new HashSet<>();
    String bestRange;
    Hashtable<String, HashSet<Integer>> rangesGiven = new Hashtable<>();

    boolean isInterval;
    Hashtable<String, Item> items;
    int capacity;



    public Greedy(ValueRange mainRange, HashSet<ValueRange> ranges, boolean isInterval) {
        this.mainRange = mainRange;
        this.ranges = ranges;
        this.isInterval = isInterval;
    }

    public Greedy(HashSet<String> states_needed, Hashtable<String, HashSet<String>> stations) {
        this.states_needed = states_needed;
        this.stations = stations;
    }
    public Greedy(int capacity, Hashtable<String, Item> items){
        this.capacity = capacity;
        this.items = items;
    }


    public String toString() {
        if (states_needed != null) {
            getBestSt();
            return String.format("""
                            Nтоговый набор станций: %s. 
                            """,
                    final_stations);
        } else {
            getBestRanges();
            return String.format("""
                            Nтоговый набор диапазонов: %s. 
                            """,
                    finalRanges);
        }

    }

    private void getBestRanges() {
        getSetFromRange();
        getHashTableFromVR();
        int i = 0;
        System.out.println(rangesGiven.size());
        while (!rangeSetMain.isEmpty()) {
            bestRange = null;
            HashSet<Integer> rangesCovered = new HashSet<>();
            if (i <= rangesGiven.size()) {
                for (Map.Entry<String, HashSet<Integer>> entry : rangesGiven.entrySet()) {
                    HashSet<Integer> copyOfNeeded = new HashSet<>(rangeSetMain);
                    copyOfNeeded.retainAll(entry.getValue());
                    if (copyOfNeeded.size() > rangesCovered.size()) {
                        bestRange = entry.getKey();
                        rangesCovered.addAll(copyOfNeeded);
                    }
                }
                i++;
                finalRanges.add(bestRange);
                rangeSetMain.removeAll(rangesCovered);
            }
            else {
                rangeSetMain.clear();
            }
        }
    }

    private void getSetFromRange() {
        for (int i = (int) mainRange.getMinimum(); mainRange.isValidIntValue(i); i++) {

            rangeSetMain.add(i);
        }
    }

    private int getMin(HashSet<Integer> copyOfNeeded) {
        int min = 100000;
        for (Integer inte : copyOfNeeded) {
            if (inte < min) {
                min = inte;
            }
        }
        return min;
    }

    private int getMax(HashSet<Integer> copyOfNeeded) {
        int max = -100000;
        for (Integer inte : copyOfNeeded) {
            if (inte > max) {
                max = inte;
            }
        }
        return max;
    }

    public HashSet<Integer> getRangeSet(ValueRange range) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = (int) range.getMinimum(); range.isValidIntValue(i); i++) {
            if (isInterval) {
                if (i == (int) range.getMinimum()) {
                    set.add(i + 1);
                }
            } else {
                set.add(i);
            }

        }
        return set;
    }

    private void getHashTableFromVR() {
        for (ValueRange entry : ranges) {
            HashSet<Integer> set = getRangeSet(entry);
            rangesGiven.put(entry.getMinimum() + " - " + entry.getMaximum(), set);
        }
    }

    private void getBestSt() {
        while (!states_needed.isEmpty()) {
            bestStation = null;
            HashSet<String> statesCovered = new HashSet<>();
            for (Map.Entry<String, HashSet<String>> entry : stations.entrySet()) {
                HashSet<String> copyOfNeeded = new HashSet<>(states_needed);
                copyOfNeeded.retainAll(entry.getValue());
                if (copyOfNeeded.size() > statesCovered.size()) {
                    bestStation = entry.getKey();
                    statesCovered.addAll(copyOfNeeded);
                }
                //System.out.println(copyOfNeeded + "" + entry.getValue() + " " + states_needed + " covered = " + statesCovered);
            }
            states_needed.removeAll(statesCovered);
            final_stations.add(bestStation);
        }
    }
    public void greedyBag() {
        // Создаем список элементов из Hashtable
        List<Item> itemList = new ArrayList<>(items.values());

        // Сортируем предметы по убыванию плотности стоимости
        Collections.sort(itemList, Comparator.comparingDouble(item -> -item.valueDensity));

        double totalValue = 0;
        int usedCapacity = 0;
        Hashtable<String, Item> selectedItems = new Hashtable<>();

        for (Item item : itemList) {
            if (usedCapacity + item.weight <= capacity) {
                usedCapacity += item.weight;
                totalValue += item.value;
                selectedItems.put(item.toString(), item);
            } else {
                double remaining = capacity - usedCapacity;
                totalValue += item.valueDensity * remaining;
                break;
            }
        }

        System.out.println("Максимальная стоимость: " + totalValue);
        System.out.println("Выбранные предметы:");
        for (Map.Entry<String, Item> entry : selectedItems.entrySet()) {
            System.out.println("Вес: " + entry.getValue().weight + ", Стоимость: " + entry.getValue().value);
        }
    }
}
