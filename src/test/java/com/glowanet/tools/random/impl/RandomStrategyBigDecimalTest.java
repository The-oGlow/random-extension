package com.glowanet.tools.random.impl;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatchersExtend.betweenWithBound;

public class RandomStrategyBigDecimalTest extends AbstractRandomStrategyTest<BigDecimal, RandomStrategyBigDecimal> {

    public RandomStrategyBigDecimalTest() {
        super(RandomStrategyBigDecimal.class, BigDecimal.class);
    }

    @Override
    protected BigDecimal rangeStart() {
        return BigDecimal.valueOf(MIN);
    }

    @Override
    protected BigDecimal rangeEnd() {
        return BigDecimal.valueOf(MAX);
    }

    @Override
    protected void verifyInRange(BigDecimal actual) {
        assertThat(actual, notNullValue());
        assertThat(actual, betweenWithBound(rangeStart(), rangeEnd()));
    }
}