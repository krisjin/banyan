package org.banyan.concurrent.sync;

/**
 * User:krisjin
 * Date:2019/3/12
 */
public class SynObjectMain {

    public static void main(String[] args) {
        SynIntrinsic synIntrinsic = new SynIntrinsic();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synIntrinsic.synBlock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synIntrinsic.synMethod();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                synIntrinsic.normal();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }


}
