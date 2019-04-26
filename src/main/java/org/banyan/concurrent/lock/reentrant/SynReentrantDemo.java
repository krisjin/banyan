package org.banyan.concurrent.lock.reentrant;

/**
 * 该类中声名了两个同步方法methodA 和 methodB,
 * User:krisjin
 * Date:2019/4/26
 */
public class SynReentrantDemo {

    public synchronized void methodA() {
        System.out.println("methodA...");
        methodB();

    }

    public synchronized void methodB() {
        System.out.println("methodB...");
    }


    public static void main(String[] args) {
        new Thread(new Runnable() {
            SynReentrantDemo synReentrantDemo = new SynReentrantDemo();

            @Override
            public void run() {
                synReentrantDemo.methodA();
            }
        }).start();
    }

}
