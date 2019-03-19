package io.ayte.utility.task.kit.async.adapter;

import io.ayte.utility.task.api.AsyncTask;
import io.ayte.utility.task.api.Task;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ExecutorBackedTaskAdapter implements AsyncTask {
    private final Task<?> task;
    private final Executor executor;

    @Override
    @SuppressWarnings("squid:S1181")
    public CompletableFuture<Void> execute() {
        val future = new CompletableFuture<Void>();
        executor.execute(() -> {
            try {
                task.execute();
                future.complete(null);
            } catch (Throwable e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    public static ExecutorBackedTaskAdapter create(@NonNull Task task, @NonNull Executor executor) {
        return new ExecutorBackedTaskAdapter(task, executor);
    }

    public static ExecutorBackedTaskAdapter usingCommonPool(@NonNull Task task) {
        return create(task, ForkJoinPool.commonPool());
    }
}
