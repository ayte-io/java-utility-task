package io.ayte.utility.task.kit.async;

import io.ayte.utility.task.api.AsyncTask;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.CompletableFuture;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmptyAsyncTask implements AsyncTask {
    private static final EmptyAsyncTask INSTANCE = new EmptyAsyncTask();

    @Override
    public CompletableFuture<Void> execute() {
        return CompletableFuture.completedFuture(null);
    }

    public static EmptyAsyncTask create() {
        return INSTANCE;
    }
}
