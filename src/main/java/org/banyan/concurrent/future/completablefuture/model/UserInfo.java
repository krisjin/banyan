package org.banyan.concurrent.future.completablefuture.model;

/**
 * @author kris
 * @date 2023/4/2
 */
public class UserInfo {
    private String id;
    private String name;
    private int age;

    public UserInfo(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
}
