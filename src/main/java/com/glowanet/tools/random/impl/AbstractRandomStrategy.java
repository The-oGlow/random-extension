package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.IRandomValue;

/**
 * Base class for all random value types.
 *
 * @param <T> the type of the random value
 */
public abstract class AbstractRandomStrategy<T> implements IRandomValue<T> {

    protected static final String RANGE_IS_NOT_SUPPORTED  = "Random value within a range is not supported!";
    public static final    String METHOD_IS_NOT_SUPPORTED = "Method is not supported!";

    private final Class<T> typeOfT;

    /**
     * @param typeOfT the type of the random value
     */
    protected AbstractRandomStrategy(Class<T> typeOfT) {
        this.typeOfT = typeOfT;
    }

    /**
     * @return the type of the random value
     */
    public Class<T> getTypeOfT() {
        return typeOfT;
    }

    @Override
    public T next(T rangeStart, T rangeEnd) {
        throw new UnsupportedOperationException(RANGE_IS_NOT_SUPPORTED);
    }

    @Deprecated(since = "0.2.0", forRemoval = true)
    @Override
    public final T randomValue() {
        return next();
    }

    @Deprecated(since = "0.2.0", forRemoval = true)
    @Override
    public final T randomValue(T rangeStart, T rangeEnd) {
        return next(rangeStart, rangeEnd);
    }
}
