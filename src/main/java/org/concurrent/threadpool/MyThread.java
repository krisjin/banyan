package org.concurrent.threadpool;

/**
 * @author shijingui on 2017/5/20
 */
public class MyThread implements Runnable {

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);//use sleep method instead a business logic
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
