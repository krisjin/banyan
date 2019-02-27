package org.concurrent.thread.pool;

import org.concurrent.util.SleepUtil;

import java.util.concurrent.*;

/**
 * User: krisjin
 * Date: 2017/3/7
 */
public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        AtomicInteger count = new AtomicInteger(1);
        ExecutorService exec = Executors.newFixedThreadPool(15);
        final BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<Future<String>>();
        //实例化CompletionService
        final CompletionService<String> completionService = new ExecutorCompletionService<String>(exec, queue);


        for (int i = 0; i < 10; i++) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    SleepUtil.second(2);
                    System.out.println(Thread.currentThread().getName() + " 休息了 ");
                    return "A";
                }
            });
        }


        for (int i = 0; i < 10; i++) {
            try {
                //谁最先执行完成，直接返回
                Future<String> f = completionService.take();
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        exec.shutdown();

    }
}
