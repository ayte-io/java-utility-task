package io.ayte.utility.task.api;

/**
 * Pretty much {@link Runnable} but with throw support. Acts same as
 * runnable in raw form.
 *
 * <p>
 * End users should treat tasks as something that can be called at most
 * once, even if implementation allows calling multiple times.
 * </p>
 *
 * @param <E> Task exception type.
 */
@FunctionalInterface
public interface Task<E extends Throwable> {
    @SuppressWarnings("squid:S00112")
    void execute() throws E;
}
