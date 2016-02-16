package com.concurrent.task;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public interface TaskFuture extends Future<Object> {

    /**
     * �?查任务是否成功完�?
     * 
     * @return
     */
    boolean isSuccess();

    /**
     * 获取任务失败原因
     * 
     * @return
     */
    Throwable cause();

    /**
     * 如果任务完成，则返回任务结果，如果任务失败或者未完成则直接返�? null
     * 
     * @return
     */
    Object getNow();

    /**
     * 等待直到任务完成
     * 
     * @return
     * @throws InterruptedException
     */
    TaskFuture await() throws InterruptedException;

    /**
     * �?大等待超时时间，如果任务没完成返�? false, 如果完成则返�? true
     * 
     * @param timeout
     *            �?大等待超时时�?
     * @param unit
     *            超时时间单位
     * @return
     * @throws InterruptedException
     */
    boolean await(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * 任务完成时回调动�?
     * 
     * @param callback
     *            回调动作
     * @return
     */
    TaskFuture onComplete(TaskCallback callback);

    /**
     * 任务成功时回调动�?
     * 
     * @param callback
     *            回调动作
     * @return
     */
    TaskFuture onSuccess(TaskCallback callback);

    /**
     * 任务失败时回调动�?
     * 
     * @param callback
     *            回调动作
     * @return
     */
    TaskFuture onFailure(TaskCallback callback);
    
    boolean hasAttr(String key);
    
    Object getAttr(String key);
    
    TaskFuture addAttr(String key, Object value);
    
    Object removeAttr(String key);
    
    
}
