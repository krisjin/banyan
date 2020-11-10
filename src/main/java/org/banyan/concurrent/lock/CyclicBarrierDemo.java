package org.banyan.concurrent.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author krisjin
 * @date 2020/11/8
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //存储每个线程生成的随机数
        Map<String, Integer> counter = new ConcurrentHashMap<>();

        //固定大小，如果屏障设置的与提交线程数不一致就会一直阻塞
        int threadNum = 4;

        //创建一个拥有4个线程数的同步屏障，设置barrier action master,作为聚合执行操作
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Master(counter));

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Worker(barrier, counter)).start();
        }
    }

    //聚合线程
    static class Master implements Runnable {
        private Map<String, Integer> counter;

        public Master(Map<String, Integer> counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            System.err.println(Thread.currentThread().getName() + " 执行聚合");
            AtomicInteger total = new AtomicInteger();
            counter.forEach((k, v) -> {
                total.addAndGet(v);
            });

            System.err.println(Thread.currentThread().getName() + " 执行聚合:" + total.get());
        }
    }


    //工作线程
    static class Worker implements Runnable {
        private CyclicBarrier barrier;
        private Map<String, Integer> counter;

        public Worker(CyclicBarrier barrier, Map<String, Integer> counter) {
            this.barrier = barrier;
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 开始计算");
                counter.put(Thread.currentThread().getName(), 1);
                //执行业务逻辑，到达屏障，等最后一个线程来到这里，继续执行下面的逻辑
                barrier.await(200, TimeUnit.MILLISECONDS);

                //这里继续执行各自的逻辑
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
