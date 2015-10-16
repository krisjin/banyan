package test;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/10/16
 * Time: 15:40
 */
public class Daemon {

    public static void main(String[] args) {
        Thread t = new Thread(new DaemonRunner());
        t.setDaemon(true);
        t.setPriority(10);
        t.setName("Daemon thread");
        t.start();
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Daemon thread run.");
            }
        }
    }
}
