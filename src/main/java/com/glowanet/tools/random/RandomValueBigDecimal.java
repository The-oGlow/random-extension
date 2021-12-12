package com.glowanet.tools.random;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;

public class RandomValueBigDecimal extends AbstractRandomValue<BigDecimal> {

    RandomValueBigDecimal() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal randomValue() {
        return BigDecimal.valueOf(RandomUtils.nextLong());
    }

    @Override
    public BigDecimal randomValue(BigDecimal rangeStart, BigDecimal rangeEnd) {
        return BigDecimal.valueOf(RandomUtils.nextLong(rangeStart.longValue(), rangeEnd.longValue()));
    }
}
