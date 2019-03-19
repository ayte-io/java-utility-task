package io.ayte.utility.task.kit;

import io.ayte.utility.task.api.AsyncTask;
import io.ayte.utility.task.api.Task;
import io.ayte.utility.task.kit.async.EmptyAsyncTask;
import io.ayte.utility.task.kit.async.adapter.ExecutorBackedTaskAdapter;
import io.ayte.utility.task.kit.async.adapter.SynchronousTaskAdapter;
import io.ayte.utility.task.kit.async.sync.SynchronousAsyncTaskCollectionWrapper;
import io.ayte.utility.task.kit.async.sync.SynchronousAsyncTaskWrapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AsyncTasks {
    /**
     * @return Task that pretends to do something, but completes
     * instantly without doing any work.
     */
    public static AsyncTask empty() {
        return EmptyAsyncTask.create();
    }

    /**
     * Creates new pseudo async task which will be executed in
     * synchronous (blocking) manner, but pretend to be asynchronous.
     *
     * @param task Task to wrap
     * @return Pseudo async task.
     */
    public static AsyncTask synchronous(Task<? extends Exception> task) {
        return SynchronousTaskAdapter.create(task);
    }

    /**
     * Creates new wrapper that will execute task inside
     * {@link ForkJoinPool#commonPool()}.
     *
     * Be careful using this, because this method uses not so
     * controllable thread pool shared by many standard JVM classes.
     *
     * @param task Task to wrap
     * @return Wrapped task that will be executed in common thread pool.
     */
    public static AsyncTask fromTaskAndCommonPool(Task task) {
        return fromTaskAndExecutor(task, ForkJoinPool.commonPool());
    }

    /**
     * Creates task wrapper that executes task in provided executor when
     * launched.
     *
     * @param task Task to wrap.
     * @param executor Executor task will be launched in.
     * @return Wrapped task
     */
    public static AsyncTask fromTaskAndExecutor(Task task, Executor executor) {
        return ExecutorBackedTaskAdapter.create(task, executor);
    }

    public static AsyncTask create(Task task, Executor executor) {
        return fromTaskAndExecutor(task, executor);
    }

    /**
     * Creates synchronous task from asynchronous one.
     *
     * @param task Task to convert.
     * @return Synchronous wrapper.
     */
    public static Task<Exception> toTask(AsyncTask task) {
        return SynchronousAsyncTaskWrapper.create(task);
    }

    public static Task<Exception> toTask(AsyncTask... tasks) {
        return toTask(Arrays.asList(tasks));
    }

    /**
     * Converts several asynchronous tasks into synchronous one.
     *
     * <p>
     * Resulting task will trigger all delegates simultaneously and wait
     * till completion.
     * </p>
     *
     * <p>
     * Clients should not rely on return type, since it can be different
     * for different input sizes.
     * </p>
     *
     * @param tasks Tasks to be converted
     * @return Task that will trigger all tasks simulte
     */
    public static Task<Exception> toTask(Collection<AsyncTask> tasks) {
        return SynchronousAsyncTaskCollectionWrapper.create(tasks);
    }
}
