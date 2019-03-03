package org.concurrent.base;

/**
 * <p/>
 * User : krisjin
 * Date: 2015/9/15
 * Time: 16:00
 */
public class DeprecatedSuspendResume implements Runnable {
    private volatile int firstVal;

    private volatile int secondVal;

    public static void main(String[] args) {
        DeprecatedSuspendResume dsr = new DeprecatedSuspendResume();
        Thread t = new Thread(dsr);
        t.start();

        //休眠1秒，让其他线程有机会获得执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException x) {
        }
        for (int i = 0; i < 10; i++) {
            //挂起线程
//            methodA.suspend();
            System.out.println("dsr.valEqual()=" + dsr.valEqual());
            //恢复线程
//            methodA.resume();
            try {
                //线程随机休眠0~2秒
                Thread.sleep((long) (Math.random() * 2000.0));
            } catch (InterruptedException x) {
                //略
            }
        }
        System.exit(0); //中断应用程序
    }

    @Override
    public void run() {

    }

    public boolean valEqual() {
        return firstVal == secondVal;
    }

    private void workMethod() {
        int val = 1;
        while (true) {
            stepOne(val);
            stepTwo(val);
            val++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //赋值后，休眠300毫秒，从而使线程有机会在stepOne操作和stepTwo操作之间被挂起
    private void stepOne(int newVal) {
        firstVal = newVal;
        try {
            //模拟长时间运行的情况
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stepTwo(int newVal) {
        secondVal = newVal;
    }
}
