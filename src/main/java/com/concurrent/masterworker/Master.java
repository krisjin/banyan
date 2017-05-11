package com.concurrent.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User shijingui
 * Date 2017/5/12
 */
public class Master {

    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<Object>();
    //worker进程队列
    protected Map<String, Thread> threadMap = new HashMap<String, Thread>();
    //结果集
    protected Map<String, Object> resultmap = new HashMap<String, Object>();

    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }


}
