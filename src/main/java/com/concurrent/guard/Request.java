package com.concurrent.guard;

/**
 * 请求对象
 * User shijingui
 * Date 2017/5/13
 */
public class Request {
    protected String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request Name is :" + this.getName();
    }
}
