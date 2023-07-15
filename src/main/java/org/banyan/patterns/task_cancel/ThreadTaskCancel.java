package org.banyan.patterns.task_cancel;

/**
 * Pattern: Thread Task Cancel
 * <p>
 * Motivations: Tasks that offers a cancel option are common in programming.
 * Background tasks, implemented by threads, are common in Java too and often
 * they need to be canceled.
 * <p>
 * Intent: Show how to create a cancel mechanism using threads. Inside the
 * Runnable code, we use Thread.currentThread().isInterrupted() to identify the
 * thread state and to keep it running or not.
 * <p>
 * Applicability: When background tasks needs a cancel option.
 */
public class ThreadTaskCancel {

    private Thread thread;
    private Runnable task = () -> {
        while (!Thread.currentThread().isInterrupted()) {
            // keep going
        }
    };

    public void run() {
        thread = new Thread(task);
        thread.start();
    }

    public void cancel() {
        if (thread != null) {
            thread.interrupt();
        }
    }

}
