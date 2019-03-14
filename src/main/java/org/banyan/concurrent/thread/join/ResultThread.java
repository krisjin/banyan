package org.banyan.concurrent.thread.join;

import java.util.List;

/**
 * User: krisjin
 * Date: 2016/9/18
 */
public class ResultThread implements Runnable {
    private final List<Thread> taskThread;
    private final List<Ware> wares;

    public ResultThread(List<Thread> taskThread, List<Ware> wares) {
        this.taskThread = taskThread;
        this.wares = wares;
    }

    @Override
    public void run() {
        try {
            for (Thread thread : taskThread) {
                thread.join();
            }
            double totalPrice = 0.00;
            for (Ware ware : wares) {
                totalPrice += ware.getPrice();
            }

            System.out.println("合并任务结果是：" + totalPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
