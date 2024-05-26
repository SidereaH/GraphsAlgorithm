package org.example;

import java.util.Hashtable;

public class BreadthFirstSearchMain {
    public static void main(String[] args) {
        //Реализация алгоитма из книги "Грокаем алгоритмы"
        Hashtable<String, String[]> table = new Hashtable<>();
        String[] yourFrineds = new String[] {"alice", "bob", "claire"};
        table.put("you", yourFrineds);
        String[] bobFriends = new String[] {"anuj","peggy"};
        table.put("bob", bobFriends);
        String[] aliceFrinds = new String[] {"peggy"};
        table.put("alice", aliceFrinds);
        String[] claireFriends = new String[] {"thom", "jonny"};
        table.put("claire", claireFriends);
        String[] empty = new String[0];
        table.put("anuj", empty);
        table.put("peggy", empty);
        table.put("thom", empty);
        table.put("jonny", empty);
        BFSMod graph = new BFSMod(table);
        String startFromPeople = "you";
        graph.toDecqueByPoint(startFromPeople);
        System.out.println(graph.searchSeller());
        //Написать программу, которая осуществляет поиск в ширину в графе и находит кратчайший путь от стартовой вершины до целевой вершины.
        Hashtable<String, String[]> tableWay = new Hashtable<>();
        String[] ourWay = new String[]{"автобус 82", "автобус 63"};
        tableWay.put("you", ourWay);
        String[] from82 =new String[]{"автобус 93"};
        tableWay.put("автобус 82", from82);
        String[] from93 = new String[]{"ДГТУ"};
        tableWay.put("автобус 93", from93);
        String[] from63 = new String[]{"ДГТУ"};
        tableWay.put("автобус 63", from63);
        tableWay.put("ДГТУ", empty);

        BFSMod wayToDSTU = new BFSMod(tableWay);
        String startFromPoint = "you";
        wayToDSTU.toDecqueByPoint(startFromPoint);
        wayToDSTU.searchTarget();
        //Реализовать алгоритм поиска в ширину для поиска кратчайшего пути в лабиринте, где некоторые клетки являются препятствиями.

        Hashtable<String, String[]> tableLab = new Hashtable<>();
        String[] ourWayLab = new String[]{"2", "3"};
        tableLab.put("you", ourWayLab);
        String[] from2 = new String[]{"4", "5"};
        tableLab.put("2", from2);
        tableLab.put("3", empty);
        String[] from4 = new String[]{"6"};
        tableLab.put("4", from4);
        String[] from5 = new String[]{"7"};
        tableLab.put("5", from5);
        String[] from6 = new String[]{"выход"};
        tableLab.put("6", from6);
        String[] from7 = new String[]{"8"};
        tableLab.put("7", from7);
        String[] from8 = new String[]{"выход"};
        tableLab.put("8", from8);

        BFSMod labGraph = new BFSMod(tableLab);
        labGraph.toDecqueByPoint("you");
        labGraph.searchExit();

    }
}