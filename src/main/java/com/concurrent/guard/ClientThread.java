package com.concurrent.guard;

import com.concurrent.util.SleepUtil;

/**
 * User shijingui
 * Date 2017/5/13
 */
public class ClientThread extends Thread {
    private RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request("RequestId: " + i + " ThreadName:" + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            requestQueue.addRequest(request);//提交请求
            SleepUtil.millisecond(10);//客户端请求的速度，快于服务端处理的速度
        }
    }
}
