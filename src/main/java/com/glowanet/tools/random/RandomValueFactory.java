package com.glowanet.tools.random;

import com.glowanet.tools.random.impl.RandomStrategyObject;
import org.apache.commons.lang3.ClassUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * Factory to manage the different provider to create random values.
 * <br>
 * In case there is no special provider, a generic legacy provider tries to create a random value.
 * If you don't want this, set {@code setAutoLegacy} = FALSE;
 * </p>
 * <p>Simple usage</p>
 * <blockquote>{@code
 * RandomValueFactory factory = RandomValueFactory.getInstance();
 * String random = factory.createRandomValue(String.class);
 * System.out.println(random);
 * }</blockquote>
 * <p>Usage without legacy mode</p>
 * <blockquote>{@code
 * RandomValueFactory factory = RandomValueFactory.getInstance();
 * factory.setAutoLegacy(false);
 * String random = factory.createRandomValue(String.class);
 * System.out.println(random);
 * }</blockquote>
 *
 * @see #createRandomValue(Class)
 * @see #setAutoLegacy(boolean)
 */
public final class RandomValueFactory {

    public static final String DEFAULT_PREFIX_PACKAGE = RandomValueFactory.class.getPackageName() + ".impl";
    public static final String DEFAULT_PREFIX_CLASS   = "RandomStrategy";
    public static final String DEFAULT_LOCATION       = DEFAULT_PREFIX_PACKAGE + "." + DEFAULT_PREFIX_CLASS;

    private static final Logger             LOGGER = LogManager.getLogger();
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
        //nothing2do
    }

    /**
     * @return the factory
     */
    public static synchronized RandomValueFactory getInstance() {
        return instance;
    }

    /**
     * @param silent TRUE = Don't annoy with logger output, else FALSE (default=TRUE)
     */
    public static synchronized void setSilent(boolean silent) {
        RandomValueFactory.silent = silent;
    }

    /**
     * @param autoLegacy TRUE = With legacy mode, else FALSE (default=TRUE)
     */
    public static synchronized void setAutoLegacy(boolean autoLegacy) {
        RandomValueFactory.autoLegacy = autoLegacy;
    }

    /**
     * @param valueClazz the class of the random value
     * @param <T>        the type of the random value
     *
     * @return a random value of type {@code T}
     *
     * @see #setAutoLegacy(boolean)
     */
    public static <T> IRandomValue<T> createRandomValue(Class<?> valueClazz) {
        IRandomValue<T> newRandomValue = generateRandomValue(valueClazz, new Class[0]);
        if (autoLegacy && (newRandomValue == null)) {
            newRandomValue = generateLegacyRandomValue();
        }
        return newRandomValue;
    }

    /**
     * @param <T> the type of the random value
     *
     * @return a random value, by legacy methods, of type {@code T}
     */
    private static <T> RandomStrategyObject<T> generateLegacyRandomValue() {
        return (RandomStrategyObject<T>) generateRandomValue(Object.class, Class.class);
    }

    /**
     * @param valueClazz           the class of the random value
     * @param valueClazzParameters array of classes for the constructor parameters
     * @param <T>                  the type of the random value
     *
     * @return a random value of type {@code T}
     */
    protected static <T> IRandomValue<T> generateRandomValue(Class<?> valueClazz, Class<?>... valueClazzParameters) {
        IRandomValue<T> newRandomValue = null;
        if (valueClazz != null) {
            String nameRandomValue = DEFAULT_LOCATION + valueClazz.getSimpleName();
            Class<?> randomClazz = findRandomProvider(nameRandomValue);
            newRandomValue = evaluateRandomProviderResult(randomClazz, valueClazz, valueClazzParameters);
        }
        return newRandomValue;
    }

    /**
     * @param providerClazzName the full class name of the random provider
     *
     * @return the found random provider
     */
    protected static Class<?> findRandomProvider(String providerClazzName) {
        Class<?> clazzType = null;
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
     * @param providerClazz        the class of the random provider
     * @param valueClazz           the class of the random value
     * @param valueClazzParameters array of classes for the constructor parameters
     * @param <T>                  the type of the random value
     *
     * @return a random value of type {@code T}
     */
    @SuppressWarnings("unchecked")
    protected static <T> IRandomValue<T> evaluateRandomProviderResult(Class<?> providerClazz, Class<?> valueClazz, Class<?>... valueClazzParameters) {
        IRandomValue<T> newRandomValue = null;
        if (providerClazz != null && ClassUtils.isAssignable(providerClazz, IRandomValue.class)) {
            try {
                if (valueClazzParameters.length > 0) {
                    newRandomValue = (IRandomValue<T>) providerClazz.getDeclaredConstructor(valueClazzParameters).newInstance(valueClazz);
                } else {
                    newRandomValue = (IRandomValue<T>) providerClazz.getDeclaredConstructor().newInstance();
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IllegalArgumentException e) {
                if (!silent) {
                    LOGGER.error("Can't create instance '{}' : {}", providerClazz, e);
                }
            }
        }
        return newRandomValue;
    }
}
