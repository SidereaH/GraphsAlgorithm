package org.algorithms.Greedy;



import org.w3c.dom.ranges.Range;

import java.awt.*;
import java.awt.font.NumericShaper;
import java.time.temporal.ValueRange;
import java.util.HashSet;
import java.util.Hashtable;

public class GreedyMain {
    public static void main(String[] args) {
        HashSet<String> states_needed = new HashSet<>();
        states_needed.add("mt");
        states_needed.add("wa");
        states_needed.add("or");
        states_needed.add("id");
        states_needed.add("nv");
        states_needed.add("ut");
        states_needed.add("ca");
        states_needed.add("az");
        Hashtable<String, HashSet<String>> stations = new Hashtable<>();
        HashSet<String> vals = new HashSet<>();
        vals.add("id");
        vals.add("nv");
        vals.add("ut");
        stations.put("kone", vals);
        HashSet<String> vals2 = new HashSet<>();
        vals2.add("wa");
        vals2.add("id");
        vals2.add("mt");
        stations.put("ktwo", vals2);
        HashSet<String> vals3 = new HashSet<>();
        vals3.add("or");
        vals3.add("nv");
        vals3.add("ca");
        stations.put("kthree", vals3);
        HashSet<String> vals4 = new HashSet<>();
        vals4.add("nv");
        vals4.add("ca");
        stations.put("kfour", vals4);
        HashSet<String> vals5 = new HashSet<>();
        vals5.add("ca");
        vals5.add("az");
        stations.put("kfive", vals5);
        Greedy state = new Greedy(states_needed, stations);
        System.out.println(state);

        //Задача об интервалах: дано множество интервалов на числовой прямой.
        //Необходимо выбрать минимальное подмножество интервалов так, чтобы все точки на числовой прямой были покрыты хотя бы одним из выбранных интервалов.
        ValueRange mainRange = ValueRange.of(0,20);
        HashSet<ValueRange> ranges = new HashSet<>();
        ranges.add(ValueRange.of(0,3));
        ranges.add(ValueRange.of(4,9));
        ranges.add(ValueRange.of(4,7));
        ranges.add(ValueRange.of(10,20));
        ranges.add(ValueRange.of(1,2));
        ranges.add(ValueRange.of(20,100));
        Greedy range = new Greedy(mainRange, ranges, false);
        System.out.println(range);

        //
        ValueRange mainRangeOtrez = ValueRange.of(0,20);
        HashSet<ValueRange> rangesOtr = new HashSet<>();
        rangesOtr.add(ValueRange.of(0,3));
        rangesOtr.add(ValueRange.of(4,9));
        rangesOtr.add(ValueRange.of(4,7));
        rangesOtr.add(ValueRange.of(10,20));
        rangesOtr.add(ValueRange.of(1,2));
        rangesOtr.add(ValueRange.of(20,100));
        Greedy rangeOtr = new Greedy(mainRangeOtrez, rangesOtr, true);
        System.out.println(rangeOtr);
    }
}
