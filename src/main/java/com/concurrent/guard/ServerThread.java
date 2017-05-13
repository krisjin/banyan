package com.concurrent.guard;

import com.concurrent.util.SleepUtil;

/**
 * User shijingui
 * Date 2017/5/13
 */
public class ServerThread extends Thread {
    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            final Request request = requestQueue.getRequest();//得到请求
            SleepUtil.millisecond(100);
            System.out.println(Thread.currentThread().getName() + " handles " + request);
        }
    }
}
