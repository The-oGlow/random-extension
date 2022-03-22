package com.glowanet.tools.random.impl;

import org.apache.commons.lang3.RandomUtils;

public class RandomValueBoolean extends AbstractRandomValue<Boolean> {

    public RandomValueBoolean() {
        super(Boolean.class);
    }

    @Override
    public Boolean randomValue() {
        return RandomUtils.nextBoolean();
    }
}
