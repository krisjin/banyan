package org.banyan.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author krisjin on 2018/1/6
 */
public class SoftRef {

    public static void main(String[] args) {
        MyObject myObject = new MyObject("soft.......................");
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<MyObject>();

        SoftReference<MyObject> softReference = new SoftReference<MyObject>(myObject, referenceQueue);
        myObject = null;
        System.gc();
        MyObject myObject1 = softReference.get();
        System.out.println(myObject1.name);

        byte[] bytes = new byte[1024 * 1314 * 2];
        System.out.println(softReference.get());
    }


    static class MyObject {
        private String name;
        private byte[] bytes = null;

        MyObject(String name) {
            this.name = name;
            bytes = new byte[1024 * 1024 * 4];
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("called...");
            super.finalize();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
