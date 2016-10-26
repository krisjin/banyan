package test;

import java.util.concurrent.CountDownLatch;

/**
 * 这个异常问题本质原因是我们创建了太多的线程，而能创建的线程数是有限制的，导致了异常的发生。能创建的线程数的具体计算公式如下：
 * (MaxProcessMemory - JVMMemory - ReservedOsMemory) / (ThreadStackSize) = Number of threads
 * MaxProcessMemory      指的是一个进程的最大内存
 * JVMMemory             JVM内存
 * ReservedOsMemory      保留的操作系统内存
 * ThreadStackSize      线程栈的大小
 * <p/>
 * User: shijingui
 * Date: 2016/10/25
 */
public class NativeOutOfMemoryErrorTest {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("i = " + i);
            new Thread(new HoldThread()).start();
        }
    }

    static class HoldThread extends Thread {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
            }
        }
    }
}
