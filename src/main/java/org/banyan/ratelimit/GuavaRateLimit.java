package org.banyan.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @User krisjin
 * @date 2020/9/12
 */
public class GuavaRateLimit {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {

        RateLimiter r = RateLimiter.create(5);
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            service.submit(new MyTask(latch, r));
            latch.countDown();
            System.out.println("countdown:" + latch.getCount());
        }
        System.out.println("countdown over");
        service.shutdown();

    }


    static class MyTask implements Runnable {
        private CountDownLatch latch;
        private RateLimiter limiter;

        public MyTask(CountDownLatch latch, RateLimiter limiter) {
            this.latch = latch;
            this.limiter = limiter;
        }

        @Override
        public void run() {
            try {
                //使得线程同时触发
                latch.await();
                System.out.println("time " + System.currentTimeMillis() + "ms :" + limiter.tryAcquire());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
