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

    /**
     * @return the type of the random value
     */
    public Class<T> getTypeOfT() {
        return typeOfT;
    }

    @Override
    public T next() {
        throw new RandomUnsupportedException(IRandomStrategy.RANGE_IS_NOT_SUPPORTED);
    }

    @Override
    public T next(T rangeStart, T rangeEnd) {
        throw new RandomUnsupportedException(IRandomStrategy.RANGE_IS_NOT_SUPPORTED);
    }

    /**
     * @return new random generator
     */
    protected SecureRandom newRandom() {
        return new SecureRandom();
    }

    protected <N> N newInstance(Class<N> newObjectClazz) {
        return newInstance(newObjectClazz, (Class<?>[]) null, (Object[]) null);
    }

    protected <N> N newInstance(Class<N> newObjectClazz, Class<?>[] parameterTypes, Object[] initargs) {
        try {
            return newObjectClazz.getConstructor(parameterTypes).newInstance(initargs);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

}
