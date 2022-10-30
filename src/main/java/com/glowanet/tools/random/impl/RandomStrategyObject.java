package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.IRandomStrategy;

import java.util.List;

/**
 * This is the "fallback" class, trying to create a random value with some legacy features.
 */
public class RandomStrategyObject extends AbstractRandomStrategyByType {

    @Override
    protected List<Class<? extends IRandomStrategy<?>>> getProviders() {
        // NOTE: The order is important!
        return List.of(
                RandomStrategyPrimitive.class,
                RandomStrategyDateTime.class,
                RandomStrategyBigDecimal.class
        );
    }
}
