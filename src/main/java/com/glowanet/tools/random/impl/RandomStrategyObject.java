package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.legacy.AbstractLegacyStrategy;
import com.glowanet.tools.random.legacy.LegacyStrategyBigDecimal;
import com.glowanet.tools.random.legacy.LegacyStrategyDateTime;
import com.glowanet.tools.random.legacy.LegacyStrategyPrimitive;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * This is the "fallback" class, trying to create a random value with some legacy features.
 *
 * @param <T> the type of the random value
 */
public class RandomStrategyObject<T> extends AbstractRandomStrategyByType<T> {

    /**
     * Standard length of alphanumeric values.
     */
    public static final int DEFAULT_ALPHA_LENGTH = 5;

    public RandomStrategyObject() {
        super(null);
    }

    @Override
    protected List<Class<? extends AbstractLegacyStrategy>> getProviders() {
        return List.of(
                LegacyStrategyBigDecimal.class, LegacyStrategyPrimitive.class, LegacyStrategyDateTime.class
        );
    }

    @Override
    protected Object valueByStaticDefinition(Class<T> valueClazz) {
        Object result = null;

        if (Boolean.class.equals(valueClazz)) {
            result = RandomUtils.nextBoolean();
        } else if (Number.class.equals(valueClazz)) {
            result = RandomUtils.nextInt();
        } else if (String.class.equals(valueClazz)) {
            result = RandomStringUtils.randomAlphanumeric(DEFAULT_ALPHA_LENGTH);
        }
        return result;
    }
}