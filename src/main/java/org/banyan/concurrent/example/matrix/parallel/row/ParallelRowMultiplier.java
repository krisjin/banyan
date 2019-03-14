package org.banyan.concurrent.example.matrix.parallel.row;

import java.util.ArrayList;
import java.util.List;

/**
 * User:krisjin
 * Date:2019/3/11
 *  
 */
public class ParallelRowMultiplier {


    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {

        List<Thread> threads = new ArrayList<>();

        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;

        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;

        for (int i = 0; i < rows1; i++) {
            RowMultiplierTask task = new RowMultiplierTask(result, matrix1, matrix2, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);

            if (threads.size() % 10 == 0) {
                waitForThreads(threads);
            }
        }
    }

    private static void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threads.clear();
    }

}
