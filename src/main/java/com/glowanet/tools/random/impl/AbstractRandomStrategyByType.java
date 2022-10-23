package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.ILegacyStrategy;
import com.glowanet.tools.random.IRandomStrategy;
import com.glowanet.tools.random.IRandomStrategyByType;
import com.glowanet.tools.random.exception.RandomUnsupportedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.List;

public abstract class AbstractRandomStrategyByType extends AbstractRandomStrategy<Object> implements IRandomStrategyByType {
    protected AbstractRandomStrategyByType() {
        super(null);
    }

    @Override
    public Object next(Class<Object> valueClazz) {
        Object result = valueByStaticDefinition(valueClazz);
        if (result == null) {
            result = loopThruProvider(valueClazz);
        }
        return result;
    }

    @Override
    public Class<Object> getTypeOfT() {
        throw new RandomUnsupportedException(IRandomStrategy.METHOD_IS_NOT_SUPPORTED);
    }

    @Override
    public boolean isSupported(Class<?> type) {
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
    protected Object valueByStaticDefinition(Class<Object> valueClazz) {
        return null;
    }

    /**
     * @return the list of random provider
     */
    protected List<Class<? extends AbstractRandomStrategy<?>>> getProviders() {
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
    protected Object loopThruProvider(Class<Object> valueClazz) {
        Object result = null;
        for (Class<?> providerClazz : getProviders()) {
            result = valueFromProviderLoop(providerClazz, valueClazz);
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
    private Object valueFromProviderLoop(Class<?> providerClazz, Class<Object> valueClazz) {
        Object result = null;
        try {
            ILegacyStrategy provider = (ILegacyStrategy) providerClazz.getDeclaredConstructor((Class<?>[]) null).newInstance((Object[]) null);
            if (provider.isSupported(valueClazz)) {
                result = provider.next(valueClazz);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) { //NOSONAR java:S1166
            //ignore
        }
        return result;
    }
}
