package org.concurrent.example.matrix.parallel.individual;

import org.concurrent.example.matrix.MatrixGenerator;

/**
 * User:krisjin
 * Date:2019/3/11
 *  
 */
public class ClientMain {

    public static void main(String[] args) {

        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);

        double[][] result = new double[matrix1.length][matrix2[0].length];
        long st = System.currentTimeMillis();
        ParallelIndividualMultiplier.multiply(matrix1, matrix2, result);

        System.out.println("Parallel individual:" + (System.currentTimeMillis() - st));
    }
}
