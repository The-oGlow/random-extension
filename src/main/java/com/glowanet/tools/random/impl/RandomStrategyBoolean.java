package com.glowanet.tools.random.impl;

import java.util.List;

/**
 * Specific Implementation for {@code Boolean}.
 */
public class RandomStrategyBoolean extends AbstractRandomStrategy<Boolean> {

    protected static final List<Class<?>> SUPP_TYPES = List.of(
            Boolean.class, boolean.class
    );

    public RandomStrategyBoolean() {
        super(Boolean.class);
    }

    @Override
    public Boolean next() {
        return newRandom().nextBoolean();
    }
}
