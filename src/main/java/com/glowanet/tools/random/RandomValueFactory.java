package com.glowanet.tools.random;

import com.glowanet.tools.random.impl.RandomValueObject;
import org.apache.commons.lang3.ClassUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public final class RandomValueFactory {

    private static final Logger             LOGGER         = LogManager.getLogger();
    private static final String             PACKAGE_PREFIX = "com.glowanet.tools.random.impl.RandomValue";
    private static final RandomValueFactory instance;

    private static boolean silent;
    private static boolean autoLegacy;

    static {
        //noinspection InstantiationOfUtilityClass
        instance = new RandomValueFactory();
        silent = true;
        autoLegacy = true;
    }

    private RandomValueFactory() {
    }

    public static synchronized RandomValueFactory getInstance() {
        return instance;
    }

    public static synchronized void setSilent(boolean silent) {
        RandomValueFactory.silent = silent;
    }

    public static synchronized void setAutoLegacy(boolean autoLegacy) {
        RandomValueFactory.autoLegacy = autoLegacy;
    }

    public static <T> IRandomValue<T> createRandomValue(Class<?> valueClazz) {

        IRandomValue<T> newRandomValue = createRandomValue(valueClazz, new Class[0]);
        if (autoLegacy && (newRandomValue == null)) {
            newRandomValue = RandomValueFactory.createLegacyRandomValue();
        }
        return newRandomValue;
    }

    private static <V> RandomValueObject<V> createLegacyRandomValue() {
        return (RandomValueObject<V>) createRandomValue(Object.class, Class.class);
    }

    protected static Class<?> findRandomClazz(String clazzName) {
        Class<?> clazzType = null;
        try {
            clazzType = ClassUtils.getClass(clazzName);
        } catch (ClassNotFoundException e) {
            if (!silent) {
                LOGGER.error("Can't find '{}' : {}", clazzName, e);
            }
        }
        return clazzType;
    }

    protected static <T> IRandomValue<T> createRandomValue(Class<?> valueClazz, Class<?>... valueParameters) {
        IRandomValue<T> newRandomValue = null;
        if (valueClazz != null) {
            String nameRandomValue = PACKAGE_PREFIX + valueClazz.getSimpleName();
            Class<?> randomClazz = findRandomClazz(nameRandomValue);
            newRandomValue = evaluateResult(randomClazz, valueClazz, valueParameters);
        }
        return newRandomValue;
    }


    @SuppressWarnings("unchecked")
    protected static <T> IRandomValue<T> evaluateResult(Class<?> randomClazz, Class<?> valueClazz, Class<?>... valueParameters) {
        IRandomValue<T> newRandomValue = null;
        if (randomClazz != null && ClassUtils.isAssignable(randomClazz, IRandomValue.class)) {
            try {
                if (valueParameters.length > 0) {
                    newRandomValue = (IRandomValue<T>) randomClazz.getDeclaredConstructor(valueParameters).newInstance(valueClazz);
                } else {
                    newRandomValue = (IRandomValue<T>) randomClazz.getDeclaredConstructor().newInstance();
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IllegalArgumentException e) {
                if (!silent) {
                    LOGGER.error("Can't create instance '{}' : {}", randomClazz, e);
                }
            }
        }
        return newRandomValue;
    }
}
