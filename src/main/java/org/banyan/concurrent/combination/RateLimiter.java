package org.banyan.concurrent.combination;

import java.util.concurrent.Semaphore;

public class RateLimiter {
    private final Semaphore semaphore;
    private final int maxPermits;
    private long lastRefillTime;
    private final long refillInterval;

    public RateLimiter(int maxPermits, long refillInterval) {
        this.maxPermits = maxPermits;
        this.refillInterval = refillInterval;
        this.semaphore = new Semaphore(maxPermits);
        this.lastRefillTime = System.currentTimeMillis();
    }

    public void acquire() throws InterruptedException {
        if (System.currentTimeMillis() - lastRefillTime >= refillInterval) {
            refill();
        }
        semaphore.acquire();
    }

    public void release() {
        semaphore.release();
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTime;
        int permitsToAdd = (int) (elapsedTime / refillInterval * maxPermits);
        for (int i = 0; i < permitsToAdd; i++) {
            semaphore.release();
        }
        lastRefillTime = now;
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(5, 1);

        for (int i = 0; i < 10000; i++) {
            try {
                rateLimiter.acquire();
                System.out.println("请求 " + (i + 1) + " 被允许");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rateLimiter.release();
            }
        }
    }
}
