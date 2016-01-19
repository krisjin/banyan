package com.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User: shijingui
 * Date: 2016/1/11
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        try {
            test1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test1() throws InterruptedException {
        BlockingQueue blockingQueue = new LinkedBlockingQueue<String>(5);
        //put方式是阻塞的
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put(null);

        //add 方式会抛出异常
//        blockingQueue.add("a");
//        blockingQueue.add("b");
//        blockingQueue.add("c");

        // offer 设置超时时间
//        blockingQueue.offer("a", 3, TimeUnit.SECONDS);
//        blockingQueue.offer("b", 3, TimeUnit.SECONDS);
//        blockingQueue.offer("c", 3, TimeUnit.SECONDS);


    }

}
