package com.concurrent.demo03;

public class Write implements Runnable {
    private BufferInterruptibly buffer;

    public Write(BufferInterruptibly buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.write();
    }

}
