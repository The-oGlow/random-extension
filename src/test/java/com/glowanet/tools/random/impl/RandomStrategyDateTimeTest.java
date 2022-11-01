package com.glowanet.tools.random.impl;

import org.hamcrest.Matcher;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(Parameterized.class)
public class RandomStrategyDateTimeTest extends AbstractRandomStrategyByTypeTest<Object, RandomStrategyDateTime> {

    public RandomStrategyDateTimeTest() {
        super(RandomStrategyDateTime.class, null);
    }

    @Parameterized.Parameters(name = "{index} primitiveType={0}, expected={1}")
    public static List<Object[]> data() {
        List<Object[]> testData = new ArrayList<>();
        for (var primClazz : RandomStrategyDateTime.SUPP_TYPES) {
            testData.add(new Object[]{primClazz, true});
        }
        testData.add(new Object[]{RandomStrategyPrimitiveTest.class, false});
        testData.add(new Object[]{null, false});
        return testData;
    }

    @Parameterized.Parameter
    public Class<?> primType;

    @Parameterized.Parameter(1)
    public boolean primExpectedResult;

    @Override
    protected Matcher<Iterable<?>> expectedSupportedTypes() {
        return containsInAnyOrder(RandomStrategyDateTime.SUPP_TYPES.toArray());
    }

    @Override
    protected Matcher<Iterable<?>> expectedGetProviders() {
        return emptyIterable();
    }

    @Override
    protected Matcher<Boolean> expectedIsSupported() {
        return equalTo(primExpectedResult);
    }

    @Override
    protected Class<?> valuesIsSupported() {
        return primType;
    }

    @Override
    protected Matcher<Object> expectedNextWithClazz() {
        return instanceOf(primType);
    }

    @Override
    protected Class<?> valuesNextWithClazz() {
        return primType;
    }

    @Override
    protected Matcher<Object> expectedNextValueFromProvider() {
        return instanceOf(primType);
    }

    @Override
    protected Class<?> valuesNextValueFromProvider() {
        return primType;
    }
}