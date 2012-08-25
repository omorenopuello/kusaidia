package org.kusaidia.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * Implementation of the {@linkplain Log} interface that use the SLF4J logging
 * infrastructure.
 *
 * @author Angel L. Villalain Garcia
 * @version 1.0.0
 * @since 1.0.0
 */
@Component("logger")
public final class SLF4JLog implements Log {

    /**
     * @see Log#isLogLevelEnabled(LogLevel, Class)
     */
    public boolean isLogLevelEnabled(LogLevel level, Class<?> clazz) {
        boolean result = false;
        switch (level) {
            case DEBUG:
                result = this.getLogger(clazz).isDebugEnabled();
                break;
            case ERROR:
                result = this.getLogger(clazz).isErrorEnabled();
                break;
            case INFO:
                result = this.getLogger(clazz).isInfoEnabled();
                break;
            case TRACE:
                result = this.getLogger(clazz).isTraceEnabled();
                break;
            case WARN:
                result = this.getLogger(clazz).isWarnEnabled();
                break;
        }
        return result;
    }

    /**
     * @see Log#log(LogLevel, Class, Throwable, String)
     */
    public void log(LogLevel level, Class<?> clazz, Throwable throwable,
                    String message) {
        switch (level) {
            case DEBUG:
                this.debug(clazz, throwable, message);
                break;
            case ERROR:
                this.error(clazz, throwable, message);
                break;
            case INFO:
                this.info(clazz, throwable, message);
                break;
            case TRACE:
                this.trace(clazz, throwable, message);
                break;
            case WARN:
                this.warn(clazz, throwable, message);
                break;
        }
    }

    /**
     * Private method to log debug messages.
     *
     * @param clazz the {@linkplain Class} that fired the log event.
     * @param throwable a {@linkplain Throwable} event if any occurred during
     * the execution of the method being advised.
     * @param message the message to print to the log.
     */
    private void debug(Class<?> clazz, Throwable throwable, String message) {
        if (throwable != null) {
            this.getLogger(clazz).debug(message, throwable);
        } else {
            this.getLogger(clazz).debug(message);
        }
    }

    /**
     * Private method to log debug messages.
     *
     * @param clazz the {@linkplain Class} that fired the log event.
     * @param throwable a {@linkplain Throwable} event if any occurred during
     * the execution of the method being advised.
     * @param message the message to print to the log.
     */
    private void error(Class<?> clazz, Throwable throwable, String message) {
        if (throwable != null) {
            this.getLogger(clazz).error(message, throwable);
        } else {
            this.getLogger(clazz).error(message);
        }
    }

    /**
     * Private method to log info messages.
     *
     * @param clazz the {@linkplain Class} that fired the log event.
     * @param throwable a {@linkplain Throwable} event if any occurred during
     * the execution of the method being advised.
     * @param message the message to print to the log.
     */
    private void info(Class<?> clazz, Throwable throwable, String message) {
        if (throwable != null) {
            this.getLogger(clazz).info(message, throwable);
        } else {
            this.getLogger(clazz).info(message);
        }
    }

    /**
     * Private method to log warn messages.
     *
     * @param clazz the {@linkplain Class} that fired the log event.
     * @param throwable a {@linkplain Throwable} event if any occurred during
     * the execution of the method being advised.
     * @param message the message to print to the log.
     */
    private void warn(Class<?> clazz, Throwable throwable, String message) {
        if (throwable != null) {
            this.getLogger(clazz).warn(message, throwable);
        } else {
            this.getLogger(clazz).warn(message);
        }
    }

    /**
     * Private method to log trace messages.
     *
     * @param clazz the {@linkplain Class} that fired the log event.
     * @param throwable a {@linkplain Throwable} event if any occurred during
     * the execution of the method being advised.
     * @param message the message to print to the log.
     */
    private void trace(Class<?> clazz, Throwable throwable, String message) {
        if (throwable != null) {
            this.getLogger(clazz).trace(message, throwable);
        } else {
            this.getLogger(clazz).trace(message);
        }
    }

    /**
     * Private helper method to format a message being logged.
     *
     * @param pattern the pattern of the message.
     * @param arguments the arguments to format.
     * @return a {@linkplain String} representing the message being logged.
     */
    private String format(String pattern, Object... arguments) {
        return MessageFormat.format(pattern, arguments);
    }

    /**
     * Private helper method to obtain the {@linkplain org.slf4j.Logger} for the specified
     * {@linkplain Class}.
     *
     * @param clazz the {@linkplain Class} requesting a {@linkplain org.slf4j.Logger}.
     * @return the {@linkplain org.slf4j.Logger} for the specified {@linkplain Class}.
     */
    private Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}

