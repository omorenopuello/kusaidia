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
