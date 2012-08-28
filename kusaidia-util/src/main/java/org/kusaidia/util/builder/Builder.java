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

package org.kusaidia.util.builder;

/**
 * Abstract Class implementation of the Builder pattern as defined by Joshua
 * Bloch in his book Effective Java. This implementation is a plain
 * implementation of the pattern. Subclass of this interface such as
 * {@linkplain ValidationAwareBuilder} adds additional behavior to
 * this default implementation.
 * @param <T> The type of object being built by this {@linkplain Builder}
 * @param <K> The type of this {@linkplain Builder} used by subclasses
 *           to support method chaining.
 *
 * @author Angel L. Villalain
 * @since 1.0.0
 * @version 1.0.0
 */
public interface Builder<T, K extends Builder> {
    /**
     * Returns a newly created instance of the object this {@linkplain Builder} is
     * responsible for.
     *
     * @return a newly created instance of the object this {@linkplain Builder} is
     * responsible for.
     */
    public T build();
}
