package org.banyan.concurrent.lock;

/**
 * User:krisjin
 * Date:2020-05-08
 */
public class LazyInitSingleton {
    private volatile static LazyInitSingleton instance = null;

    private LazyInitSingleton() {
    }

//    public static synchronized LazyInitSingleton getInstance() {
//        if (instance == null) {
//            instance = new LazyInitSingleton();
//        }
//        return instance;
//    }


    public static LazyInitSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyInitSingleton.class) {
                if (instance == null) {
                    instance = new LazyInitSingleton();
                }
            }
        }
        return instance;
    }


}
