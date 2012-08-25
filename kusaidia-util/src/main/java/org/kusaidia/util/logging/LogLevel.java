package org.kusaidia.util.logging;

/**
 * Enum representing the different type of Logging level events for the
 * underlying java logging infrastructure. Used along with the specific
 * {@linkplain Loggable} annotation to configure
 * the level of logging event that will be fired once the aspect is bound.
 *
 * @author Angel L. Villalain Garcia
 * @version 1.0.0
 * @since 1.0.0
 */
public enum LogLevel {
    /**
     * Debug level messages.
     */
    DEBUG,
    /**
     * Error level messages.
     */
    ERROR,
    /**
     * Info level messages.
     */
    INFO,
    /**
     * Trace level messages.
     */
    TRACE,
    /**
     * Warn level messages.
     */
    WARN
}
