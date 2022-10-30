package com.glowanet.tools.random.impl;

import org.hamcrest.Matcher;

import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class RandomStrategyObjectTest extends
        AbstractRandomStrategyByTypeTest<RandomStrategyObjectTest.RandomStrategyObjectTestObject, RandomStrategyObject> {

    public static class RandomStrategyObjectTestObject {
    }

    public RandomStrategyObjectTest() {
        super(RandomStrategyObject.class, RandomStrategyObjectTestObject.class);
    }

    @Override
    protected Matcher<Boolean> expectedIsSupported() {
        return equalTo(false);
    }

    @Override
    protected Class<?> actualIsSupported() {
        return TEST_CLAZZ_OBJECT;
    }

    @Override
    protected Matcher<Collection<?>> expectedSupportedTypes() {
        return hasSize(greaterThan(0));
    }

    @Override
    protected Matcher<Collection<?>> expectedGetProviders() {
        return hasSize(greaterThan(0));
    }
}