package io.ayte.utility.task.kit.sync;

import io.ayte.utility.task.api.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Just a dummy placeholder.
 */
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmptyTask implements Task<RuntimeException> {
    private static final EmptyTask INSTANCE = new EmptyTask();

    @SuppressWarnings("squid:S1186")
    @Override
    public void execute() {}

    @SuppressWarnings("unchecked")
    public static <E extends Throwable> Task<E> create() {
        return (Task<E>) INSTANCE;
    }
}
