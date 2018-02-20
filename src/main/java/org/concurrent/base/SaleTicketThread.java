package org.concurrent.base;

/**
 * 非线程安全
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/15
 * Time: 14:47
 */
public class SaleTicketThread implements Runnable {
    private int ticketNum = 5;

    //主线程
    public static void main(String[] args) {
        SaleTicketThread saleTicketThread = new SaleTicketThread();
        new Thread(saleTicketThread, "saler_1").start();
        new Thread(saleTicketThread, "saler_2").start();
        new Thread(saleTicketThread, "saler_3").start();

    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            if (ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + " priority " + Thread.currentThread().getPriority() + " sale ticket,current ticket num is " + ticketNum--);
            }
        }
    }
}
