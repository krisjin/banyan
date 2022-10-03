package org.banyan.concurrent.base;

import org.banyan.concurrent.domain.Message;
import org.banyan.concurrent.domain.UserService;

/**
 * User: krisjin
 * Date: 2017/1/3
 */
public class GenericService {
    public static void main(String[] args) {
        GenericService genericService = new GenericService();
        try {
            Message m = genericService.getObject(Message.class);
            UserService userService = genericService.getObject(UserService.class);

            String u = userService.getUserInfo();
            m.setMsg("test service...");
            System.out.println(m.getMsg());
            System.out.println(u);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 泛型方法
     *
     * @param c   用来创建泛型对象
     * @param <T> 声明一个泛型T
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {
        //创建泛型对象
        T t = c.newInstance();
        return t;
    }
}
