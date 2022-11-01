package com.glowanet.tools.random.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.Matcher;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;

@RunWith(Parameterized.class)
public class RandomStrategyObjectTest extends
        AbstractRandomStrategyByTypeTest<RandomStrategyObjectTest.RandomStrategyObjectTestObject, RandomStrategyObject> {

    @Parameterized.Parameters(name = "{index} primitiveType={0}, expected={1}")
    public static List<Object[]> data() {
        List<Object[]> testData = new ArrayList<>();
        for (var primClazz : RandomStrategyObject.SUPP_TYPES) {
            testData.add(new Object[]{primClazz, true});
        }
        testData.add(new Object[]{RandomStrategyObjectTest.class, false});
        testData.add(new Object[]{null, false});
        return testData;
    }

    @Parameterized.Parameter
    public Class<?> primType;

    @Parameterized.Parameter(1)
    public boolean primExpectedResult;

    public static class RandomStrategyObjectTestObject {
    }

    public RandomStrategyObjectTest() {
        super(RandomStrategyObject.class, RandomStrategyObjectTestObject.class);
    }

    @Override
    protected Matcher<Iterable<?>> expectedSupportedTypes() {
        return containsInAnyOrder(
                ArrayUtils.addAll(
                        RandomStrategyPrimitive.SUPP_TYPES.toArray(),
                        ArrayUtils.addAll(
                                RandomStrategyDateTime.SUPP_TYPES.toArray(),
                                RandomStrategyBigDecimal.SUPP_TYPES.toArray()
                        )
                )
        );
    }

    @Override
    protected Matcher<Iterable<?>> expectedGetProviders() {
        return containsInAnyOrder(
                RandomStrategyPrimitive.class,
                RandomStrategyDateTime.class,
                RandomStrategyBigDecimal.class
        );
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
    protected Matcher<RandomStrategyObjectTestObject> expectedNextWithClazz() {
        return instanceOf(primType);
    }

    @Override
    protected Class<?> valuesNextWithClazz() {
        return primType;
    }

    @Override
    protected Matcher<RandomStrategyObjectTestObject> expectedNextValueFromProvider() {
        return anyOf(nullValue(), instanceOf(primType));
    }

    @Override
    protected Class<?> valuesNextValueFromProvider() {
        return primType;
    }

}