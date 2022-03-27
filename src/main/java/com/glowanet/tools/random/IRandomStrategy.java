package com.glowanet.tools.random;

/**
 * Interface for all random provider.
 *
 * @param <T> the type of the random value
 */
public interface IRandomStrategy<T> extends ICommonStrategy {

    /**
     * @return a random value of type {@code T}
     */
    T next();

    /**
     * @param rangeStart the minimum value of the range
     * @param rangeEnd   the maximum value of the range
     *
     * @return a random value of type {@code T} within {@code rangeStart} and {@code rangeEnd}, including boundaries
     *
     * @throws UnsupportedOperationException a random value within a range is not supported
     */
    T next(T rangeStart, T rangeEnd) throws UnsupportedOperationException;
}
