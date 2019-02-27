package org.concurrent.lock;

/**
 * <p>
 * 死锁是两个或更多线程阻塞着等待其它处于死锁状态的线程所持有的锁。死锁通常发生在多个线程同时但以不同的顺序请求同一组锁的时候。
 * 例如，如果线程1锁住了A，然后尝试对B进行加锁，同时线程2已经锁住了B，接着尝试对A进行加锁，这时死锁就发生了。线程1永远得不到B，
 * 线程2也永远得不到A，并且它们永远也不会知道发生了这样的事情。为了得到彼此的对象（A和B），它们将永远阻塞下去。这种情况就是一个死锁。
 * 如下情况：
 * Thread 1 locks A ,waits for B
 * Thread 2 locks B ,waits for A
 * </p>
 * 热锁：
 * 热锁往往也是导致系统性能瓶颈的主要因素，其表现特征为多个线程对临界区或锁的竞争。可能出现：
 * ——频繁的线程上下文切换：  从操作系统对线程的调度来看，当线程在等待资源而阻塞的时候，操作系统会将之切换出来，放到等待的对列。当线程获得资源之后，调度
 * 算法会将这个线程切换回去，放到执行队列中去。
 * ——大量的系统调用：因为线程上下文的切换，以及热锁的竞争，或者临界区的频繁的进出，都可能是有大量的系统调用。
 * ——大部分CPU开销用在“系统态”：线程上下文切换和系统调用，都会导致CPU在“系统态”运行。换而言之，虽然系统很忙碌，但是CPU用在用户态的比例较小。应用程序
 * 得不到充分的CPU资源。
 * ——随着CPU数目的增多，系统的性能反而下降。因为CPU数目多，同时运行的线程就越多。可能就造成更频繁的线程上下文切换和系统态的CPU开销，从而导致更糟糕的性能。
 * User : krisjin
 * Date: 2015/10/12
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }


}
