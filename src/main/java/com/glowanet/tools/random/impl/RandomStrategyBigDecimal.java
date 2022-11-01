package com.glowanet.tools.random.impl;

import java.math.BigDecimal;
import java.util.List;

/**
 * Specific Implementation for {@code BigDecimal}.
 */
public class RandomStrategyBigDecimal extends AbstractRandomStrategy<BigDecimal> {

    protected static final List<Class<?>> SUPP_TYPES = List.of(
            BigDecimal.class
    );

    public RandomStrategyBigDecimal() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal next() {
        return BigDecimal.valueOf(newRandom().nextLong());
    }

    @Override
    public BigDecimal next(BigDecimal rangeStart, BigDecimal rangeEnd) {
        var min = rangeStart.intValue();
        var max = rangeEnd.intValue();
        return BigDecimal.valueOf(newRandom().nextInt(max - ((int) min) + 1) + min);
    }
}
