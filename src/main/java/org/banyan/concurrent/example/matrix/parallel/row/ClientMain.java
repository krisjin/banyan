package org.banyan.concurrent.example.matrix.parallel.row;

import org.banyan.concurrent.example.matrix.MatrixGenerator;

/**
 * User:krisjin
 * Date:2019/3/11
 */
public class ClientMain {

    public static void main(String[] args) {

        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);

        double[][] result = new double[matrix1.length][matrix2[0].length];
        long st = System.currentTimeMillis();
        ParallelRowMultiplier.multiply(matrix1, matrix2, result);

        System.out.println("Parallel Row:" + (System.currentTimeMillis() - st));
    }
}
