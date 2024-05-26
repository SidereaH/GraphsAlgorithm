package org.example;

import java.util.*;

public class BFSMod {
    Hashtable<String, String[]> graph;
    ArrayDeque<String> searchQueue = new ArrayDeque<>();
    boolean isSeller;
    int countway = 0;
    public BFSMod(Hashtable<String, String[]> graph) {
        this.graph = graph;
    }

    @Override
    public String toString(){
        return graph.toString();
    }
    public ArrayDeque<String> toDecque(){
        searchQueue.clear();
        for(Map.Entry<String, String[]> entry : graph.entrySet()){
            String[] string = entry.getValue();
            for(int i =0; i<string.length; i++){

                searchQueue.add(string[i]);
            }
        }
        return searchQueue;
    }
    public ArrayDeque<String> toDecqueByPoint(String person){

        for(Map.Entry<String, String[]> entry : graph.entrySet()){
            if(entry.getKey() == person){
                String[] string = entry.getValue();

                for(int i =0; i<string.length; i++){
                    searchQueue.add(string[i]);
                }
            }
        }
        return searchQueue;
    }

    public boolean searchSeller(){
        ArrayList<String> searched  = new ArrayList<>();
        while (!searchQueue.isEmpty()){
            String person = searchQueue.pollFirst();
            if (!searched.contains(person)){

                if(personIsSeller(person)){
                    System.out.printf("""
                                Продавец манго: %s%n""",
                            person);

                    return true;
                }
                else{
                    searchQueue = toDecqueByPoint(person);
                    searched.add(person);
                }
            }
        }

        return false;
    }
    public boolean searchTarget(){
        ArrayList<String> searched  = new ArrayList<>();
        while (!searchQueue.isEmpty()){
            String person = searchQueue.pollFirst();
            if (!searched.contains(person)){
                if(isTarget(person)){
                    System.out.printf("""
                                Цель достигнута!: %s%n""",
                            person);

                    return true;
                }
                else{
                    searchQueue = toDecqueByPoint(person);
                    searched.add(person);
                }
            }
        }

        return false;
    }
    public boolean searchExit(){
        ArrayList<String> searched  = new ArrayList<>();
        while (!searchQueue.isEmpty()){
            String person = searchQueue.pollFirst();
            if (!searched.contains(person)){
                if(!isBlocked(person)){
                    System.out.println(person);
                    if (isExit(person)){
                        System.out.printf("""
                                Цель достигнута!: %s%n""",
                                person);

                        return true;
                    }
                    else{
                        searchQueue = toDecqueByPoint(person);
                        searched.add(person);
                    }

                }

            }
        }
        return false;
    }


    private boolean personIsSeller(String person){
        return Objects.equals(person, "jonny");
    }
    private boolean isTarget(String target){
        return Objects.equals(target, "ДГТУ");
    }
    private boolean isExit(String target){
        return target == "выход";
    }
    private boolean isBlocked(String point){
        if(isExit(point)){
            return false;
        }
        for(Map.Entry<String, String[]> entry : graph.entrySet()){
            if(entry.getKey() == point){
                String[] string = entry.getValue();
                if(string.length == 0){
                    System.out.println("blocked - " + point);
                    return true;
                }
            }
        }
        return false;
    }
}
