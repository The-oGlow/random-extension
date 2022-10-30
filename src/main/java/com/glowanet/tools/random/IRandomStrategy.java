package com.glowanet.tools.random;

import com.glowanet.tools.random.exception.RandomException;

/**
 * Interface for all random provider.
 *
 * @param <T> the type of the random value
 */
public interface IRandomStrategy<T> extends ICommonStrategy {

    String RANGE_IS_NOT_SUPPORTED  = "Random value within a range is not supported : %s!";
    String METHOD_IS_NOT_SUPPORTED = "Method is not supported : %s!";
    String PROVIDER_IS_INVALID     = "Provider is invalid : %s!";

    /**
     * @return the type of the random value
     */
    Class<T> getTypeOfT();

    /**
     * @return a random value of type {@code T}
     *
     * @see #next(Object, Object)
     */
    T next();

    /**
     * @param rangeStart the minimum value of the range
     * @param rangeEnd   the maximum value of the range
     *
     * @return a random value of type {@code T} within {@code rangeStart} and {@code rangeEnd}, including boundaries
     *
     * @throws RandomException a random value within a range is not supported
     * @see #next()
     */
    T next(T rangeStart, T rangeEnd) throws RandomException;
}
