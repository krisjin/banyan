package org.concurrent.lock.reentrant;

/**
 * @author shijingui on 2019/3/2
 */
public class Demo1Main {


    /**
     * 在当前执行的任务，每个任务现场都是顺序执行的
     * 共享对象，不同的线程调用各自的方法
     * 一个线程获取lock之后，其它的线程只能等待，直到锁被释放
     *
     * @param args
     */
    public static void main(String[] args) {
        Demo1Service service = new Demo1Service();
        TaskService taskService = new TaskService(service);
        Thread thread1 = new Thread(taskService);
        Thread thread2 = new Thread(taskService);
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.methodB();
            }
        });
        thread1.start();
        thread3.start();
        thread2.start();
    }


    protected static class TaskService implements Runnable {

        private Demo1Service demo1Service;

        public TaskService(Demo1Service demo1Service) {
            this.demo1Service = demo1Service;
        }

        @Override
        public void run() {
            demo1Service.methodA();
        }
    }
}
