package com.concurrent.base;

/**
 * 非线程安全
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/15
 * Time: 14:47
 */
public class SaleTicketThread implements Runnable {
    private int ticketNum = 5;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (ticketNum > 0) {

                System.out.println(Thread.currentThread().getName() + " priority " + Thread.currentThread().getPriority() + " sale ticket,current ticket num is " + ticketNum--);
            }
        }
    }

    //主线程
    public static void main(String[] args) {
        SaleTicketThread saleTicketThread = new SaleTicketThread();
        new Thread(saleTicketThread).start();
        new Thread(saleTicketThread).start();
        new Thread(saleTicketThread).start();

    }
}
