package com.glowanet.tools.random;

import com.glowanet.tools.random.exception.RandomUnsupportedException;
import com.glowanet.tools.random.impl.RandomStrategyObject;

/**
 * <p>
 * Factory to manage the different provider to create random values.
 * <br>
 * In case there is no special provider, a generic provider tries to create a random value.
 * </p>
 * <p>
 * <b>Simple usage</b>
 * <br>
 * {@code
 * RandomValueFactory factory = RandomValueFactory.getInstance();
 * String random = factory.getProvider(String.class);
 * System.out.println(random.next());
 * }
 * </p>
 *
 * @see #getProvider(Class)
 */
public final class RandomValueFactory extends AbstractRandomValueFactory {

    private static final String             DEFAULT_PREFIX_PACKAGE = RandomValueFactory.class.getPackageName() + ".impl";
    private static final String             DEFAULT_PREFIX_CLASS   = "RandomStrategy";
    private static final RandomValueFactory instance;

    private boolean fallback = true;

    static {
        instance = new RandomValueFactory();
    }

    private RandomValueFactory() {
        super();
    }

    /**
     * @return the factory
     */
    public static synchronized RandomValueFactory getInstance() {
        return instance;
    }

    /**
     * @param fallback TRUE = With legacy mode, else FALSE (default=TRUE)
     */
    public void setFallback(boolean fallback) {
        this.fallback = fallback;
    }

    /**
     * @return TRUE = With legacy mode, else FALSE
     */
    public boolean isFallback() {
        return fallback;
    }

    @Override
    protected String getProviderLocation() {
        return DEFAULT_PREFIX_PACKAGE + "." + DEFAULT_PREFIX_CLASS;
    }

    @Override
    public IRandomStrategy<?> getProvider(Class<?> valueClazz) {
        if (valueClazz != null) {
            ICommonStrategy providerInstance = generateRandomProvider(valueClazz);
            if (fallback && (providerInstance == null)) {
                providerInstance = getFallbackProvider();
            }
            return (IRandomStrategy<?>) providerInstance;
        } else {
            throw new RandomUnsupportedException(NULL_NOT_SUPPORTED);
        }
    }

    /**
     * @return a random provider, using legacy methods
     */
    private RandomStrategyObject getFallbackProvider() {
        return (RandomStrategyObject) generateRandomProvider(Object.class);
    }

}
