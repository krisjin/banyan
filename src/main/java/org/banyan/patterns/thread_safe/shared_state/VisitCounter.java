package org.banyan.patterns.thread_safe.shared_state;


import org.banyan.patterns.GuardedBy;
import org.banyan.patterns.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Pattern: Protected Shared State
 * <p>
 * Example: A simple Counter example.
 */
@ThreadSafe
public class VisitCounter {

    @GuardedBy("this")
    private int value;

    public synchronized int actualValue() {
        return value;
    }

    public synchronized void increase() {
        value++;
    }

    public synchronized void decrease() {
        value--;
    }

    public static void main(String[] args) {
        VisitCounter counter = new VisitCounter();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 50; i++) {
            System.out.println("value " + counter.actualValue() + " i " + i);
            threadPool.execute(() -> counter.increase());
        }
        threadPool.shutdown();
        System.out.println(counter.actualValue());
    }
}
