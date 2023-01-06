package com.glowanet.tools.random.impl;

import java.math.BigDecimal;
import java.util.List;

/**
 * Specific Implementation for {@code BigDecimal}.
 */
public class RandomStrategyBigDecimal extends AbstractRandomStrategy<BigDecimal> {

    public static final List<Class<?>> SUPP_TYPES = List.of(
            BigDecimal.class
    );

    protected RandomStrategyBigDecimal() {
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
        return BigDecimal.valueOf((newRandom().nextInt(max - min + 1) + min));
    }

    @Override
    protected List<Class<?>> getProviders() {
        return List.of(getClass());
    }
}
