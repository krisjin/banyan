package org.banyan.features.atomics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomics can be used from the java.util.concurrent.atomic.* package.
 * <p>
 * An atomic operation is a compound action that totally completes out totally
 * fails, not supporting inconsistent values or results during it's execution.
 * <p>
 * The classes in this package supports atomic operations on single variables,
 * having get and set (read and write) methods that behave like a volatile
 * variable.
 * <p>
 * The compareAndSet are commonly used in non-blocking algorithms. They
 * basically tries to set a new value to a specified field, and it returns a
 * boolean indicating success or not. All atomic, only blocking briefly.
 * <p>
 * Interesting classes in this package are: AtomicBoolean, AtomicLong,
 * AtomicReference<T>, AtomicMarkableReference<T> and
 * AtomicReferenceFieldUpdater<T, V>.
 */
public class UsingAtomics {

    /*
     * A Counter using AtomicInteger
     */
    static class AtomicCounter {
        private AtomicInteger atomicInteger = new AtomicInteger(0);

        public void increment() {
            atomicInteger.incrementAndGet();
        }

        public void decrement() {
            atomicInteger.decrementAndGet();
        }

        public int get() {
            return atomicInteger.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();
        ExecutorService ctp = Executors.newCachedThreadPool();
        for (int i = 0; i < 10_000; i++) {
            ctp.execute(() -> counter.increment());
        }
        ctp.shutdown();
        ctp.awaitTermination(4000, TimeUnit.SECONDS);
        System.out.println("Result shound be 10000: Actual result is: " + counter.get());
        System.out.println(10_000);


    }
}
