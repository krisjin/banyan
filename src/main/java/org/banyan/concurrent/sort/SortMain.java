package org.banyan.concurrent.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User:krisjin
 * Date:2019/2/24
 */
public class SortMain {
    private final static int maxNum = 10000000;
    private final static int threadNum = 4;

    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        int[] numArr = initData();

        int[] resultArr = null;
        int numOffset = maxNum / threadNum;

        final List<int[]> sortedList = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        CyclicBarrier barrier = new CyclicBarrier(threadNum,
                new Runnable() {
                    @Override
                    public void run() {
                        if (sortedList != null && sortedList.size() > 0) {
                            System.err.println("");
                            int[] tmp = new int[1];
                            for (int[] nums : sortedList) {
                                List<Integer> result = mergeArrays(tmp, nums);
                                tmp = toIntArray(result);
                            }
                            System.out.println(tmp.length);
                        }
                    }
                });


        for (int i = 0; i < threadNum; i++) {
            int[] splitData = null;
            if (i == 0) {
                splitData = Arrays.copyOfRange(numArr, 0, numOffset);
            } else {
                splitData = Arrays.copyOfRange(numArr, i * numOffset, (i + 1) * numOffset);
            }

            SortTask sortTask = new SortTask(barrier, splitData, sortedList);
            service.submit(sortTask);
        }
        System.out.println(System.currentTimeMillis() - st);
        service.shutdown();


    }


    /**
     * init random number
     *
     * @return
     */
    private static int[] initData() {

        Random random = new Random();
        int[] numArr = new int[maxNum];
        for (int i = 0; i < maxNum; i++) {
            int randomNum = random.nextInt(maxNum);
            numArr[i] = randomNum;
        }


        return numArr;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, writeIdx = m + n - 1;
        while (i >= 0 && j >= 0)
            nums1[writeIdx--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        while (j >= 0)
            nums1[writeIdx--] = nums2[j--];
    }


    public static List<Integer> mergeArrays(int[] arrayOne, int[] arrayTwo) {
        int arrayOneLen = arrayOne.length;
        int arrayTwoLen = arrayTwo.length;
        List<Integer> resultArray = new ArrayList<Integer>();
        int i = 0, j = 0;

        while (i < arrayOneLen || j < arrayTwoLen) {
            if (i == arrayOneLen && j < arrayTwoLen) {
                resultArray.add(arrayTwo[j]);
                j++;
            } else if (i < arrayOneLen && j == arrayTwoLen) {
                resultArray.add(arrayOne[i]);
                i++;
            } else {
                if (arrayOne[i] <= arrayTwo[j]) {
                    resultArray.add(arrayOne[i]);
                    i++;
                } else if (arrayOne[i] > arrayTwo[j]) {
                    resultArray.add(arrayTwo[j]);
                    j++;
                }
            }
        }

        return resultArray;
    }

    private static int[] toIntArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = list.get(i);
        return ret;
    }
}
