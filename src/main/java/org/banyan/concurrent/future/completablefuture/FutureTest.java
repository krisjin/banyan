package org.banyan.concurrent.future.completablefuture;

import org.banyan.concurrent.future.completablefuture.model.MedalInfo;
import org.banyan.concurrent.future.completablefuture.model.UserInfo;
import org.banyan.concurrent.future.completablefuture.service.MedalService;
import org.banyan.concurrent.future.completablefuture.service.UserInfoService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();

        long userId = 111L;
        long startTime = System.currentTimeMillis();

        FutureTask<UserInfo> userInfoFutureTask = new FutureTask<>(() -> userInfoService.getUserInfo(userId));
        executorService.submit(userInfoFutureTask);

        Thread.sleep(300);

        FutureTask<MedalInfo> medalInfoFutureTask = new FutureTask<>(() -> medalService.getMedalInfo(userId));
        executorService.submit(medalInfoFutureTask);

        //Future对于结果的获取，不是很友好，只能通过阻塞或者轮询的方式得到任务的结果。Future.get() 就是阻塞调用，在线程获取结果之前get方法会一直阻塞。Future提供了一个isDone方法，可以在程序中轮询这个方法查询执行结果。'
        //阻塞的方式和异步编程的设计理念相违背，而轮询的方式会耗费无谓的CPU资源。因此，JDK8设计出CompletableFuture。CompletableFuture提供了一种观察者模式类似的机制，可以让任务执行完成后通知监听的一方。
        UserInfo userInfo = userInfoFutureTask.get();
        MedalInfo medalInfo = medalInfoFutureTask.get();

        System.err.println(userInfo.getName() + "-" + medalInfo.getName());

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }


}
