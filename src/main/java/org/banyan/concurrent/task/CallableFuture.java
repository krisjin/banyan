package org.banyan.concurrent.task;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用Callable 和 Future 返回线程执行结果
 */
public class CallableFuture {
    static ExecutorService executorService = Executors.newFixedThreadPool(20);
    static AtomicLong num = new AtomicLong(1);

    public static void main() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Task task = new Task("序表达式（Ranking Formula）允许用户为应用自定义搜索结果排序方式，通过在查询请求中指定表达式来对结果排序。排序表达式支持基本运算（算术运算、关系运算、逻辑运算、位运算、条件运算）、数学函数和排序特征（feature）等。Open Search对于几种经典的应用（如论坛、资讯等）提供了表达式模板，用户可根据自己数据的特点，选择合适的表达式模板，并以此为基础进行修改，生成自己的表达式。", num);

            Future<Long> future = executorService.submit(task);
        }

        System.out.println("####################################");
//        executorService.shutdown();
//        TimeUnit.MILLISECONDS.sleep(100);

//        try {
//            Integer result = future.get();
        System.out.println("task execute result is : ");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    static class Task implements Callable<Long> {
        String msg;
        AtomicLong num;

        public Task(String msg, AtomicLong num) {
            this.msg = msg;
            this.num = num;
        }

        @Override
        public Long call() {
            System.out.println("任务线程在进行计算" + num.incrementAndGet());

            return num.get();
        }
    }
}
