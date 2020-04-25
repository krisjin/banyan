package org.banyan.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Cache<String, double[][]> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();

        String key = "name";

        double[][] v = cache.getIfPresent(key);
        double[][] v1 = new double[0][0];

        if (null == v) {
            cache.put(key, v1);
        }

        while (true) {

            try {
                Thread.sleep(1000);

                System.err.println(cache.getIfPresent(key));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
//
//        double[][] d1 = {{1, 2}, {3, 4}};
//        double[][] d2 = new double[d1.length][d1[0].length];
//        copy(d1, d2);
//
//        System.out.println(d2[1][0]);

    }


    public static void copy(double[][] d1, double[][] d2) {
        for (int i = 0; i < d1.length; i++) {
            System.arraycopy(d1[i], 0, d2[i], 0, d1[i].length);
        }

    }

}
