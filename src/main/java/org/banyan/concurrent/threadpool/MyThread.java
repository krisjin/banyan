package org.banyan.concurrent.threadpool;

public class MyThread implements Runnable {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
