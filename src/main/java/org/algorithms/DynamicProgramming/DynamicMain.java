package org.algorithms.DynamicProgramming;

import java.util.Arrays;
import java.util.Hashtable;

public class DynamicMain {
    public static void main(String[] args) {
        //Написать программу, которая решает задачу о рюкзаке с использованием метода динамического программирования.
        System.out.println("Написать программу, которая решает задачу о рюкзаке с использованием метода динамического программирования.");
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
        System.out.println("Создать функцию, которая решает задачу о нахождении наибольшей суммы элементов в неупорядоченном массиве с использованием динамического программирования.");
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
        //- Реализовать алгоритм динамического программирования для нахождения наибольшей возрастающей подпоследовательности в массиве чисел.
        System.out.println("Реализовать алгоритм динамического программирования для нахождения наибольшей возрастающей подпоследовательности в массиве чисел.");
        Hashtable<String, int[]> arraysPosl = new Hashtable<>();
        int[] arrPos = new int[] {10,11,12,13,14};
        int[] arr2Pos = new int[] {1,5,7,8, 9, 120};
        int[] arr3Pos = new int[] {100,101,200};
        int[][] arrPosCosts = new int[][]{
                {getPosl(arrPos), arrPos.length},
                {getPosl(arr2Pos), arr2Pos.length},
                {getPosl(arr3Pos), arr3Pos.length}
        };
        for (int i=0; i<=arrCosts.length-1; i++){
            arraysPosl.put("Массив " + i, arrPosCosts[i]);
        }
        Dynamic arrsPos = new Dynamic(arraysPosl, 5);
        arrsPos.dynamicSolveBackPack();
    }
    public static int sumOfArr(int[] arr){
        int sum = 0;
        for(int i =0; i<= arr.length-1; i++){
            sum+=arr[i];
        }
        return sum;
    }
    public static  int getPosl(int[] arr){
        int posl = 0;
        int insval = 0;
        insval = getMin(arr);
        for (int i = 0; i< arr.length-1;i++){
            if (insval==arr[i] && insval+1 == arr[i+1]){
                insval = arr[i+1];
                posl++;
            }
            else{
                insval = arr[i+1];
            }
        }
        posl++;
        return posl;
    }

    public static int getMin(int[] arr){
        int min =10000;
        for (int i=0; i< arr.length; i++){
            if (arr[i]<min){
                min = arr[i];
            }
        }
        return min;
    }
    public static int getMax(int[] arr){
        int max =-10000;
        for (int i=0; i< arr.length; i++){
            if (arr[i]>max){
                max = arr[i];
            }
        }
        return max;
    }


}
