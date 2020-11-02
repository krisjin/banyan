package org.banyan.concurrent.reference;

import java.lang.ref.WeakReference;

/**
 * @author krisjin
 * @date 2020/11/2
 */
public class WeakReferenceExam {
    //execute gc , weak reference is collection
    static WeakReference<String> name = new WeakReference(new String("krs"));

    public static void main(String[] args) {
        System.gc();
        if (name.get() != null) {
            System.err.println("没有回收");
        } else {
            System.out.println("已回收");
        }
    }

}
