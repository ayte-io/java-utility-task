package io.ayte.utility.task.kit.async.sync;

import io.ayte.utility.task.api.AsyncTask;
import io.ayte.utility.task.api.Task;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.ExecutionException;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SynchronousAsyncTaskWrapper implements Task<Exception> {
    private final AsyncTask task;

    @Override
    public void execute() throws InterruptedException, ExecutionException {
        task.execute().get();
    }

    public static Task<Exception> create(@NonNull AsyncTask task) {
        return new SynchronousAsyncTaskWrapper(task);
    }
}
