package org.banyan.concurrent.interrupt;

/**
 * User : krisjin
 * Date: 2015/9/8
 */
public class SleepInterrupt implements Runnable {

    public static void main(String[] args) {
        SleepInterrupt sleepInterrupt = new SleepInterrupt();
        Thread threadA = new Thread(sleepInterrupt);
        threadA.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main() - interrupting other thread");
        threadA.interrupt();
        System.out.println("main() - end");
    }

    @Override
    public void run() {
        try {
            System.out.println("run() sleep 10 seconds");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("run() - interrupted while sleeping");
            //处理完中断异常后，返回到run（）方法人口，.如果没有return，线程不会实际被中断，它会继续打印下面的信息
            return;
        }
        System.out.println("run() - leaving normally");
    }

}
