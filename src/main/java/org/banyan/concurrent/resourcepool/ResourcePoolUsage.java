package org.banyan.concurrent.resourcepool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResourcePoolUsage {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        ResourcePool pool = new ResourcePool<Integer>(15,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 11, 12, 13, 14));
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            executor.execute(() -> {
                try {
                    Object value = pool.get(60);
                    System.out.println("Value taken: " + value);
                    Thread.sleep(random.nextInt(5000));
                    pool.release(value);
                    System.out.println("Value released " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
