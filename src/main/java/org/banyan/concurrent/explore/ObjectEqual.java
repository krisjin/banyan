package org.banyan.concurrent.explore;

import org.banyan.concurrent.model.UserBean;

/**
 * <p/>
 * User : krisjin
 * Date: 2015/7/15
 * Time: 9:04
 */
public class ObjectEqual {

    public static void main(String[] args) {

        UserBean userBean = new UserBean();
        UserBean userBean1 = new UserBean();

        userBean.setAge(12);
        userBean.setName("kris");
        userBean.setEmail("kris11");

        userBean1.setAge(12);
        userBean1.setName("kris");
        userBean1.setEmail("kris11");

        int code = userBean.hashCode();
        int code1 = userBean1.hashCode();

        System.out.println(code + "=" + code1);
        boolean equal = userBean.equals(userBean1);
        System.out.println(equal);
    }


}
