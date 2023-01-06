package com.glowanet.tools.random.impl;

import java.util.List;

/**
 * Specific Implementation for {@code Boolean}.
 */
public class RandomStrategyBoolean extends AbstractRandomStrategy<Boolean> {

    public static final List<Class<?>> SUPP_TYPES = List.of(
            Boolean.class, boolean.class
    );

    protected RandomStrategyBoolean() {
        super(Boolean.class);
    }

    @Override
    public Boolean next() {
        return newRandom().nextBoolean();
    }

    @Override
    public List<Class<?>> supportedTypes() {
        return SUPP_TYPES;
    }
}
