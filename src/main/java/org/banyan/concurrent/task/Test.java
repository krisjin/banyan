package org.banyan.concurrent.task;

/**
 * User:krisjin
 * Date:2020-07-08
 */
public class Test {
    public static void main(String[] args) {

        for (int i = 0; i < 1000000; i++) {
            try {
                CallableFuture.main();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
