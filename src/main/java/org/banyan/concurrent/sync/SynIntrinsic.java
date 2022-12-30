package org.banyan.concurrent.sync;

import org.banyan.concurrent.util.SleepUtil;

/**
 * synchronized使用
 * 同步一个方法或一个代码快，同步锁作用对象，没有加同步的方法不会影响
 */
public class SynIntrinsic {

    private Object lock = new Object();

    /**
     * synchronized实例方法使用
     */
    public synchronized void synMethod() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":syn method:" + i);
        }
    }

    /**
     * synchronized静态方法使用
     */
    public static synchronized void synStatic() {
        System.out.println(Thread.currentThread().getName() + ":syn static");
        SleepUtil.second(3);
    }

    /**
     * synchronized代码块使用
     * 使用代码块加锁有哪几种方式，1.对变量加锁，2.this实例对象加锁，3.对象class对象加锁
     */
    public void synBlockInVar() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":syn  block:" + i);
            }
        }
    }

    public void synBlockInThis() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":syn  block this:" + i);
            }
        }
    }

    public void synBlockInClass() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":syn  block this:" + i);
            }
        }
    }


    /**
     * 未使用synchronized
     */
    public void normal() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":syn  not  :" + i);
        }
    }

}
