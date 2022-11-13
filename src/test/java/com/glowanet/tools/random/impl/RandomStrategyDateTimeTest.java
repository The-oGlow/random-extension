package com.glowanet.tools.random.impl;

import org.hamcrest.Matcher;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(Parameterized.class)
public class RandomStrategyDateTimeTest<VT extends Comparable<VT>> extends AbstractRandomStrategyByTypeTest<VT> {

    @Parameterized.Parameters(name = "{index} dateTimeType={0}, expected={1}")
    public static List<Object[]> data() {
        List<Object[]> testData = new ArrayList<>();
        for (var primClazz : RandomStrategyDateTime.SUPP_TYPES) {
            testData.add(new Object[]{primClazz, true});
        }
        testData.add(new Object[]{null, false});
        return testData;
    }

    @Parameterized.Parameter
    public Class<?> primType;

    @Parameterized.Parameter(1)
    public boolean primExpectedResult;

    public RandomStrategyDateTimeTest() {
        super(RandomStrategyDateTime.class);
    }

    @Override
    protected List<Class<?>> invalidTestData() {
        return Arrays.asList(RandomStrategyDateTime.class, null);
    }

    @Override
    protected Matcher<Iterable<?>> supportedTypesExpect() {
        return containsInAnyOrder(
                Date.class, Time.class, Timestamp.class,
                LocalDateTime.class, LocalDate.class, LocalTime.class,
                ZonedDateTime.class, OffsetDateTime.class, OffsetTime.class,
                Instant.class
        );
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
    protected Matcher<Iterable<?>> getProvidersExpect() {
        return containsInAnyOrder(RandomStrategyDateTime.class);
    }

    @Override
    protected Matcher<?> nextWithClazzExpect() {
        Matcher<?> result = expectInvalidResult(primType);
        if (result == null) {
            result = instanceOf(primType);
        }
        return result;
    }

    @Override
    protected Class<?> loopThruProviderValues() {
        return primType;
    }

    @Override
    protected Matcher<?> loopThruProviderExpect() {
        Matcher<?> result = expectInvalidResult(primType);
        if (result == null) {
            result = anyOf(instanceOf(primType));
        }
        return result;
    }

    @Override
    protected Class<?> nextWithClazzValues() {
        return primType;
    }

    @Override
    protected Class<?> nextValueFromProviderProvider() {
        return RandomStrategyDateTime.class;
    }

    @Override
    protected Matcher<?> nextValueFromProviderExpect() {
        Matcher<?> result = expectInvalidResult(primType);
        if (result == null) {
            result = instanceOf(primType);
        }
        return result;
    }

    @Override
    protected Class<?> nextValueFromProviderValues() {
        return primType;
    }
}