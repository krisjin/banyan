package org.banyan.concurrent.sync;

/**
 * User:krisjin
 * Date:2019/3/12
 */
public class SynObjectMain {

    public static void main(String[] args) {
        SynObject synObject = new SynObject();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synObject.synBlock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synObject.synMethod();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synObject.normal();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }


}
