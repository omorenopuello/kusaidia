package org.kusaidia.util.logging;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Interface definition for all Loggable used by the Loggable aspect. This
 * interface should be implemented accordingly based on the logging
 * infrastructure in place.
 *
 * @author Angel L. Villalain Garcia
 * @version 1.0.0
 * @since 1.0.0
 */
@Validated
public interface Log {

    /**
     * Method that verifies if the given log event is enabled for the specified
     * {@linkplain Class} clazz and the specified {@linkplain LogLevel} level.
     *
     * @param level the specified {@linkplain LogLevel} for the log event being
     * fired.
     * @param clazz the {@linkplain Class} that fired the log event.
     * @return {@code true} if the specified level is enabled for the specified
     *         class, {@code false} otherwise.
     * @throws org.hibernate.validator.method.MethodConstraintViolationException
     * if
     * <ul>
     *     <li>{@code level} is {@code null}.</li>
     *     <li>{@code clazz} is {@code null}.</li>
     * </ul>
     *
     */
    boolean isLogLevelEnabled(@NotNull LogLevel level,@NotNull Class<?> clazz);

    /**
     * Method that logs the specified message, alongside the specified
     * {@linkplain Throwable} event if any, and the specified arguments.
     *
     * @param level the specified {@linkplain LogLevel} for the log event being
     * fired.
     * @param clazz the {@linkplain Class} that fired the log event.
     * @param throwable a {@linkplain Throwable} event if any occurred during
     * the execution of the method being advised.
     * @param message the message to print to the log.
     * @throws org.hibernate.validator.method.MethodConstraintViolationException
     * if
     * <ul>
     *     <li>{@code level} is {@code null}.</li>
     *     <li>{@code clazz} is {@code null}.</li>
     *     <li>{@code message} is {@code null} or empty {@linkplain
     *     String}</li>
     * </ul>
     */
    void log(@NotNull LogLevel level, @NotNull Class<?> clazz,
             Throwable throwable, @NotEmpty String message);
}
