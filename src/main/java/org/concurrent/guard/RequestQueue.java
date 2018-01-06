package org.concurrent.guard;

import java.util.LinkedList;

/**
 * request object collection
 * User shijingui
 * Date 2017/5/13
 */
public class RequestQueue {
    private LinkedList queue = new LinkedList();

    public synchronized Request getRequest() {
        while (queue.size() == 0) {
            try {
                wait();//等待直到有新的Request加入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (Request) queue.remove();
    }

    public synchronized void addRequest(Request request) {
        queue.add(request);//add new request
        notifyAll();//notify getRequest方法
    }
}
