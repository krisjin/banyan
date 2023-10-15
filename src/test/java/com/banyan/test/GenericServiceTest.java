package com.banyan.test;

import org.banyan.concurrent.base.GenericService;
import org.banyan.concurrent.domain.Message;
import org.junit.Test;

/**
 * @author kris
 * @date 2022/10/4
 */
public class GenericServiceTest {

    @Test
    public void test1() {
        GenericService genericService = new GenericService();
        try {
            Message inst = genericService.getObject(Message.class);
            inst.setMsg("hello world!!");
            System.out.println(inst.getMsg());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
