package org.concurrent.thread.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: krisjin
 * Date: 2016/9/18
 */
public class Main {

    public static void main(String[] args) {

        final List<Ware> wareList = Collections.synchronizedList(new ArrayList<Ware>());


        List<Thread> threadList = Collections.synchronizedList(new ArrayList<Thread>());
        for (int i = 0; i < 10; i++) {
            Thread calculate = new Thread(new ApiTask(wareList));
            threadList.add(calculate);
            calculate.start();
        }
        Thread resultThread = new Thread(new ResultThread(threadList, wareList));

        resultThread.start();
        try {
            resultThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
