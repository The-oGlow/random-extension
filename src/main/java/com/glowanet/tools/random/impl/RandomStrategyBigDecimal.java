package com.glowanet.tools.random.impl;

import java.math.BigDecimal;

/**
 * Specific Implementation for {@code BigDecimal}.
 */
public class RandomStrategyBigDecimal extends AbstractRandomStrategy<BigDecimal> {

    public RandomStrategyBigDecimal() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal next() {
        return BigDecimal.valueOf(newRandom().nextLong());
    }

    @Override
    public BigDecimal next(BigDecimal rangeStart, BigDecimal rangeEnd) {
        int min = rangeStart.intValue();
        int max = rangeEnd.intValue();
        return BigDecimal.valueOf(newRandom().nextInt(max - min + 1) + min);
    }
}
