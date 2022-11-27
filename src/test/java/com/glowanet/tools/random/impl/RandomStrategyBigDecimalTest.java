package com.glowanet.tools.random.impl;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsBetween;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatchersExtend.betweenWithBound;

@RunWith(Parameterized.class)
public class RandomStrategyBigDecimalTest<V extends Comparable<V>> extends AbstractRandomStrategyTest<V> {

    private static final long MIN = 1;
    private static final long MAX = Integer.MAX_VALUE;

    @Parameterized.Parameters(name = "{index} bigType={0}, expected={1}")
    public static List<Object[]> data() {
        List<Object[]> testData = new ArrayList<>();
        testData.add(new Object[]{BigDecimal.class, true});
        testData.add(new Object[]{RandomStrategyBigDecimal.class, false});
        testData.add(new Object[]{null, false});
        return testData;
    }

    @Parameterized.Parameter
    public Class<?> bigType;

    @Parameterized.Parameter(1)
    public boolean bigExpectedResult;

    public RandomStrategyBigDecimalTest() {
        super(RandomStrategyBigDecimal.class);
    }

    @Override
    protected Matcher<Iterable<?>> getProvidersExpect() {
        return containsInAnyOrder(RandomStrategyBigDecimal.class);
    }

    @Override
    protected Class<?> isSupportedValue() {
        return bigType;
    }

    @Override
    protected Matcher<Boolean> isSupportedExpect() {
        return equalTo(bigExpectedResult);
    }

    @Override
    protected V valuesRangeStart() {
        return (V) BigDecimal.valueOf(MIN);
    }

    @Override
    protected V valuesRangeEnd() {
        return (V) BigDecimal.valueOf(MAX);
    }

    @Override
    protected Matcher<V> nextWithRangeExpect() {
        IsBetween.Range<V> fromTo = new IsBetween.Range<>(valuesRangeStart(), valuesRangeEnd());
        return anyOf(notNullValue(), betweenWithBound(fromTo));
    }

    @Override
    protected Matcher<Iterable<?>> supportedTypesExpect() {
        return containsInAnyOrder(BigDecimal.class);
    }

    @Override
    protected Matcher<V> valueByStaticDefinitionExpect() {
        return anyOf(nullValue());
    }
}