package io.ayte.utility.task.kit.sync;

import io.ayte.utility.task.api.Task;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Wraps another task, guaranteeing single execution.
 *
 * @param <E> Exception type.
 */
@ToString
@RequiredArgsConstructor
public class SingleExecutionTask<E extends Throwable> implements Task<E> {
    private final AtomicBoolean launched = new AtomicBoolean(false);

    private final Task<E> delegate;

    @Override
    public void execute() throws E {
        if (!launched.compareAndSet(false, true)) {
            throw new IllegalStateException("This task has already been run");
        }
        delegate.execute();
    }

    public static <E extends Throwable> Task<E> create(@NonNull Task<E> delegate) {
        if (delegate instanceof SingleExecutionTask || delegate instanceof EmptyTask) {
            return delegate;
        }
        return new SingleExecutionTask<>(delegate);
    }
}
