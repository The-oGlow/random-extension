package com.glowanet.tools.random;

/**
 * @param <T> the type of the random value
 */
public interface IRandomValueByType<T> extends IRandomValue<T> {

    /**
     * @param valueClazz the class to create the random value from
     *
     * @return a random value of class {@code valueClazz}
     */
    Object next(Class<?> valueClazz);

    /**
     * @param valueClazz the class to create the random value from
     *
     * @return a random value of class {@code valueClazz}
     */
    @Deprecated(since = "0.2.0", forRemoval = true)
    Object randomValue(Class<?> valueClazz);
}
