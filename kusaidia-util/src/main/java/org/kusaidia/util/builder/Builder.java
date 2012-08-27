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
