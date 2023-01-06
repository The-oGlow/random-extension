package com.glowanet.tools.random.impl;

import org.apache.commons.collections4.ListUtils;

import java.util.List;

/**
 * This is the "fallback" clazz, trying to create a random value with some legacy features.
 */
public class RandomStrategyObject extends AbstractRandomStrategyByType {

    public static final List<Class<?>> SUPP_TYPES =
            ListUtils.union(
                    RandomStrategyPrimitive.SUPP_TYPES,
                    ListUtils.union(
                            RandomStrategyDateTime.SUPP_TYPES,
                            RandomStrategyBigDecimal.SUPP_TYPES
                    )
            );

    @Override
    protected List<Class<?>> getProviders() {
        // NOTE: The order is important!
        return List.of(
                RandomStrategyBigDecimal.class,
                RandomStrategyDateTime.class,
                RandomStrategyPrimitive.class
        );
    }

    @Override
    public List<Class<?>> supportedTypes() {
        return SUPP_TYPES;
    }
}
