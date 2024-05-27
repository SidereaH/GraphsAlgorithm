package org.example.KNearestNeighbour;

import java.util.Hashtable;
import java.util.Map;

public class KNearest {
    Hashtable<String, double[]> iris;
    Hashtable<String, double[]> kindsOfIris = new Hashtable<>();
    Hashtable<String, Double> lengths = new Hashtable<>();
    String nearest;

    public KNearest(Hashtable<String, double[]> iris) {
        double[] setosair = new double[]{5.1, 3.5, 1.4, 0.2};
        this.kindsOfIris.put("setosa", setosair);
        double[] versicolor = new double[]{7.0, 3.2, 4.7, 1.4};
        this.kindsOfIris.put("versicolor", versicolor);
        double[] virginica = new double[]{6.3, 3.3, 6.0, 2.5};
        this.kindsOfIris.put("virginica", virginica);
        this.iris = iris;
    }
    @Override
    public String toString(){
        return String.format("""   
                Больше всего похож на: %s. 
                """,
                nearest);
    }
    public void searchNearest() {
        double[] irval = iris.get("iris");

        for (Map.Entry<String, double[]> entry : kindsOfIris.entrySet()) {
            double length = getLength(irval, entry.getValue());
            lengths.put("iris - " + entry.getKey(), length);
        }
        double min = Double.POSITIVE_INFINITY;
        for (Map.Entry<String, Double> entry : lengths.entrySet()){
            if (min > entry.getValue()){
                min = entry.getValue();
            }
        }
        for (Map.Entry<String, Double> entry : lengths.entrySet()){
            if (min == entry.getValue()){
                nearest = entry.getKey();
            }
        }
    }
    private double getLength(double[] arr1, double[] arr2) {
        double length;
        double iter = 0;
        for (int i = 0; i < arr1.length; i++) {
            iter += Math.abs(arr1[i] - arr2[i]);
        }
        length = Math.sqrt(iter);
        return length;
    }
    private double[][] getArr(Hashtable<String, double[]> table) {
        double[][] arr = new double[table.size()][3];
        int j = 0;
        for (Map.Entry<String, double[]> entry : table.entrySet()) {
            double[] value = entry.getValue();
            for (int i = 0; i < value.length; i++) {
                arr[j][i] = value[i];
            }
            j++;
        }
        return arr;
    }
}
