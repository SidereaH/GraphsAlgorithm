package org.algorithms.DynamicProgramming;

import java.util.Hashtable;

public class DynamicMain {
    public static void main(String[] args) {
        //Написать программу, которая решает задачу о рюкзаке с использованием метода динамического программирования.
        Hashtable<String, int[]> items = new Hashtable<>();
        String[] itemNames = new String[]{"Гитара", "Магнитофон", "Ноутбук"};

        int[][] itemCosts = new int[][]{
                {1500, 1},
                {2000, 3},
                {3000, 4}
        };
        for (int i=0; i<=itemNames.length-1; i++){
            items.put(itemNames[i], itemCosts[i]);
        }
        Dynamic backPack = new Dynamic(items, 4);

        backPack.dynamicSolveBackPack();
        //Создать функцию, которая решает задачу о нахождении наибольшей суммы элементов в неупорядоченном массиве с использованием динамического программирования.
        Hashtable<String, int[]> arrays = new Hashtable<>();
        int[] arr = new int[] {10,12,3213,4, -100};
        int[] arr2 = new int[] {7,-1000,212,78, -1500, 120};
        int[] arr3 = new int[] {-100,-100,200};

        int[][] arrCosts = new int[][]{
                {sumOfArr(arr), arr.length},
                {sumOfArr(arr2), arr2.length},
                {sumOfArr(arr3), arr3.length}
        };
        for (int i=0; i<=arrCosts.length-1; i++){
            arrays.put("Массив " + i, arrCosts[i]);
        }

        Dynamic arrs = new Dynamic(arrays, 5);
        arrs.dynamicSolveBackPack();

    }
    public static int sumOfArr(int[] arr){
        int sum = 0;
        for(int i =0; i<= arr.length-1; i++){
            sum+=arr[i];
        }
        return sum;
    }


}
