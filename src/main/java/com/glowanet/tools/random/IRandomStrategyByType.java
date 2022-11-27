package com.glowanet.tools.random;

import java.util.List;

/**
 * Interface for all random provider, based on the clazz type.
 */
public interface IRandomStrategyByType extends IRandomStrategy<Object> {

    /**
     * @param valueClazz A clazz
     *
     * @return true=the {@code valueClazz} is supported, else false
     *
     * @see #supportedTypes()
     */
    @Override
    boolean isSupported(Class<?> valueClazz);

    /**
     * @return list of supported types
     *
     * @see #isSupported(Class)
     */
    @Override
    List<Class<?>> supportedTypes();

    /**
     * @param valueClazz the clazz to create the random value from
     *
     * @return a random value of any type
     */
    <V> V next(Class<?> valueClazz);
}
