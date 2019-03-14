package org.banyan.optimization;

/**
 * @author krisjin on 2018/1/9
 */
public class ArrayTest {


    public static void main(String[] args) {
        int size = 100000;
        int[] array = new int[size];
        int[] arrayDest = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        long st = System.currentTimeMillis();
        for (int k = 0; k < 1000; k++) {
            System.arraycopy(array, 0, arrayDest, 0, size);//copy
        }
        long cost = System.currentTimeMillis() - st;
        System.out.println(cost);
    }

}
