package io.ayte.utility.task.kit;

import io.ayte.utility.task.api.Task;
import io.ayte.utility.task.kit.sync.EmptyTask;
import io.ayte.utility.task.kit.sync.RunnableTask;
import io.ayte.utility.task.kit.sync.SingleExecutionTask;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tasks {
    public static <E extends Throwable> Task<E> runnable(Runnable source) {
        return RunnableTask.create(source);
    }

    public static <E extends Throwable> Task<E> empty() {
        return EmptyTask.create();
    }

    /**
     * Wraps task in a protective barrier that allows task to be
     * invoked only once.
     *
     * <p>
     * Task class itself doesn't restrict number of invocations, but
     * implies that task should be invoked only once. This method
     * allows to ensure that task would only be called once.
     * </p>
     *
     * <p>
     * Passing such a wrapper <b>does not</b> add additional wrapper.
     * </p>
     *
     * @param <E> Task exception type.
     * @param task Task to wrap.
     * @return Task that is guaranteed to be executed only once.
     */
    public static <E extends Throwable> Task<E> once(Task<E> task) {
        return SingleExecutionTask.create(task);
    }

    /**
     * Allows safe type coercion, casting task to a task with a broader
     * exception type.
     *
     * @param task Task to cast.
     * @param <E> Target exception type.
     * @return Same task but of type with target exception.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Throwable> Task<E> downcast(Task<? extends E> task) {
        return (Task<E>) task;
    }
}
