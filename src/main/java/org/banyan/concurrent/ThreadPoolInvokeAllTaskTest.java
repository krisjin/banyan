package org.banyan.concurrent;

import java.math.BigDecimal;
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
            System.out.println(future.get() + "-----");
        }
    }

    public static void main(String[] args) {
        try {
            invokeAllTask();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        Double dd=1.3145962650000082E7;13145962.650000082
//        Double dd=1.314596265E7;       13145962.65

        BigDecimal decimal = new BigDecimal("1.314596265E7");
        System.err.println(decimal.toString());


    }

    static class Say {
        public void s() {
            System.out.println(1111);
        }
    }


}
