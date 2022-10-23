package com.glowanet.tools.random;

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
 * String random = factory.getProvider(String.class);
 * System.out.println(random.next());
 * }</blockquote>
 * <p>Usage without legacy mode</p>
 * <blockquote>{@code
 * RandomValueFactory factory = RandomValueFactory.getInstance();
 * factory.setAutoLegacy(false);
 * String random = factory.getProvider(String.class);
 * System.out.println(random.next());
 * }</blockquote>
 *
 * @see #getProvider(Class)
 */
public final class RandomValueFactory extends AbstractRandomValueFactory {

    private static final String DEFAULT_PREFIX_PACKAGE = RandomValueFactory.class.getPackageName() + ".impl";
    private static final String DEFAULT_PREFIX_CLASS   = "RandomStrategy";

    private static final RandomValueFactory instance;

//    private boolean autoLegacy = true;

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
     * @param autoLegacy TRUE = With legacy mode, else FALSE (default=TRUE)
     */
//    public void setAutoLegacy(boolean autoLegacy) {
//        this.autoLegacy = autoLegacy;
//    }

    /**
     * @return TRUE = With legacy mode, else FALSE
     */
//    public boolean isAutoLegacy() {
//        return autoLegacy;
//    }
    @Override
    protected String getProviderLocation() {
        return DEFAULT_PREFIX_PACKAGE + "." + DEFAULT_PREFIX_CLASS;
    }

    @Override
    public IRandomStrategy<?> getProvider(Class<?> valueClazz) {
        ICommonStrategy providerInstance = generateRandomProvider(valueClazz);
//        if (autoLegacy && (providerInstance == null)) {
//            providerInstance = getLegacyProvider();
//        }
        return (IRandomStrategy<?>) providerInstance;
    }

//    /**
//     * @return a random provider, using legacy methods
//     */
//    private RandomStrategyObject getLegacyProvider() {
//        return (RandomStrategyObject) generateRandomProvider(Object.class);
//    }

}
