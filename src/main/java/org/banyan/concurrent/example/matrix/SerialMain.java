package org.banyan.concurrent.example.matrix;

/**
 * User:krisjin
 * Date:2019/3/10
 */
public class SerialMain {

    public static void main(String[] args) {

        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);

        double[][] result = new double[matrix1.length][matrix2[0].length];
        long st = System.currentTimeMillis();
        SerialMultiplier.multiply(matrix1, matrix2, result);

        System.out.println("Serial:" + (System.currentTimeMillis() - st));
    }

}
