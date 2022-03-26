package com.glowanet.tools.random.impl;

import org.apache.commons.lang3.RandomUtils;

/**
 * Specific Implementation for {@code Boolean}.
 */
public class RandomStrategyBoolean extends AbstractRandomStrategy<Boolean> {

    public RandomStrategyBoolean() {
        super(Boolean.class);
    }

    @Override
    public Boolean next() {
        return RandomUtils.nextBoolean();
    }
}
