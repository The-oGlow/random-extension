package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.IRandomStrategy;
import com.glowanet.tools.random.IRandomStrategyByType;
import com.glowanet.tools.random.exception.RandomUnsupportedException;

import java.security.SecureRandom;
import java.util.List;

/**
 * Base clazz for all random value types.
 *
 * @param <V> the type of the random value
 */
public abstract class AbstractRandomStrategy<V> implements IRandomStrategy<V> {

    private final Class<V> typeOfT;

    /**
     * @param typeOfT the type of the random value
     */
    protected AbstractRandomStrategy(Class<V> typeOfT) {
        this.typeOfT = typeOfT;
    }

    @Override
    public boolean isSupported(Class<?> valueClazz) {
        if (valueClazz == null) {
            return false;
        }
        return supportedTypes().contains(valueClazz);
    }

    /**
     * {@inheritDoc}
     * <p>
     * NOTE: MUST be overridden in the inherited concrete clazz!
     */
    @Override
    public V next() {
        throw new RandomUnsupportedException(String.format(IRandomStrategy.RANGE_IS_NOT_SUPPORTED, getClass()));
    }

    /**
     * {@inheritDoc}
     * <p>
     * NOTE: MAY be overridden in the inherited concrete clazz!
     */
    @Override
    public V next(V rangeStart, V rangeEnd) {
        throw new RandomUnsupportedException(String.format(IRandomStrategy.RANGE_IS_NOT_SUPPORTED, getClass()));
    }

    /**
     * {@inheritDoc}
     * <p>
     * NOTE: MAY be overridden in the inherited concrete clazz!
     */
    @Override
    public List<Class<?>> supportedTypes() {
        return List.of(typeOfT);
    }

    /**
     * NOTE: The order of the providers is important!<p>
     * Required Order:
     * <ol>
     *     <li>All providers implementing {@link IRandomStrategy}.</li>
     *     <li>All providers implementing {@link IRandomStrategyByType}.</li>
     * </ol>
     *
     * @return the list of random provider
     * <p>
     * NOTE: MAY be overridden in the inherited concrete clazz!
     */
    protected List<Class<?>> getProviders() {
        return List.of(getClass());
    }

    /**
     * @return new random generator
     */
    protected SecureRandom newRandom() {
        return new SecureRandom();
    }

    /**
     * @return a random value of any type
     * <p>
     * NOTE: MAY be overridden in the inherited concrete clazz!
     */
    protected V valueByStaticDefinition() {
        return null;
    }
}
