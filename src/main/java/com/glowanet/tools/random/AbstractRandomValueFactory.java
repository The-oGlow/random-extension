package com.glowanet.tools.random;

import org.apache.commons.lang3.ClassUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

/**
 * Base class for all random factories.
 */
public abstract class AbstractRandomValueFactory {

    protected static final Logger LOGGER = LogManager.getLogger(); //NOSONAR java:S1312

    private boolean silent = true;

    protected AbstractRandomValueFactory() {
        //nothing2do
    }

    /**
     * @param silent TRUE = Don't annoy with logger output, else FALSE (default=TRUE)
     */
    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    protected abstract String getProviderLocation();

    public abstract ICommonStrategy getProvider(Class<?> valueClazz);

    /**
     * @param providerLocation the package name of the provider
     * @param valueClazzName   the class name of the provider
     *
     * @return the found class
     */
    protected Class<?> findProviderClazz(String providerLocation, String valueClazzName) {
        Class<?> clazzType = null;
        String providerClazzName = providerLocation + valueClazzName;
        try {
            clazzType = ClassUtils.getClass(providerClazzName);
        } catch (ClassNotFoundException e) {
            if (!silent) {
                LOGGER.error("Can't find '{}' : {}", providerClazzName, e);
            }
        }
        return clazzType;
    }

    /**
     * @param valueClazz           the class of the random value
     * @param valueClazzParameters array of classes for the constructor parameters
     *
     * @return a provider instance
     */
    protected ICommonStrategy generateRandomProvider(Class<?> valueClazz, Class<?>... valueClazzParameters) {
        ICommonStrategy providerInstance = null;
        if (valueClazz != null) {
            Class<?> providerClazz = findProviderClazz(getProviderLocation(), valueClazz.getSimpleName());
            providerInstance = instantiateProvider(providerClazz, valueClazz, valueClazzParameters);
        }
        return providerInstance;
    }

    /**
     * @param providerClazz        the class of the random provider
     * @param valueClazz           the class of the random value
     * @param valueClazzParameters array of classes for the constructor parameters
     *
     * @return a new provider instance
     */
    protected ICommonStrategy instantiateProvider(Class<?> providerClazz, Class<?> valueClazz, Class<?>... valueClazzParameters) {
        ICommonStrategy providerInstance = null;
        if (providerClazz != null && ClassUtils.isAssignable(providerClazz, ICommonStrategy.class)) {
            try {
                if (valueClazzParameters.length > 0) {
                    providerInstance = (ICommonStrategy) providerClazz.getDeclaredConstructor(valueClazzParameters).newInstance(valueClazz);
                } else {
                    providerInstance = (ICommonStrategy) providerClazz.getDeclaredConstructor().newInstance();
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IllegalArgumentException e) {
                if (!silent) {
                    LOGGER.error("Can't create instance '{}' : {}", providerClazz, e);
                }
            }
        }
        return providerInstance;
    }
}
