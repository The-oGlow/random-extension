package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.Primitive;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class RandomStrategyPrimitiveTest<T> extends AbstractRandomStrategyByTypeTest<T, RandomStrategyPrimitive> {

    public RandomStrategyPrimitiveTest() {
        super(RandomStrategyPrimitive.class, null);
    }

    @Override
    protected Matcher<Boolean> expectedIsSupported() {
        return equalTo(true);
    }

    @Override
    protected Class<?> actualIsSupported() {
        return Integer.class;
    }

    @Override
    protected Matcher<Collection<?>> expectedSupportedTypes() {
        return hasSize(allOf(greaterThan(0), lessThanOrEqualTo(Primitive.size() + 1)));
    }

    @Override
    protected Matcher<Collection<?>> expectedGetProviders() {
        return hasSize(0);
    }

    @Override
    @Test
    public void testNext() {
        super.testNext();
    }

}