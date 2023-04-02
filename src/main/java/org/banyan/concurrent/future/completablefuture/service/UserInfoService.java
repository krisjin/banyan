package org.banyan.concurrent.future.completablefuture.service;

import org.banyan.concurrent.future.completablefuture.model.UserInfo;

public class UserInfoService {

    public UserInfo getUserInfo(Long userId)  {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new UserInfo("111", "星空", 27);
    }
}
