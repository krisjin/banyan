package org.banyan.concurrent.masterworker;

import java.util.Map;
import java.util.Queue;

/**
 * Worker进程
 *
 * @author krisjin on 2017/5/13
 */
public class Worker implements Runnable {

    //任务队列，用于取得子任务
    protected Queue<Object> workQueue;

    //子任务处理结果集
    protected Map<String, Object> resultMap;


    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Object handle(Object input) {
        return input;
    }

    @Override
    public void run() {
        while (true) {
            Object input = workQueue.poll();
            if (input == null) break;
            Object result = handle(input);
            resultMap.put(Integer.toString(input.hashCode()), result);
        }

    }
}
