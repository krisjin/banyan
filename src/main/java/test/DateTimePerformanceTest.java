package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: shijingui
 * Date: 2016/10/21
 */
public class DateTimePerformanceTest {
    @Test
    public void test() {
        long time = System.nanoTime();
        long timeTest = System.currentTimeMillis();
        System.out.println(System.nanoTime() - time);

        time = System.nanoTime();
        Date date = new Date();
        System.out.println(System.nanoTime() - time);

        time = System.nanoTime();
        Object object = new Object();
        System.out.println(System.nanoTime() - time);

        time = System.nanoTime();
        List list = new ArrayList();
        System.out.println(System.nanoTime() - time);


    }
}
