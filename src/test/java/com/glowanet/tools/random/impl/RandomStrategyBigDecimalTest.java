package com.glowanet.tools.random.impl;

import org.hamcrest.MatchersExtend;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

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

//    @Override
//    @Test
//    public void testNextWithRange() {
//        BigDecimal actual = o2ST.next(rangeStart(), rangeEnd());
//
//        MatcherAssert.assertThat(actual, Matchers.instanceOf(valueClazz));
//    }

    @Override
    protected void verifyInRange(BigDecimal actual) {
        assertThat(actual, notNullValue());
        MatchersExtend.betweenWithBound(rangeStart(), rangeEnd());

        assertThat(actual, MatchersExtend.betweenWithBound(rangeStart(), rangeEnd()));
    }
}