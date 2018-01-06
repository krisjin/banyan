package org.concurrent.task.async;


public interface TaskPromise extends TaskFuture {

    /**
     * 获取和 Promise 关联的 Future
     *
     * @return
     */
    TaskFuture getFuture();

    /**
     * 标记任务成功完成, 如果任务已经完成，则可能抛出 {@code IllegalStateException}
     *
     * @param result 任务结果
     * @return
     */
    TaskPromise setSuccess(Object result);

    /**
     * 尝试标记任务成功完成
     *
     * @param result 任务结果
     * @return
     */
    boolean trySuccess(Object result);

    /**
     * 标记任务完成失败, 如果任务已经完成，则可能抛出 {@link IllegalStateException}
     *
     * @param cause 失败原因
     * @return
     */
    TaskPromise setFailure(Throwable cause);

    /**
     * 尝试标记任务完成失败, 如果任务已经完成，则可能抛出 {@link IllegalStateException}
     *
     * @param cause 失败原因
     * @return
     */
    boolean tryFailure(Throwable cause);
}
