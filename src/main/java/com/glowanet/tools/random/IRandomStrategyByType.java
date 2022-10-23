package com.glowanet.tools.random;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Interface for all random provider.
 */
public interface IRandomStrategyByType extends IRandomStrategy<Object> {

    /**
     * @param valueClazz the class to create the random value from
     *
     * @return a random value of any type
     */
    Object next(Class<Object> valueClazz);

    boolean isSupported(Class<?> type);

    List<Type> supportedTypes();
}
