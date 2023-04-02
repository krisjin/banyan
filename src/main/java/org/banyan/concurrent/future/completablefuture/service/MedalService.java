package org.banyan.concurrent.future.completablefuture.service;

import org.banyan.concurrent.future.completablefuture.model.MedalInfo;

public class MedalService {

    public MedalInfo getMedalInfo(long userId) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new MedalInfo("111", "会员");
    }
}
