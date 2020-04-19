package org.banyan.bytecode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User:shijingui
 * Date:2019/4/3
 *  
 */
public class Syntest {


    public static synchronized void synTest() {
    }

    public void synTest2() {

        synchronized (this) {
            System.err.println(11);
            aa();
        }

    }


    public static void main(String[] args) {
        aa();
    }

    public static void aa() {
        AtomicInteger num = new AtomicInteger(1);
        num.compareAndSet(num.get(), 2);
        num.incrementAndGet();
        System.out.println();
    }
}
