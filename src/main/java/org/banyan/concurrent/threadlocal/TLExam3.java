package org.banyan.concurrent.threadlocal;

/**
 * @author krisjin
 * @date 2020/11/3
 */
public class TLExam3 {
    private static ThreadLocal<String> tl = new ThreadLocal<>();

    public void setVal(String name) {
        tl.set(name + ">" + Thread.currentThread().getName());
    }

    public String get1() {
        return tl.get();
    }

    public String get2() {
        return tl.get();
    }

    public String get3() {
        return tl.get();
    }


    public static void main(String[] args) {
        TLExam3 tlExam3 = new TLExam3();
        tlExam3.setVal("kris");
        System.err.println(tlExam3.get1());
        System.err.println(tlExam3.get2());
        System.err.println(tlExam3.get3());


        //创建子线程

        new Thread(new ChildThread(tlExam3)).start();
    }


    static class ChildThread implements Runnable {
        private TLExam3 tlExam3;

        public ChildThread(TLExam3 tlExam3) {
            this.tlExam3 = tlExam3;
        }

        @Override
        public void run() {
            tlExam3.setVal("chris");
            System.err.println(tlExam3.get1());
            System.err.println(tlExam3.get2());
            tlExam3.setVal("chris2");
            System.err.println(tlExam3.get3());

        }


    }

}
