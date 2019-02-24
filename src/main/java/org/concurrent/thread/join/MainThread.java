package org.concurrent.thread.join;

/**
 * User:shijingui
 * Date:2019/2/23
 *  
 */
public class MainThread {


    public static void main(String[] args) {


        for (int i = 0; i < 6; i++) {
            SubThread s1 = new SubThread();
            s1.start();
            try {
                s1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.err.println("当前主线程运行完成...");
    }
}
