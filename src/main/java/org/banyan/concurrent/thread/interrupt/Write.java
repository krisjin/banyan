package org.banyan.concurrent.thread.interrupt;

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
