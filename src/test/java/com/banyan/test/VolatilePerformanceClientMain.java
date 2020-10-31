package com.banyan.test;

import org.junit.Test;

/**
 * User: krisjin
 * Date: 2016/10/21
 */
public class VolatilePerformanceClientMain {
    private long num;
    private volatile long volatileNum;
    private int size = 10000000;

    @Test
    public void test() {
        long time = System.nanoTime();

        for (int i = 0; i < size; i++)
            volatileNum = System.currentTimeMillis();
        System.out.println((-time + (time = System.nanoTime())) + "    volatile写+取系统时间");

        for (int i = 0; i < size; i++)
            num = System.currentTimeMillis();
        System.out.println((-time + (time = System.nanoTime())) + "    普通写+取系统时间");

        for (int i = 0; i < size; i++) {
            synchronized (VolatilePerformanceClientMain.class) {
            }
        }
        System.out.println((-time + (time = System.nanoTime())) + "    空的同步块(synchronized)");

        for (int i = 0; i < size; i++)
            volatileNum++;
        System.out.println((-time + (time = System.nanoTime())) + "     volatile变量自增");

        for (int i = 0; i < size; i++)
            volatileNum = i;
        System.out.println((-time + (time = System.nanoTime())) + "     volatile写");

        for (long i = 0; i < size; i++)
            i = volatileNum;
        System.out.println((-time + (time = System.nanoTime())) + "     volatile读");

        for (long i = 0; i < size; i++)
            num++;
        System.out.println((-time + (time = System.nanoTime())) + "     普通变量自增");

        for (long i = 0; i < size; i++)
            num = i;
        System.out.println((-time + (time = System.nanoTime())) + "     普通变量写");


        for (long i = 0; i < size; i++)
            i = num;
        System.out.println((-time + (time = System.nanoTime())) + "     普通变量读");
    }

}
