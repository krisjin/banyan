package org.banyan.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolInvokeAllTaskTest {
    static int FLAG = 2;

    public static void invokeAllTask() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
        List<Callable<Boolean>> task = new ArrayList<>();
        for (int i = 1; i <= 5; i++)
            task.add(() -> {
                new Say().s();
                return false;
            });
        List<Future<Boolean>> futures = THREAD_POOL.invokeAll(task);
        for (Future<Boolean> t : futures) {
            System.err.println(t.get() + "-----");
        }
    }

    public static void main(String[] args) {
        try {
            invokeAllTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class Say {
        public void s() {
            System.out.println(1111);
        }
    }


}
