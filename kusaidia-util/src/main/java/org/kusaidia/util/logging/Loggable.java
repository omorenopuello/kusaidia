/*
 * Kusaidia
 *
 * Copyright (c) 2012, third-party contributors as indicated by the
 * @author tags or express copyright attribution statements applied by the authors.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */

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
