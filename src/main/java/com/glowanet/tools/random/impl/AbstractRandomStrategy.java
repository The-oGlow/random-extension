package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.IRandomStrategy;
import com.glowanet.tools.random.exception.RandomUnsupportedException;

import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;

/**
 * Base class for all random value types.
 *
 * @param <T> the type of the random value
 */
public abstract class AbstractRandomStrategy<T> implements IRandomStrategy<T> {

    private final Class<T> typeOfT;

    /**
     * @param typeOfT the type of the random value
     */
    protected AbstractRandomStrategy(Class<T> typeOfT) {
        this.typeOfT = typeOfT;
    }

    @Override
    public Class<T> getTypeOfT() {
        return typeOfT;
    }

    @Override
    public T next() {
        throw new RandomUnsupportedException(String.format(IRandomStrategy.RANGE_IS_NOT_SUPPORTED, getClass()));
    }

    @Override
    public T next(T rangeStart, T rangeEnd) {
        throw new RandomUnsupportedException(String.format(IRandomStrategy.RANGE_IS_NOT_SUPPORTED, getClass()));
    }

    /**
     * @return new random generator
     */
    protected SecureRandom newRandom() {
        return new SecureRandom();
    }

    /**
     * @param newObjectClazz class of {@code <N>}
     * @param <N>            type of the new instance
     *
     * @return a new instance of {@code newObjectClazz}
     */
    protected <N> N newInstance(Class<N> newObjectClazz) {
        return newInstance(newObjectClazz, null, null);
    }

    /**
     * @param newObjectClazz class of {@code N}
     * @param parameterTypes types of {@code initargs}
     * @param initargs       values for the new instance
     * @param <N>            type of the new instance
     *
     * @return a new instance of {@code newObjectClazz}
     */
    protected <N> N newInstance(Class<N> newObjectClazz, Class<?>[] parameterTypes, Object[] initargs) {
        try {
            return newObjectClazz.getConstructor(parameterTypes).newInstance(initargs);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) { //NOSONAR java:S1166
            return null;
        }
    }

}
