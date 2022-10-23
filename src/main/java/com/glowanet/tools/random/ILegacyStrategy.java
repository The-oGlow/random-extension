package com.glowanet.tools.random;

/**
 * Interface for all legacy provider.
 *
 * @deprecated
 */
@Deprecated
public interface ILegacyStrategy extends ICommonStrategy {

    /**
     * @param type the class of the random value
     *
     * @return a random value of any type
     */
    Object next(Class<?> type);

    /**
     * @param type the class of the random value
     *
     * @return TRUE = if the {@code type} is supported, else FALSE
     */
    boolean isSupported(Class<?> type);
}
