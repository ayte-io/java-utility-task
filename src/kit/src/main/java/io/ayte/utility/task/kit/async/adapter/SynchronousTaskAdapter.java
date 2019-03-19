package io.ayte.utility.task.kit.async.adapter;

import io.ayte.utility.task.api.AsyncTask;
import io.ayte.utility.task.api.Task;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.concurrent.CompletableFuture;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SynchronousTaskAdapter implements AsyncTask {
    private final Task delegate;

    @Override
    @SuppressWarnings("squid:S1181")
    public CompletableFuture<Void> execute() {
        val future = new CompletableFuture<Void>();
        try {
            delegate.execute();
        } catch (Throwable e) {
            future.completeExceptionally(e);
            return future;
        }
        future.complete(null);
        return future;
    }

    public static SynchronousTaskAdapter create(@NonNull Task task) {
        return new SynchronousTaskAdapter(task);
    }
}
