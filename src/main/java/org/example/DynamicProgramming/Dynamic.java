package org.example.DynamicProgramming;

import java.util.Hashtable;
import java.util.Map;

public class Dynamic {
    Hashtable<String, int[]> table;
    int[][] matrix;
    int sizeOfBackPack;
    int[] weight;
    int[] value;
    int bestPrice = 0;
    String finalWay;



    public Dynamic(Hashtable<String, int[]> table, int sizeOfBackPack) {
        this.table = table;
        this.sizeOfBackPack = sizeOfBackPack;
    }



    @Override
    public String toString() {
//        if (matrix != null) {
//            matrixToString();
//            return String.format("""
//                            Предметы:
//                            Размер рюкзака: %s,
//                            Матрица: %s
//                            """,
//                    sizeOfBackPack);
//        } else return null;
        return String.valueOf(bestPrice) +" "+ finalWay;

    }

    public void dynamicSolveBackPack() {
        getWeightsValues();
        backpackDP();
        matrixToString();


//        matrix = new double[table.size()][sizeOfBackPack];
//        for (Map.Entry<String, int[]> entry : table.entrySet()) {
//            int[] value = entry.getValue();
//            for (int i = 0; i <= matrix.length - 1; i++) {
//                for (int j = 0; j <= matrix[i].length - 1; j++) {
//                    //System.out.println(Arrays.toString(value) + " matrix[i][j] < value[0]" + (matrix[i][j] < value[0]) + " " + j + " " + "(j+1) >=value[1]" + ((j + 1) >= value[1]));
////                    if (matrix[i][j] < value[0] && (j+1) >=value[1]) {
////                        matrix[i][j] = value[0];
////                    }
//                    if (i == 0) {
//                        if (matrix[i][j] < (value[0]) && (j + 1) >= value[1]) {
//                            matrix[i][j] = value[0];
//                        }
//                    } else {
//                        if (j!=0){
//                            if (matrix[i - 1][j] > (value[0] + matrix[i - 1][(int) (j - value[1])])) {
//                                matrix[i][j] = matrix[i - 1][j];
//                            } else {
//
//                                matrix[i][j] = matrix[i - 1][(int) (j - value[1])];
//                            }
//                        }
//                        else {
//                            matrix[i][j] = matrix[i - 1][j];
//                        }
//                    }
//                }
//            }
//        }
   }

    private void matrixToString() {
        for (int i = 0; i <= matrix.length - 1; i++) {
            for (int j = 0; j <= matrix[i].length - 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println(finalWay);
    }

    int[][] backpackDP() {
        if (table.size() <= 0 || sizeOfBackPack <= 0) {
            return null;
        }

        matrix = new int[table.size() + 1][sizeOfBackPack + 1];
        for (int j = 0; j <= sizeOfBackPack; j++) {
            matrix[0][j] = 0;
        }

        for (int i = 1; i <= table.size(); i++) {
            for (int j = 1; j <= sizeOfBackPack; j++) {
                if (weight[i - 1] > j) {
                    matrix[i][j] = matrix[i - 1][j];

                } else {
                    matrix[i][j] = Math.max(
                            matrix[i - 1][j],
                            matrix[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        return  matrix;
    }

    private void getWeightsValues() {
        weight = new int[table.size()];
        value = new int[table.size()];
        int[][] wv = new int[table.size()][2];
        int i = 0;
        for (Map.Entry<String, int[]> entry : table.entrySet()) {
            int[] vals = entry.getValue();
            int j = 0;
            wv[i][j] = vals[1];
            j++;
            wv[i][j] = vals[0];
            i++;
        }
        arrToString(wv);
        for (int k = 0; k <= wv.length - 1; k++) {
            weight[k] = wv[k][0];
            value[k] = wv[k][1];
        }
        //System.out.println(Arrays.toString(weight));
        //System.out.println(Arrays.toString(value));
    }
    private void getKeyByWV(int w, int v){
        for(Map.Entry<String, int[]> entry : table.entrySet()) {
            int[] value = entry.getValue();
            if (value[0] == v && value[1] == w) {
                finalWay = entry.getKey();
            }
        }
    }
    private String arrToString(int[][] arr) {
        for (int i = 0; i <= arr.length - 1; i++) {
            for (int j = 0; j <= arr[i].length - 1; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(" ");
        }
        return arr.toString();
    }


}
