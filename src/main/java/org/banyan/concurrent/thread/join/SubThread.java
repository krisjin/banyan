package org.banyan.concurrent.thread.join;

/**
 * User:krisjin
 * Date:2019/2/23
 *  
 */
public class SubThread extends Thread {

    @Override
    public void run() {
        System.err.println(Thread.currentThread().getId() + ":子线程执行完成...");
    }
}
