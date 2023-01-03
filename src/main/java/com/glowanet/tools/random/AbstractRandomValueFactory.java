package com.glowanet.tools.random;

import org.apache.commons.lang3.ClassUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

/**
 * Base clazz for all random factories.
 */
public abstract class AbstractRandomValueFactory {

    public static final String NULL_NOT_SUPPORTED = "Null is not supported as class!";

    private static final Logger LOGGER = LogManager.getLogger();

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

    /**
     * @return TRUE = Don't annoy with logger output, else FALSE
     */
    public boolean isSilent() {
        return silent;
    }

    /**
     * @return the clazzpath to the provider
     */
    protected abstract String getProviderLocation();

    /**
     * @param valueClazz the type of the random values
     *
     * @return the provider, which creates random values of {@code valueClazz}
     */
    public abstract ICommonStrategy getProvider(Class<?> valueClazz);

    /**
     * @param providerLocation the package name of the provider
     * @param valueClazzName   the clazz name of the provider
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
     * @param valueClazz           the clazz of the random value
     * @param valueClazzParameters array of clazzes for the constructor parameters
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
     * @param providerClazz        the clazz of the random provider
     * @param valueClazz           the clazz of the random value
     * @param valueClazzParameters array of clazzes for the constructor parameters
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
