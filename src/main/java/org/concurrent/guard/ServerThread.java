package org.concurrent.guard;

import org.concurrent.future.FutureData;
import org.concurrent.future.RealData;
import org.concurrent.util.SleepUtil;

/**
 * server side thread
 * User shijingui
 * Date 2017/5/13
 */
public class ServerThread extends Thread {
    private RequestQueue requestQueue;

    /**
     * structure a server thread
     *
     * @param requestQueue accept request queue
     * @param name
     */
    public ServerThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            final Request request = requestQueue.getRequest();//得到请求
            FutureData futureData = (FutureData) request.getResponse();
            RealData realData = new RealData(request.getName());
            futureData.setRealData(realData);
            SleepUtil.millisecond(100);//simulation request process cost
            System.out.println(Thread.currentThread().getName() + " handles " + request);
        }
    }
}
