package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.ILegacyStrategy;
import com.glowanet.tools.random.IRandomStrategyByType;
import com.glowanet.tools.random.legacy.AbstractLegacyStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class AbstractRandomStrategyByType<T> extends AbstractRandomStrategy<T> implements IRandomStrategyByType<T> {
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

    @Override
    public Object next(Class<T> valueClazz) {
        Object result = valueByStaticDefinition(valueClazz);
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
    protected abstract Object valueByStaticDefinition(Class<T> valueClazz);

    /**
     * @return the list of random provider
     */
    protected abstract List<Class<? extends AbstractLegacyStrategy>> getProviders();

    /**
     * @param valueClazz the type of the random value
     *
     * @return a random value of any type
     */
    protected Object loopThruProvider(Class<T> valueClazz) {
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
    private Object valueFromProviderLoop(Class<?> providerClazz, Class<T> valueClazz) {
        Object result = null;
        try {
            ILegacyStrategy provider = (ILegacyStrategy) providerClazz.getDeclaredConstructor((Class<?>[]) null).newInstance((Object[]) null);
            if (provider.isSupported(valueClazz)) {
                result = provider.next(valueClazz);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            //ignore
        }
        return result;
    }
}
