package com.concurrent.demo03;

public class Read implements Runnable {
    private BufferInterruptibly buffer;

    public Read(BufferInterruptibly buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            buffer.read();
        } catch (InterruptedException e) {
            System.out.println("太慢了，不想读了，我去干别的事.");
        }
        System.out.println("读结束");
    }
}
