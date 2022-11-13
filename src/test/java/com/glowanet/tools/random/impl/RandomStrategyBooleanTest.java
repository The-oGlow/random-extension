package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.exception.RandomUnsupportedException;
import com.glowanet.util.junit.TestResultHelper;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.oneOf;

@RunWith(Parameterized.class)
public class RandomStrategyBooleanTest<VT extends Comparable<VT>> extends AbstractRandomStrategyTest<VT> {

    @Parameterized.Parameters(name = "{index} boolType={0}, expected={1}")
    public static List<Object[]> data() {
        List<Object[]> testData = new ArrayList<>();
        testData.add(new Object[]{Boolean.class, true});
        testData.add(new Object[]{boolean.class, true});
        testData.add(new Object[]{RandomStrategyBoolean.class, false});
        testData.add(new Object[]{null, false});
        return testData;
    }

    @Parameterized.Parameter
    public Class<?> boolType;

    @Parameterized.Parameter(1)
    public boolean boolExpectedResult;

    public RandomStrategyBooleanTest() {
        super(RandomStrategyBoolean.class);
    }

    @Override
    protected Matcher<Iterable<?>> getProvidersExpect() {
        return containsInAnyOrder(RandomStrategyBoolean.class);
    }

    @Override
    protected Class<?> isSupportedValue() {
        return boolType;
    }

    @Override
    protected Matcher<Boolean> isSupportedExpect() {
        return equalTo(boolExpectedResult);
    }

    @Override
    @Test
    public void testNext() {
        Object actual = o2ST.next();
        assertThat(actual, oneOf(true, false, Boolean.TRUE, Boolean.FALSE));
    }

    @Override
    protected Matcher<Iterable<?>> supportedTypesExpect() {
        return containsInAnyOrder(Boolean.class, boolean.class);
    }

    @Override
    protected VT valuesRangeStart() {
        return (VT) Boolean.TRUE;
    }

    @Override
    protected VT valuesRangeEnd() {
        return (VT) Boolean.FALSE;
    }

    @Override
    @Test
    public void testNextWithRange() {
        TestResultHelper.verifyException(super::testNextWithRange, RandomUnsupportedException.class);
    }

    @Override
    protected Matcher<VT> valueByStaticDefinitionExpect() {
        return anyOf(nullValue());
    }
}