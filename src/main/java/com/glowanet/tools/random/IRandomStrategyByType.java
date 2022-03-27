package com.glowanet.tools.random;

/**
 * Interface for all random provider.
 */
public interface IRandomStrategyByType<T> extends IRandomStrategy<T> {

    /**
     * @param valueClazz the class to create the random value from
     *
     * @return a random value of any type
     */
    Object next(Class<T> valueClazz);
}
