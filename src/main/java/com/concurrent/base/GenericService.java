package com.concurrent.base;

/**
 * User: shijingui
 * Date: 2017/1/3
 */
public class GenericService {
    /**
     * 泛型方法
     * @param c 用来创建泛型对象
     * @param <T> 声明一个泛型T
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException{
        //创建泛型对象
        T t = c.newInstance();
        return t;
    }
}
