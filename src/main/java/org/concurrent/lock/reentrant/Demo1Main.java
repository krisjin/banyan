package org.concurrent.lock.reentrant;

/**
 * @author shijingui on 2019/3/2
 */
public class Demo1Main {


    public static void main(String[] args) {



    }


    protected static class Service implements Runnable {

        private Demo1 demo1;

        public Service(Demo1 demo1) {
            this.demo1 = demo1;
        }

        @Override
        public void run() {
            demo1.t();
        }
    }
}
