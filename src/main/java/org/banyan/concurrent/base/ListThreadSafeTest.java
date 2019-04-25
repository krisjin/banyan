package org.banyan.concurrent.base;

import java.util.ArrayList;
import java.util.List;

/**
 * User:krisjin
 * Date:2019/4/25
 *  
 */
public class ListThreadSafeTest {
    public static List<Integer> numberList = new ArrayList<Integer>();


    public static void main(String[] args) {

        Thread t1 = new Thread(new AddToListTask(0));
        Thread t2 = new Thread(new AddToListTask(1));
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(numberList.size());

    }


    public static class AddToListTask implements Runnable {
        int startNum;

        public AddToListTask(int startNum) {
            this.startNum = startNum;
        }

        @Override
        public void run() {
            int count = 0;
            synchronized (ListThreadSafeTest.class){

                while (count < 1000000) {
                    numberList.add(startNum);
                    startNum += 2;
                    count++;
                }
            }

        }
    }

}
