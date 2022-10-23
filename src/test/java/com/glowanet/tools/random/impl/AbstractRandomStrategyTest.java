package com.glowanet.tools.random.impl;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public abstract class AbstractRandomStrategyTest<T, ST extends AbstractRandomStrategy<T>> {

    protected static final String VERIFY_IS_NOT_SUPPORTED = "Verify the random value is not supported!";
    protected static final int    MIN                     = 1;
    protected static final int    MAX                     = 10000;

    protected ST        o2ST;
    protected Class<ST> strategyClazz;
    protected Class<T>  valueClazz;

    protected AbstractRandomStrategyTest(Class<ST> strategyClazz, Class<T> valueClazz) {
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
        Class<T> actual = o2ST.getTypeOfT();

        assertThat(actual, equalTo(valueClazz));
    }

    @Test
    public void testNext() {
        T prev = null;
        for (int idx = 0; idx < 2; idx++) {
            T actual = o2ST.next();
            assertThat(actual, not(equalTo(prev)));
            prev = actual;
        }
    }

    @Test
    public void testNextWithRange() {
        T actual = o2ST.next(rangeStart(), rangeEnd());

        verifyInRange(actual);
    }

    @Test
    public void newRandom() {
        SecureRandom actual = o2ST.newRandom();

        assertThat(actual, notNullValue());
    }

    protected <TC> TC newInstance(Class<TC> typeClazz) {
        return newInstance(typeClazz, (Class<?>[]) null, (Object[]) null);
    }

    protected <TC> TC newInstance(Class<TC> typeClazz, Class<?>[] parameterTypes, Object[] initargs) {
        try {
            return typeClazz.getConstructor(parameterTypes).newInstance(initargs);
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