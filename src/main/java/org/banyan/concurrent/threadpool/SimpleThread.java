package org.banyan.concurrent.threadpool;

public class SimpleThread implements Runnable {
    private String name;

    public SimpleThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            //业务线程执行时间
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
