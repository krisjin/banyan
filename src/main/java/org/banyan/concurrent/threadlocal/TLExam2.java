package org.banyan.concurrent.threadlocal;

import java.util.concurrent.CountDownLatch;

/**
 * @author krisjin
 * @date 2020/11/2
 */
public class TLExam2 {

    public static void main(String[] args) throws InterruptedException {
        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        InnerClass innerClass = new InnerClass();

        for (int i = 1; i <= threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
                innerClass.set("hello world");
                countDownLatch.countDown();
            }, "thread - " + i).start();
        }
        countDownLatch.await();
    }

    private static class InnerClass {
        private static ThreadLocal<StringBuilder> strTL = new ThreadLocal<StringBuilder>() {
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };

        /**
         * @param newStr
         */
        public void add(String newStr) {
            StringBuilder str = strTL.get();
            strTL.set(str.append(newStr));
        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    strTL.hashCode(),
                    strTL.get().hashCode(),
                    strTL.get().toString());
        }

        public void set(String words) {
            strTL.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    strTL.hashCode(),
                    strTL.get().hashCode(),
                    strTL.get().toString());
        }
    }

//    /**
//     * String Builder 计数
//     */
//    private static class Counter {
//        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
//            protected StringBuilder initialValue() {
//                return new StringBuilder();
//            }
//        };
//    }

}
