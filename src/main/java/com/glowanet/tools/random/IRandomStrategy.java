package com.glowanet.tools.random;

import com.glowanet.tools.random.exception.RandomException;

import java.util.List;

/**
 * Interface for all random provider.
 *
 * @param <V> the type of the random value
 */
public interface IRandomStrategy<V> extends ICommonStrategy {

    String RANGE_IS_NOT_SUPPORTED  = "Random value within a range is not supported : %s!";
    String METHOD_IS_NOT_SUPPORTED = "Method is not supported : %s!";
    String PROVIDER_IS_INVALID     = "Provider is invalid : %s!";

    /**
     * @param valueClazz A clazz
     *
     * @return true=the {@code valueClazz} is supported, else false
     *
     * @see #supportedTypes()
     */
    boolean isSupported(Class<?> valueClazz);

    /**
     * @return list of supported types
     *
     * @see #isSupported(Class)
     */
    List<Class<?>> supportedTypes();

    /**
     * @return a random value of type {@code V}
     *
     * @see #next(Object, Object)
     */
    V next();

    /**
     * @param rangeStart the minimum value of the range
     * @param rangeEnd   the maximum value of the range
     *
     * @return a random value of type {@code V} within {@code rangeStart} and {@code rangeEnd}, including boundaries
     *
     * @throws RandomException a random value within a range is not supported
     * @see #next()
     */
    V next(V rangeStart, V rangeEnd) throws RandomException;
}
