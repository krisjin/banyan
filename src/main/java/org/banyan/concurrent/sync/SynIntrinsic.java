package org.banyan.concurrent.sync;

/**
 * 同步一个方法或一个代码快，同步锁作用对象，没有加同步的方法不会影响
 * User:krisjin
 * Date:2019/3/12
 */
public class SynIntrinsic {

    public synchronized void synMethod() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":syn method:" + i);
        }
    }


    public void synBlock() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":syn  block:" + i);
            }
        }
    }


    public void normal() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":syn  not  :" + i);
        }
    }

}
