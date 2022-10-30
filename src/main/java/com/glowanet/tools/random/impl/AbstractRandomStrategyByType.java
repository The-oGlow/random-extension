package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.IRandomStrategy;
import com.glowanet.tools.random.IRandomStrategyByType;
import com.glowanet.tools.random.exception.RandomUnsupportedException;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.List;

/**
 * Base class for random values identified by its type.
 */
public abstract class AbstractRandomStrategyByType extends AbstractRandomStrategy<Object> implements IRandomStrategyByType {

    private static final Logger LOGGER = LogManager.getLogger();

    protected AbstractRandomStrategyByType() {
        super(null);
    }

    @Override
    public <T> T next(Class<?> valueClazz) {
        Object result = valueByStaticDefinition(valueClazz);
        if (result == null) {
            result = loopThruProvider(valueClazz);
        }
        if (valueClazz != null && result != null && valueClazz.isAssignableFrom(result.getClass())) {
            //noinspection unchecked
            return (T) result;
        } else {
            throw new RandomUnsupportedException(String.format(RANDOM_VALUE_FOR_CLAZZ_IS_NOT_GENERATED, valueClazz));
        }
    }

    /**
     * @throws RandomUnsupportedException this method must not be used
     */
    @Override
    public Class<Object> getTypeOfT() {
        throw new RandomUnsupportedException(String.format(IRandomStrategy.METHOD_IS_NOT_SUPPORTED, "getTypeOfT()"));
    }

    @Override
    public boolean isSupported(Class<?> type) {
        if (type == null) {
            return false;
        }
        return supportedTypes().stream().anyMatch(st -> st.equals(type));
    }

    @Override
    public List<Type> supportedTypes() {
        return List.of();
    }

    /**
     * @param valueClazz the type of the random value
     *
     * @return a random value of any type
     */
    protected Object valueByStaticDefinition(Class<?> valueClazz) {
        return null;
    }

    /**
     * @return the list of random provider
     */
    protected List<Class<? extends IRandomStrategy<?>>> getProviders() { //NOSONAR java:S1452
        return List.of();
    }

    /**
     * @return new random generator
     */
    @Override
    protected SecureRandom newRandom() {
        return new SecureRandom();
    }

    /**
     * @param valueClazz the type of the random value
     *
     * @return a random value of any type
     */
    protected Object loopThruProvider(Class<?> valueClazz) {
        Object result = null;
        for (Class<? extends IRandomStrategy<?>> providerClazz : getProviders()) {
            result = nextValueFromProvider(providerClazz, valueClazz);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * @param providerClazz the class of the random provider
     * @param valueClazz    the type of the random value
     *
     * @return a random value of any type
     */
    protected Object nextValueFromProvider(Class<? extends IRandomStrategy<?>> providerClazz, Class<?> valueClazz) {
        Object result = null;
        IRandomStrategy<Object> provider = null;
        try {
            //noinspection unchecked
            provider = (IRandomStrategy<Object>) ConstructorUtils.invokeConstructor(providerClazz, null, null);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.warn(String.format(PROVIDER_IS_INVALID, provider), e.getMessage());
        }
        if (provider != null && IRandomStrategyByType.class.isAssignableFrom(provider.getClass())) {
            IRandomStrategyByType typedProvider = (IRandomStrategyByType) provider;
            if (typedProvider.isSupported(valueClazz)) {
                result = typedProvider.next(valueClazz);
            }
        }
        return result;
    }
}
