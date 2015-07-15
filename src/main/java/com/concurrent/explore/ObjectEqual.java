package com.concurrent.explore;

import com.concurrent.common.UserBean;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/7/15
 * Time: 9:04
 */
public class ObjectEqual {


    public static void main(String[] args) {

        UserBean userBean = new UserBean();
        UserBean userBean1 = new UserBean();

        userBean.setAge(12);
        userBean.setName("jingui");
        userBean.setEmail("krisibm@163.com");

        userBean1.setAge(12);
        userBean1.setName("jingui");
        userBean1.setEmail("krisibm@163.com");

        int code = userBean.hashCode();
        int code1 = userBean1.hashCode();

        System.out.println(code + "=" + code1);

        boolean equal = userBean.equals(userBean1);
        System.out.println(equal);
    }


}
