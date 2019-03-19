package io.ayte.utility.task.kit.async.sync;

import io.ayte.utility.task.api.AsyncTask;
import io.ayte.utility.task.api.Task;
import io.ayte.utility.task.kit.sync.EmptyTask;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SynchronousAsyncTaskCollectionWrapper implements Task<Exception> {
    private final Collection<? extends AsyncTask> tasks;

    @Override
    public void execute() throws InterruptedException, ExecutionException {
        val futures = tasks.stream()
                .map(AsyncTask::execute)
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).get();
    }

    public static Task<Exception> create(@NonNull Collection<? extends AsyncTask> tasks) {
        switch (tasks.size()) {
            case 0:
                return EmptyTask.create();
            case 1:
                return SynchronousAsyncTaskWrapper.create(tasks.iterator().next());
            default:
                return new SynchronousAsyncTaskCollectionWrapper(tasks);
        }
    }
}
