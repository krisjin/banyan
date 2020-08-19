package org.banyan.feature;

public class Norm {


    public static double lineNorm(double x, double max, double min) {
        double d = (x - min) / (max - min);
        return d;
    }


    public static void main(String[] args) {
        System.err.println(lineNorm(1000,1015,15));
        System.err.println(70/1000);
    }

}
