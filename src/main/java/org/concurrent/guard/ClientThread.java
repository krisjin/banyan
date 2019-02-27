package org.concurrent.guard;

import org.concurrent.future.FutureData;
import org.concurrent.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User krisjin
 * Date 2017/5/13
 */
public class ClientThread extends Thread {
    private RequestQueue requestQueue;
    private List<Request> myRequest = new ArrayList<Request>();

    public ClientThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request("RequestId: " + i + " ThreadName:" + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            request.setResponse(new FutureData());
            myRequest.add(request);
            requestQueue.addRequest(request);//提交请求
            SleepUtil.millisecond(10);//客户端请求的速度，快于服务端处理的速度
            System.out.println("ClientThread Name is :" + Thread.currentThread().getName());
        }
        //loop the request list , wait get response result
        for (Request request : myRequest) {
            //如果服务起还没有处理完，会在这里等待。
            System.out.println("ClientThread name is:" + Thread.currentThread().getName() + " Response is:" + request.getResponse().getResult());
        }

        System.out.println(Thread.currentThread().getName() + " request end.");
    }
}
