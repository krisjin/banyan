package org.concurrent.interrupt;

/**
 * main 线程中断它自身。除了将中断标志（它是 Thread 的内部标志）设置为 true 外，没有其他任何影响。线程被中断了，但 main 线程仍然运行，
 * main 线程继续监视实时时钟，并进入 try 块，一旦调用 sleep（）方法，
 * 它就会注意到待决中断的存在，并抛出 InterruptException。于是执行跳转到 catch 块，
 * 并打印出线程被中断的信息。最后，计算并打印出时间差。
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/8
 * Time: 14:43
 */
public class PendingInterrupt {

    public static void main(String[] args) {

        //是否执行线程终端操作
        boolean isInterrupt = false;

        if (isInterrupt) {
            Thread.currentThread().interrupt();
            //在执行中断操作并return;,后面的操作将不会执行
            //            return;
        }

        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(10000);
            System.out.println("current thread is not interrupt");
        } catch (InterruptedException e) {
            System.out.println("current thread is interrupt");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("interrupt is " + isInterrupt + "; cost:" + (endTime - startTime));
    }
}
