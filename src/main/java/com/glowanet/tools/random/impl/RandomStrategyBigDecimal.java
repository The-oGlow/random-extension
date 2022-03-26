package com.glowanet.tools.random.impl;

import org.apache.commons.lang3.RandomUtils;

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
        return BigDecimal.valueOf(RandomUtils.nextLong());
    }

    @Override
    public BigDecimal next(BigDecimal rangeStart, BigDecimal rangeEnd) {
        return BigDecimal.valueOf(RandomUtils.nextLong(rangeStart.longValue(), rangeEnd.longValue()));
    }
}
