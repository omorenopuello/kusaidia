package org.kusaidia.util.logging;

import java.lang.annotation.*;

/**
 * Annotation used by the underlying log aspect. This aspect abstract the
 * concern of logging from the system thus reducing scattering and tangling.
 *
 * @author Angel L. Villalain Garcia
 * @version 1.0.0
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Loggable {
    /**
     * Specifies the default log level message used by the underlying logging
     * system in place. It defaults to {@linkplain LogLevel#INFO}
     *
     * @return the configured log level used by the underlying logging system in
     *         place
     */
    LogLevel value() default LogLevel.INFO;

    /**
     * Specifies the default message used for printing purposes. This message
     * can be also a key used by the underlying i18n infrastructure in place in
     * order to provide localized error messages. It defaults to an empty
     * string.
     *
     * @return the default message used for printing purposes
     */
    String message() default "";
}
