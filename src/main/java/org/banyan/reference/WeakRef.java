package org.banyan.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author krisjin on 2018/1/6
 */
public class WeakRef {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        ReferenceQueue weakQueue = new ReferenceQueue<MyObject>();
        WeakReference<MyObject> weakReference = new WeakReference<MyObject>(myObject, weakQueue);
        myObject = null;
        System.gc();
        System.out.println(weakReference.get());

    }

    static class MyObject {
        @Override
        public String toString() {
            return "Weak ref";
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("called...");
        }
    }
}
