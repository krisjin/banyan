package org.banyan.concurrent.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * User:krisjin
 * Date:2019/4/26
 */
public class SimpleLockCount {


    public static void main(String[] args) throws InterruptedException {
        List data = new ArrayList();
        SimpleLock lock = new SimpleLock();

        Thread t1 = new Thread(new CountTask(lock, data));
        t1.setName("A");
        Thread t2 = new Thread(new CountTask(lock, data));
        t2.setName("B");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(data.size());

    }


    public static class CountTask implements Runnable {
        SimpleLock simpleLock;
        List data;


        public CountTask(SimpleLock simpleLock, List data) {
            this.simpleLock = simpleLock;
            this.data = data;
        }

        @Override
        public void run() {
            try {
                simpleLock.lock();
                for (int i = 0; i < 10; i++) {
                    data.add(i);

                    System.out.println(Thread.currentThread().getName() + " add..." + i);
                }

                simpleLock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
