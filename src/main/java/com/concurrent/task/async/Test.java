package com.concurrent.task.async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * User: shijingui
 * Date: 2016/2/16
 */
public class Test {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        test1();
    }

    /**
     * 异步执行任务，获得 Future 后添加 Callback
     *
     * @throws InterruptedException
     */
    private static void test1() throws InterruptedException, ExecutionException {

        final TaskPromise promise = new DefaultTaskPromise();
        final TaskFuture future = promise.getFuture();
        final CountDownLatch latch = new CountDownLatch(1);

//        TaskFuture taskFuture = future.onComplete(new TaskCallback() { // 添加结束 Callback
//            @Override
//            public TaskFuture apply(TaskFuture f) {
//                latch.countDown();
//                return f;
//            }
//        });
//        future.onSuccess(new TaskCallback() {
//            @Override
//            public TaskFuture apply(TaskFuture f) {
//                System.out.println("success process.....");
//                return f;
//            }
//        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                promise.setSuccess("done");

            }
        }).start();
        System.out.println(future.get());
        latch.await();

    }
}
