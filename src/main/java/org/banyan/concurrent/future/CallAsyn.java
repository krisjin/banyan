package org.banyan.concurrent.future;

import java.util.concurrent.*;

/**
 * @author krisjin on 2019/5/20
 */
public class CallAsyn {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<String> future = es.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(10);
                return "Hello World!";
            }
        });

        try {
            String s = future.get();
            System.out.println(s);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}
