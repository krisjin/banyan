package com.concurrent.task;

import java.util.concurrent.Callable;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/22
 * Time: 11:03
 */
public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);

        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}