package org.banyan.concurrent.reference;

import java.lang.ref.WeakReference;

/**
 *
 * 弱引用对象的存在不会阻止它所指向的对象被垃圾回收器回收。弱引用最常见的用途是实现规范映射(canonicalizing mappings，比如哈希表）。
 * 假设垃圾收集器在某个时间点决定一个对象是弱可达的(weakly reachable)（也就是说当前指向它的全都是弱引用），这时垃圾收集器会清除所有指向该对象的弱引用，
 * 然后垃圾收集器会把这个弱可达对象标记为可终结(finalizable)的，这样它们随后就会被回收。与此同时或稍后，
 * 垃圾收集器会把那些刚清除的弱引用放入创建弱引用对象时所登记到的引用队列(Reference Queue)中。
 * @author krisjin
 * @date 2020/11/2
 */
public class WeakReferenceExam {
    //execute gc , weak reference is collection
    static WeakReference<String> name = new WeakReference(new String("kris"));

    public static void main(String[] args) {
        System.gc();
        if (name.get() != null) {
            System.err.println("没回收");
        } else {
            System.out.println("已回收");
        }
    }

}
