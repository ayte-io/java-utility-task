package io.ayte.utility.task.kit.sync;

import io.ayte.utility.task.api.Task;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RunnableTask<E extends Throwable> implements Task<E> {
    private final Runnable runnable;

    @Override
    public void execute() {
        runnable.run();
    }

    public static <E extends Throwable> RunnableTask<E> create(Runnable runnable) {
        return new RunnableTask<>(runnable);
    }
}
