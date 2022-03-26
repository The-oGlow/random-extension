package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.ILegacyValue;
import com.glowanet.tools.random.IRandomValueByType;
import com.glowanet.tools.random.legacy.AbstractLegacyStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class AbstractRandomStrategyByType<T> extends AbstractRandomStrategy<T> implements IRandomValueByType<T> {
    /**
     * @param typeOfT the type of the random value
     */
    protected AbstractRandomStrategyByType(Class<T> typeOfT) {
        super(typeOfT);
    }

    @Override
    public T next() {
        throw new UnsupportedOperationException(METHOD_IS_NOT_SUPPORTED);
    }

    @Override
    public Class<T> getTypeOfT() {
        throw new UnsupportedOperationException(METHOD_IS_NOT_SUPPORTED);
    }

    @Deprecated(since = "0.2.0", forRemoval = true)
    @Override
    public Object randomValue(Class<?> valueClazz) {
        return next(valueClazz);
    }

    @Override
    public Object next(Class<?> valueClazz) {
        Object result = valueFromProvider(valueClazz);
        if (result == null) {
            result = loopThruProvider(valueClazz);
        }
        return result;
    }

    /**
     * @param valueClazz the type of the random value
     *
     * @return a random value of any type
     */
    protected abstract Object valueFromProvider(Class<?> valueClazz);

    /**
     * @return the list of random provider
     */
    protected abstract List<Class<? extends AbstractLegacyStrategy>> getProviders();

    /**
     * @param valueClazz the type of the random value
     *
     * @return a random value of any type
     */
    protected Object loopThruProvider(Class<?> valueClazz) {
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
    private Object valueFromProviderLoop(Class<?> providerClazz, Class<?> valueClazz) {
        Object result = null;
        try {
            ILegacyValue provider = (ILegacyValue) providerClazz.getDeclaredConstructor((Class<?>[]) null).newInstance((Object[]) null);
            if (provider.isSupported(valueClazz)) {
                result = provider.next(valueClazz);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            //ignore
        }
        return result;
    }
}
