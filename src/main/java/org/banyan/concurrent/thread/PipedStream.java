package org.banyan.concurrent.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * User: krisjin
 * Date: 2016/2/14
 */
public class PipedStream {

    public static void main(String... args) throws IOException {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();

        out.connect(in);
        Thread thread = new Thread(new Print(in), "PrintThread");
        thread.start();

        int receive = 0;
        while ((receive = System.in.read()) != -1) {
            out.write(receive);
        }
    }

    static class Print implements Runnable {

        private PipedInputStream in;

        public Print(PipedInputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.println(receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
