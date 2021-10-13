package org.banyan.concurrent.sort;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * User:krisjin
 * Date:2019/2/24
 */
public class SortTask implements Runnable {

    private final int[] numArr;
    private List<int[]> resultData;
    private CyclicBarrier cyclicBarrier;

    public SortTask(CyclicBarrier cyclicBarrier, final int[] numArr, List<int[]> resultData) {
        this.numArr = numArr;
        this.resultData = resultData;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        sort(numArr, 0, numArr.length - 1);
        synchronized (resultData) {
            resultData.add(numArr);
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


    public static void sort(int numArr[], int low, int high) {
        int i, j, index;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        index = numArr[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && numArr[j] >= index)
                j--;
            if (i < j)
                numArr[i++] = numArr[j];// 用比基准小的记录替换低位记录
            while (i < j && numArr[i] < index)
                i++;
            if (i < j) // 用比基准大的记录替换高位记录
                numArr[j--] = numArr[i];
        }
        numArr[i] = index;// 将基准数值替换回 a[i]
        sort(numArr, low, i - 1); // 对低子表进行递归排序
        sort(numArr, i + 1, high); // 对高子表进行递归排序

    }
}
