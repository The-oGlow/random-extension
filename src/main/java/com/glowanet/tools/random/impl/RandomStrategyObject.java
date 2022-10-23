package com.glowanet.tools.random.impl;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

/**
 * This is the "fallback" class, trying to create a random value with some legacy features.
 */
public class RandomStrategyObject extends AbstractRandomStrategyByType {

    /**
     * Standard length of alphanumeric values.
     */
    public static final int DEFAULT_ALPHA_LENGTH = 5;

    public RandomStrategyObject() {
        super();
    }

    @Override
    protected List<Class<? extends AbstractRandomStrategy<?>>> getProviders() {
        return List.of(
                RandomStrategyBigDecimal.class, RandomStrategyPrimitive.class, RandomStrategyDateTime.class
        );
    }

    @Override
    protected Object valueByStaticDefinition(Class<Object> valueClazz) {
        Object result;

        if (Boolean.class.equals(valueClazz)) {
            result = newRandom().nextBoolean();
        } else if (Number.class.equals(valueClazz)) {
            result = newRandom().nextInt();
        } else if (String.class.equals(valueClazz)) {
            result = RandomStringUtils.randomAlphanumeric(DEFAULT_ALPHA_LENGTH);
        } else {
            result = null;
        }
        return result;
    }
}
