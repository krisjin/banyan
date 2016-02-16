package com.concurrent.task;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class DefaultTaskPromise implements TaskPromise {

    @SuppressWarnings("rawtypes")
    private static final AtomicReferenceFieldUpdater<DefaultTaskPromise, ConcurrentMap> updater = AtomicReferenceFieldUpdater
            .newUpdater(DefaultTaskPromise.class, ConcurrentMap.class, "attrs");

    private static final ResultHolder VOID_SUCCESS = new Success(null);

    private volatile CountDownLatch complteLatch;

    private volatile ResultHolder result;

    private List<TaskCallback> callbacks;

    private volatile ConcurrentMap<String, Object> attrs;

    private static interface ResultHolder {
        boolean isSuccess();

        boolean isFailure();

        Object getResult();
    }

    private static final class Success implements ResultHolder {

        final Object value;

        Success(Object value) {
            this.value = value;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public Object getResult() {
            return value;
        }
    }

    private static final class Failure implements ResultHolder {

        Throwable t;

        Failure(Throwable t) {
            this.t = t;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public Throwable getResult() {
            return t;
        }
    }

    @Override
    public boolean isSuccess() {
        ResultHolder result = this.result;
        return result != null && result.isSuccess();
    }

    @Override
    public Throwable cause() {
        ResultHolder result = this.result;
        if (result != null && result.isFailure()) {
            return ((Failure) result).getResult();
        }
        return null;
    }

    @Override
    public Object getNow() {
        ResultHolder result = this.result;
        if (result != null && result.isSuccess()) {
            return result.getResult();
        }
        return null;
    }

    @Override
    public TaskFuture await() throws InterruptedException {
        if (isDone()) {
            return this;
        }

        synchronized (this) {
            if (isDone()) {
                return this;
            }

            if (this.complteLatch == null) {
                this.complteLatch = new CountDownLatch(1);
            }
        }
        this.complteLatch.await();
        return this;
    }

    @Override
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        if (isDone()) {
            return true;
        }

        synchronized (this) {
            if (isDone()) {
                return true;
            }
            if (this.complteLatch == null) {
                this.complteLatch = new CountDownLatch(1);
            }
        }
        return this.complteLatch.await(timeout, unit);
    }

    @Override
    public TaskFuture onComplete(final TaskCallback callback) {
        if (isDone()) {
            return callback.apply(this);
        }
        synchronized (this) {
            if (isDone()) {
                return callback.apply(this);
            }
            if (this.callbacks == null) {
                this.callbacks = new LinkedList<>();
            }
            this.callbacks.add(callback);
        }
        return this;
    }

    @Override
    public TaskFuture onSuccess(final TaskCallback callback) {
        return onComplete(new TaskCallback() {
            @Override
            public TaskFuture apply(final TaskFuture f) {
                if (f.isSuccess()) {
                    return callback.apply(f);
                }
                return f;
            }
        });
    }

    @Override
    public TaskFuture onFailure(final TaskCallback callback) {
        return onComplete(new TaskCallback() {
            @Override
            public TaskFuture apply(final TaskFuture f) {
                if (!f.isSuccess()) {
                    return callback.apply(f);
                }
                return f;
            }
        });
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return this.result != null;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        await();
        if (!isSuccess()) {
            throw new ExecutionException(((Failure) result).getResult());
        }
        return getNow();
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!await(timeout, unit)) {
            throw new TimeoutException("Futures timed out after [" + unit.toMillis(timeout) + "ms]");
        }
        if (isDone() && !isSuccess()) {
            throw new ExecutionException(((Failure) result).getResult());
        }
        return getNow();
    }

    @Override
    public TaskFuture getFuture() {
        return this;
    }

    @Override
    public TaskPromise setSuccess(Object value) {
        if (!trySuccess(value)) {
            throw new IllegalStateException("Already complted future: " + this);
        }
        return this;
    }

    @Override
    public boolean trySuccess(Object value) {
        ResultHolder holder = value == null ? VOID_SUCCESS : new Success(value);
        if (setComplete(holder)) {
            executeCallbacks();
            return true;
        }
        return false;
    }

    @Override
    public TaskPromise setFailure(Throwable cause) {
        if (!tryFailure(cause)) {
            throw new IllegalStateException("Already complted future: " + this);
        }
        return this;
    }

    @Override
    public boolean tryFailure(Throwable cause) {
        if (setComplete(new Failure(cause))) {
            executeCallbacks();
            return true;
        }
        return false;
    }

    private boolean setComplete(ResultHolder holder) {
        if (isDone()) {
            return false;
        }
        synchronized (this) {
            if (isDone()) {
                return false;
            }

            this.result = holder;
            if (this.complteLatch != null) {
                this.complteLatch.countDown();
            }
        }
        return true;
    }

    private void executeCallbacks() {
        if (this.callbacks != null) {
            Iterator<TaskCallback> callbackIter = this.callbacks.iterator();
            TaskFuture f = this;
            while (callbackIter.hasNext()) {
                f = callbackIter.next().apply(f);
                callbackIter.remove();
            }
        }
    }

    @Override
    public boolean hasAttr(String key) {
        ConcurrentMap<String, Object> attributes = this.attrs;
        if (attributes == null || key == null) {
            return false;
        }
        return attributes.containsKey(key);
    }

    @Override
    public Object getAttr(String key) {
        ConcurrentMap<String, Object> attributes = this.attrs;
        if (attributes == null || key == null) {
            return null;
        }
        return attributes.get(key);
    }

    @Override
    public TaskFuture addAttr(String key, Object value) {
        ConcurrentMap<String, Object> attributes = this.attrs;
        if (attributes == null) {
            attributes = new ConcurrentHashMap<>();
            if (!updater.compareAndSet(this, null, attributes)) {
                attributes = this.attrs;
            }
        }

        if (value == null) {
            attributes.remove(key);
            return this;
        }
        attributes.put(key, value);
        return this;
    }

    @Override
    public Object removeAttr(String key) {
        ConcurrentMap<String, Object> attributes = this.attrs;
        if (attributes == null || key == null) {
            return null;
        }
        return attributes.remove(key);
    }
}
