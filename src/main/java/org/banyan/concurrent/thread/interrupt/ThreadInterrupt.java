package org.banyan.concurrent.thread.interrupt;

/**
 * @User krisjin
 * @date 2020/8/26
 */
public class ThreadInterrupt {

    public static void main(String[] args) {
        try {
            TestTask t = new TestTask();
            t.start();
            Thread.sleep(333);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("main end");
    }

    static class TestTask extends Thread {
        public void run() {
            System.err.println("task execute....");
//            try {
                for (int i = 0; i < 1000000000; i++) {
                    if (this.isInterrupted()) {
                        System.err.println("线程停止....");
//                        throw new InterruptedException();
                    }
                }
                System.err.println(11);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}
