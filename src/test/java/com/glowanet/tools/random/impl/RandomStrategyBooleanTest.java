package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.exception.RandomUnsupportedException;
import com.glowanet.util.junit.TestResultHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.oneOf;

public class RandomStrategyBooleanTest extends AbstractRandomStrategyTest<Boolean, RandomStrategyBoolean> {

    public RandomStrategyBooleanTest() {
        super(RandomStrategyBoolean.class, Boolean.class);
    }

    @Override
    protected Boolean rangeStart() {
        return Boolean.TRUE;
    }

    @Override
    protected Boolean rangeEnd() {
        return Boolean.FALSE;
    }

    @Override
    public void testNext() {
        Boolean actual = o2ST.next();
        assertThat(actual, oneOf(true, false));
    }

    @Override
    public void testNextWithRange() {
        TestResultHelper.verifyException(super::testNextWithRange, RandomUnsupportedException.class);
    }
}