package org.algorithms.KNearestNeighbour;

import java.awt.image.AreaAveragingScaleFilter;
import java.rmi.MarshalledObject;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class KNearest {
    Hashtable<String, double[]> object;
    Hashtable<String, double[]> libObj = new Hashtable<>();
    Hashtable<String, Double> lengths = new Hashtable<>();
    String nearest;
    String typeOfSearch;

    public KNearest(Hashtable<String, double[]> iris, String typeflowerOrfilm) {
        if (typeflowerOrfilm.toLowerCase() == "flower"){
            this.typeOfSearch = typeflowerOrfilm;
            System.out.println(typeOfSearch);
            double[] setosair = new double[]{5.1, 3.5, 1.4, 0.2};
            this.libObj.put("setosa", setosair);
            double[] versicolor = new double[]{7.0, 3.2, 4.7, 1.4};
            this.libObj.put("versicolor", versicolor);
            double[] virginica = new double[]{6.3, 3.3, 6.0, 2.5};
            this.libObj.put("virginica", virginica);
            this.object = iris;
        }
        else if (typeflowerOrfilm.toLowerCase() == "film"){
            //комедия/боевик/драма/ужасы/мелодрама
            this.typeOfSearch = typeflowerOrfilm;
            System.out.println(typeOfSearch);
            double[] filmPriyanka = new double[]{3, 4, 4, 1, 4};
            this.libObj.put("priyanka", filmPriyanka);
            double[] djastin = new double[]{4,3,5,1,5};
            this.libObj.put("djastin", djastin);
            double[] morpheus = new double[]{2,5,1,3,1};
            this.libObj.put("morpheus", morpheus);
            this.object = iris;
        }

    }
    @Override
    public String toString(){
        double[] prdskazocenka = new double[0];
        for (Map.Entry<String, double[]> entry:libObj.entrySet() ){
            if (Objects.equals(nearest.toLowerCase(), entry.getKey().toLowerCase())){
                prdskazocenka = entry.getValue();
            }

        }
        return String.format("""   
                Больше всего похож на: %s.
                будет иметь параметры: %s. 
                """,
                nearest,
                Arrays.toString(prdskazocenka)
                );
    }
    public void searchNearest() {

        double[] irval = object.get(typeOfSearch);

        for (Map.Entry<String, double[]> entry : libObj.entrySet()) {
            double length = getLength(irval, entry.getValue());
            lengths.put(entry.getKey(), length);
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
