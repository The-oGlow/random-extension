package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.exception.RandomUnsupportedException;
import com.glowanet.util.junit.TestResultHelper;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public abstract class AbstractRandomStrategyByTypeTest<T, ST extends AbstractRandomStrategyByType> {

    protected static final String VERIFY_IS_NOT_SUPPORTED = "Verify the random value is not supported!";
    protected static final int    MIN                     = 1;
    protected static final int    MAX                     = 10000;

    protected       ST        o2ST;
    protected final Class<ST> strategyClazz;
    protected final Class<T>  valueClazz;

    public AbstractRandomStrategyByTypeTest(Class<ST> strategyClazz, Class<T> valueClazz) {
        this.strategyClazz = strategyClazz;
        this.valueClazz = valueClazz;
    }

    @Before
    public void setUp() {
        o2ST = prepO2T();
        assertThat(o2ST, instanceOf(strategyClazz));
    }

    @Test
    public void testGetTypeOfT() {
        TestResultHelper.verifyException(() -> o2ST.getTypeOfT(), RandomUnsupportedException.class);
    }

    @Test
    public void testNext() {
        TestResultHelper.verifyException(() -> o2ST.next(), RandomUnsupportedException.class);
    }

    @Test
    public void testNextWithRange() {
        TestResultHelper.verifyException(() -> o2ST.next(), RandomUnsupportedException.class);
    }

    @Test
    public void testValueByStaticDefinition() {
        Object actual = o2ST.valueByStaticDefinition(Object.class);

        assertThat(actual, nullValue());
    }

    @Test
    public void newRandom() {
        SecureRandom actual = o2ST.newRandom();

        assertThat(actual, notNullValue());
    }

    protected <TC> TC newInstance(Class<TC> typeClazz) {
        return newInstance(typeClazz, null, null);
    }

    protected <TC> TC newInstance(Class<TC> typeClazz, Class<?>[] parameterTypes, Object[] initargs) {
        try {
            return ConstructorUtils.invokeConstructor(typeClazz, initargs, parameterTypes);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    protected ST prepO2T() {
        return newInstance(strategyClazz);
    }

    protected T rangeStart() {
        return newInstance(valueClazz, new Class[]{int.class}, new Object[]{1});
    }

    protected T rangeEnd() {
        return newInstance(valueClazz, new Class[]{int.class}, new Object[]{100});
    }

    protected void verifyInRange(T actual) {
        throw new UnsupportedOperationException(VERIFY_IS_NOT_SUPPORTED);
    }
}