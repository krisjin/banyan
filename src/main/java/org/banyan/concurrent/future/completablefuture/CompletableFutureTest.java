package org.banyan.concurrent.future.completablefuture;

import org.banyan.concurrent.future.completablefuture.model.MedalInfo;
import org.banyan.concurrent.future.completablefuture.model.UserInfo;
import org.banyan.concurrent.future.completablefuture.service.MedalService;
import org.banyan.concurrent.future.completablefuture.service.UserInfoService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId = 666L;
        long startTime = System.currentTimeMillis();

        CompletableFuture<UserInfo> completableUserInfoFuture = CompletableFuture.supplyAsync(() -> userInfoService.getUserInfo(userId));

        Thread.sleep(300);

        CompletableFuture<MedalInfo> completableMedalInfoFuture = CompletableFuture.supplyAsync(() -> medalService.getMedalInfo(userId));

        UserInfo userInfo = completableUserInfoFuture.get(2, TimeUnit.SECONDS);
        MedalInfo medalInfo = completableMedalInfoFuture.get();
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

    }
}
