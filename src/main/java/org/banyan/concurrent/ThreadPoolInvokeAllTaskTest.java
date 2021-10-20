package org.banyan.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolInvokeAllTaskTest {

    public static void invokeAllTask() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
        List<Callable<Boolean>> task = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            task.add(() -> {
                new Say().s();
                return false;
            });
        }

        List<Future<Boolean>> futures = executor.invokeAll(task);
        for (Future<Boolean> future : futures) {
            System.err.println(future.get() + "-----");
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
