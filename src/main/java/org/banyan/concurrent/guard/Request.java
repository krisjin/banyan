package org.banyan.concurrent.guard;

import org.banyan.concurrent.future.Data;

/**
 * 请求对象
 * User krisjin
 * Date 2017/5/13
 */
public class Request {
    protected String name;
    private Data response;

    public Request(String name) {
        this.name = name;
    }

    public synchronized Data getResponse() {
        return response;
    }

    public synchronized void setResponse(Data response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request Name is :" + this.getName();
    }
}
