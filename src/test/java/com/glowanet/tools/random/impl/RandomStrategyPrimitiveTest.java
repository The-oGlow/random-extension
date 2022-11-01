package com.glowanet.tools.random.impl;

import com.glowanet.reflect.Primitive;
import org.hamcrest.Matcher;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;

@RunWith(Parameterized.class)
public class RandomStrategyPrimitiveTest<T> extends AbstractRandomStrategyByTypeTest<T, RandomStrategyPrimitive> {

    public RandomStrategyPrimitiveTest() {
        super(RandomStrategyPrimitive.class, null);
    }

    @Parameterized.Parameters(name = "{index} primitiveType={0}, expected={1}")
    public static List<Object[]> data() {
        List<Object[]> testData = new ArrayList<>();
        for (var primClazz : Primitive.typesOfPrimitiveAll()) {
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
        return containsInAnyOrder(
                RandomStrategyPrimitive.SUPP_TYPES.toArray()
        );
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
    protected Matcher<T> expectedNextValueFromProvider() {
        return anyOf(nullValue(), instanceOf(primType));
    }

    @Override
    protected Class<?> valuesNextValueFromProvider() {
        return primType;
    }

    @Override
    protected Matcher<T> expectedNextWithClazz() {
        return anyOf(nullValue(), instanceOf(primType));
    }

    @Override
    protected Class<?> valuesNextWithClazz() {
        return primType;
    }
}