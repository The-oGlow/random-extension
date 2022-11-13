package com.glowanet.tools.random.impl;

import com.glowanet.reflect.Primitive;
import org.hamcrest.Matcher;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;

@RunWith(Parameterized.class)
public class RandomStrategyPrimitiveTest<V extends Comparable<V>> extends AbstractRandomStrategyByTypeTest<V> {

    @Parameterized.Parameters(name = "{index} primitiveType={0}, expected={1}")
    public static List<Object[]> data() {
        List<Object[]> testData = new ArrayList<>();
        for (var entry : Primitive.all()) {
            testData.add(new Object[]{entry, true});
        }
        testData.add(new Object[]{RandomStrategyPrimitive.class, false});
        testData.add(new Object[]{null, false});
        return testData;
    }

    @Parameterized.Parameter
    public Class<?> primType;

    @Parameterized.Parameter(1)
    public boolean primExpectedResult;

    public RandomStrategyPrimitiveTest() {
        super(RandomStrategyPrimitive.class);
    }

    @Override
    protected List<Class<?>> invalidTestData() {
        return Arrays.asList(RandomStrategyPrimitive.class, null);
    }

    @Override
    protected Matcher<Iterable<?>> supportedTypesExpect() {
        return containsInAnyOrder(
                RandomStrategyPrimitive.SUPP_TYPES.toArray()
        );
    }

    @Override
    protected Matcher<Iterable<?>> getProvidersExpect() {
        return containsInAnyOrder(RandomStrategyPrimitive.class);
    }

    @Override
    protected Matcher<Boolean> isSupportedExpect() {
        return equalTo(primExpectedResult);
    }

    @Override
    protected Class<?> isSupportedValues() {
        return primType;
    }

    @Override
    protected Class<?> loopThruProviderValues() {
        return primType;
    }

    @Override
    protected Matcher<?> loopThruProviderExpect() {
        Matcher<?> result = expectInvalidResult(primType);
        if (result == null) {
            result = anyOf(instanceOf(primType), instanceOf(Primitive.viceVersa(primType)));
        }
        return result;
    }

    @Override
    protected Matcher<V> nextValueFromProviderExpect() {
        Matcher<?> result = expectInvalidResult(primType);
        if (result == null) {
            result = anyOf(instanceOf(primType));
        }
        return (Matcher<V>) result;
    }

    @Override
    protected Class<?> nextValueFromProviderValues() {
        return primType;
    }

    @Override
    protected Class<?> nextValueFromProviderProvider() {
        return RandomStrategyPrimitive.class;
    }

    @Override
    protected Matcher<Object> nextWithClazzExpect() {
        Matcher<?> result = expectInvalidResult(primType);
        if (result == null) {
            result = anyOf(nullValue(), instanceOf(primType));
        }
        return (Matcher<Object>) result;
    }

    @Override
    protected Class<?> nextWithClazzValues() {
        return primType;
    }
}