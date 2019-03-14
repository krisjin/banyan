package org.banyan.concurrent.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Master-Worker 是一种使用多线程进行数据处理的结构。多个Worker进程协作处理用户请求，Master进程负责维护Worker进程，
 * 并整合最终处理结果。
 * Master-Worker模式是将一种串行任务并行化的方法，被分解的子任务在系统中可以被并行处理。同时，如果有需要，Master进程
 * 不需要等待所有子任务都完成计算，就可以根据已有的部分结果集计算最终结果。
 * User krisjin
 * Date 2017/5/12
 */
public class Master {

    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<Object>();
    //worker进程队列
    protected Map<String, Thread> threadMap = new HashMap<String, Thread>();
    //子任务处理结果集
    protected Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

    /**
     * structure a master worker,need  a worker process logic
     *
     * @param worker
     * @param countWorker
     */
    public Master(Worker worker, int countWorker) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < countWorker; i++) {
            threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));

        }
    }

    //are all the sub task completed
    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }

        return true;
    }

    /**
     * submit a task
     *
     * @param obj
     */
    public void submit(Object obj) {
        workQueue.add(obj);
    }

    /**
     * get sub task result
     *
     * @return
     */
    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    /**
     * start run all the worker process，conduct handle
     */
    public void execute() {

        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }

}
