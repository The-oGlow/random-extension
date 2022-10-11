package com.glowanet.tools.random.impl;

/**
 * Specific Implementation for {@code Boolean}.
 */
public class RandomStrategyBoolean extends AbstractRandomStrategy<Boolean> {

    public RandomStrategyBoolean() {
        super(Boolean.class);
    }

    @Override
    public Boolean next() {
        return newRandom().nextBoolean();
    }
}
