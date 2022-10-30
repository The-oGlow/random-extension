package com.glowanet.tools.random;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Interface for all random provider, based on the class type.
 */
public interface IRandomStrategyByType extends IRandomStrategy<Object> {

    String RANDOM_VALUE_FOR_CLAZZ_IS_NOT_GENERATED = "Random value is not generated for class : %s!";

    /**
     * @param valueClazz the class to create the random value from
     *
     * @return a random value of any type
     */
    <T> T next(Class<?> valueClazz);

    /**
     * @param type A class
     *
     * @return true=the {@code type} is supported, else false
     *
     * @see #supportedTypes()
     */
    boolean isSupported(Class<?> type);

    /**
     * @return list of supported types
     *
     * @see #isSupported(Class)
     */
    List<Type> supportedTypes();
}
