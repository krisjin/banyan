package org.concurrent.interrupt;

/**
 * 可以在 Thread 对象上调用 isInterrupted()方法来检查任何线程的中断状态。这里需要注意：线程一旦被中断，
 * isInterrupted()方法便会返回 true，而一旦 sleep()方法抛出异常，它将清空中断标志，此时isInterrupted()方法将返回 false。
 * <p/>
 * User : krisjin
 * Date: 2015/9/8
 * Time: 15:01
 */
public class InterruptCheck {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        System.out.println("1- thread isInterrupted() : " + thread.isInterrupted());

        //待决中断，中断自身
        thread.interrupt();
        System.out.println("2- thread isInterrupted() : " + thread.isInterrupted());
        System.out.println("3- thread isInterrupted() : " + thread.isInterrupted());

        try {
            Thread.sleep(2000);
            System.out.println("thread is not interrupted");
        } catch (InterruptedException e) {
            System.out.println("thread is  interrupted : ");
        }

        //抛出异常后，会清除中断标志，这里会返回false
        System.out.println("4- thread isInterrupted() : " + thread.isInterrupted());
    }


}
