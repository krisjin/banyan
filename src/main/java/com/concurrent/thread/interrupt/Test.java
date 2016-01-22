package com.concurrent.thread.interrupt;

public class Test {

    public static void main(String[] args) {
        BufferInterruptibly buffer = new BufferInterruptibly();

        final Write write = new Write(buffer);
        final Read read = new Read(buffer);

        final Thread wThread = new Thread(write);
        final Thread rThread = new Thread(read);
        wThread.start();
        rThread.start();

        new Thread(new Runnable() {

            @Override
            public void run() {

                long startDate = System.currentTimeMillis();

                for (; ; ) {
                    if (System.currentTimeMillis() - startDate > 5000) {
                        System.out.println("不等了，尝试中断!");
                        rThread.interrupt();
                        break;
                    }
                }

            }
        }).start();

    }

}
