package com.concurrent.interrupt;

/**
 * 测试当前线程是否已经中断。线程的中断状态 由该方法清除。换句话说，如果连续两次调用该方法，
 * 则第二次调用将返回 false（在第一次调用已清除了其中断状态之后，且第二次调用检验完中断状态前，
 * 当前线程再次中断的情况除外）。线程中断被忽略，因为在中断时不处于活动状态的线程将由此返回 false 的方法反映出来。
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/8
 * Time: 15:22
 */
public class InterruptReset {

    public static void main(String[] args) {
        System.out.println("Point X: Thread.interrupted()=" + Thread.interrupted());

        Thread.currentThread().interrupt();

        System.out.println("Point Y: Thread.interrupted()=" + Thread.interrupted());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Point Z: Thread.interrupted()=" + Thread.interrupted());
    }
}
