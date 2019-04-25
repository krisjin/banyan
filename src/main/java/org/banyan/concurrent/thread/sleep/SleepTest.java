package org.banyan.concurrent.thread.sleep;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User:shijingui
 * Date:2019/4/7
 *  
 */
public class SleepTest {

    ReentrantLock lock = new ReentrantLock();


    Thread threadA = new Thread() {

        public void run() {

            System.err.println("Thread A start sleep... ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    Thread threadB = new Thread() {

        public void run() {
            System.err.println("Thread B start sleep... ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


}
