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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the {@linkplain Builder} pattern to support Bean
 * Validation as part of the construction of an Object during its building
 * process. In order for this to work all invariants must be defined in the
 * Object {@code T} being built by this interface.
 *
 * @param <T> The type of object being built by this {@linkplain Builder}
 * @param <K> The type of this {@linkplain Builder} used by subclasses
 *           to support method chaining that must implement this interface.
 *
 * @author Angel L. Villalain
 * @since 1.0.0
 * @version 1.0.0
 */
public abstract class ValidationAwareBuilder<T,K extends Builder>
        implements Builder<T,K> {

    private static Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();

    /**
     * Method to be implemented by underlying subclasses to build its
     * respective specific object being built.
     *
     * @return the object being built by this {@linkplain ValidationAwareBuilder}.
     */
    protected abstract T buildInternal();

    /**
     * @see org.kusaidia.util.builder.Builder#build()
     * @throws ConstraintViolationException if the object
     * built invariants does not pass the required checks.
     */
    @Override
    public final T build() throws ConstraintViolationException{
        T object = this.buildInternal();
        Set<ConstraintViolation<T>> violations =  validator.validate(object);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(new
                    HashSet<ConstraintViolation<?>>(violations));
        }
        return object;
    }
}
