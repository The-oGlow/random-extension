package com.glowanet.tools.random;

/**
 * <p>
 * Factory to manage the different provider to create random values by legacy mode.
 * </p>
 * <p>Simple usage</p>
 * <blockquote>{@code
 * RandomValueFactory factory = RandomValueFactory.getInstance();
 * String random = factory.getProvider(String.class);
 * System.out.println(random.next());
 * }</blockquote>
 *
 * @see #getProvider(Class)
 * @deprecated
 */
@Deprecated
public class LegacyValueFactory extends AbstractRandomValueFactory {
    public static final String DEFAULT_PREFIX_PACKAGE = LegacyValueFactory.class.getPackageName() + ".legacy";
    public static final String DEFAULT_PREFIX_CLASS   = "LegacyStrategy";

    private static final LegacyValueFactory instance;

    static {
        instance = new LegacyValueFactory();
    }

    private LegacyValueFactory() {
        //nothing2do
    }

    /**
     * @return the factory
     */
    public static synchronized LegacyValueFactory getInstance() {
        return instance;
    }

    @Override
    protected String getProviderLocation() {
        return DEFAULT_PREFIX_PACKAGE + "." + DEFAULT_PREFIX_CLASS;
    }

    @Override
    public ILegacyStrategy getProvider(Class<?> valueClazz) {
        ILegacyStrategy providerInstance = (ILegacyStrategy) generateRandomProvider(valueClazz);
        if (providerInstance == null && valueClazz != null && !Object.class.equals(valueClazz)) {
            providerInstance = (ILegacyStrategy) generateRandomProvider(Primitive.class);
        }
        if (providerInstance != null && !providerInstance.isSupported(valueClazz)) {
            providerInstance = null;
        }
        return providerInstance;
    }
}
