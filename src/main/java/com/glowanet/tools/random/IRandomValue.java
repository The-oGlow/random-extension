package com.glowanet.tools.random;

/**
 * @param <T> the type of the random value
 */
public interface IRandomValue<T> {

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

    /**
     * @return a random value of type {@code T}
     *
     * @see #next()
     */
    @Deprecated(since = "0.2.0", forRemoval = true)
    T randomValue();

    /**
     * @param rangeStart the minimum value of the range
     * @param rangeEnd   the maximum value of the range
     *
     * @return a random value of type {@code T} within {@code rangeStart} and {@code rangeEnd}, including boundaries
     *
     * @throws UnsupportedOperationException a random value within a range is not supported
     * @see #next(Object, Object)
     */
    @Deprecated(since = "0.2.0", forRemoval = true)
    T randomValue(T rangeStart, T rangeEnd) throws UnsupportedOperationException;
}
