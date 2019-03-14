package org.banyan.concurrent.model;

/**
 * <p/>
 * User : krisjin
 * Date: 2015/7/15
 * Time: 9:08
 */
public class UserBean {

    private String name;

    private String email;

    private int age;

    private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public int hashCode() {

        return name.hashCode() + ((Integer) age).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        UserBean userBean = null;
        if (obj instanceof UserBean) {
            userBean = (UserBean) obj;
        }

        return (this.age == userBean.getAge() && this.name.equals(userBean.getName()));
    }
}
