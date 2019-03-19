package io.ayte.utility.task.api;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface AsyncTask {
    CompletableFuture<Void> execute();
}
