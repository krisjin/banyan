package org.concurrent.example.matrix;

import java.util.Random;

/**
 * User:krisjin
 * Date:2019/3/10
 */
public class MatrixGenerator {

    /**
     * 随机生成矩阵
     *
     * @param rows
     * @param columns
     * @return
     */
    public static double[][] generate(int rows, int columns) {
        double[][] ret = new double[rows][columns];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ret[i][j] = random.nextDouble() * 10;
            }
        }
        return ret;
    }

}
