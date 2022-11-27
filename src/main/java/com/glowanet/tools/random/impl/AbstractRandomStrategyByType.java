package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.ICommonStrategy;
import com.glowanet.tools.random.IRandomStrategy;
import com.glowanet.tools.random.IRandomStrategyByType;
import com.glowanet.tools.random.exception.RandomUnsupportedException;
import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base clazz for random values identified by its type.
 */
public abstract class AbstractRandomStrategyByType extends AbstractRandomStrategy<Object> implements IRandomStrategyByType {

    private static final Logger LOGGER = LogManager.getLogger();

    protected AbstractRandomStrategyByType() {
        super(null);
    }

    @Override
    public boolean isSupported(Class<?> valueClazz) {
        if (valueClazz == null) {
            return false;
        }
        return supportedTypes().contains(valueClazz);
    }

    @Override
    public <V> V next(Class<?> valueClazz) {
        Object result = null;
        if (isSupported(valueClazz)) {
            result = valueByStaticDefinition(valueClazz);
            if (result == null) {
                result = loopThruProvider(valueClazz);
            }
        }
        return (V) result;
    }

    /**
     * @param valueClazz the type of the random value
     *
     * @return a random value of any type
     */
    protected Object loopThruProvider(Class<?> valueClazz) {
        Object result = null;
        for (Class<?> providerClazz : getProviders()) {
            result = nextValueFromProvider(providerClazz, valueClazz);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * @param providerClazz the clazz of the random provider
     * @param valueClazz    the type of the random value
     *
     * @return a random value of any type
     */
    @SuppressWarnings({"java:S2629", "java:S126"})
    protected Object nextValueFromProvider(Class<?> providerClazz, Class<?> valueClazz) {
        Object result = null;
        ICommonStrategy provider = ReflectionHelper.newInstance(providerClazz);
        if (provider == null) {
            LOGGER.warn(String.format(PROVIDER_IS_INVALID, providerClazz));
        } else {
            if (IRandomStrategyByType.class.isAssignableFrom(providerClazz)) {
                IRandomStrategyByType typedProvider = (IRandomStrategyByType) provider;
                result = typedProvider.next(valueClazz);
            } else if (AbstractRandomStrategy.class.isAssignableFrom(providerClazz)) {
                AbstractRandomStrategy<?> typedProvider = (AbstractRandomStrategy<?>) provider;
                if (typedProvider.isSupported(valueClazz)) {
                    result = typedProvider.next();
                }
            }
        }
        return result;
    }

    @Override
    protected Object valueByStaticDefinition() {
        throw new RandomUnsupportedException(String.format(IRandomStrategy.RANGE_IS_NOT_SUPPORTED, getClass()));
    }

    /**
     * @param valueClazz the type of the random value
     *
     * @return a random value of any type
     */
    protected Object valueByStaticDefinition(Class<?> valueClazz) {
        return null;
    }
}
