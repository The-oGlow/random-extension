package com.glowanet.tools.random.impl;

import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public abstract class AbstractRandomStrategyTest<T, ST extends AbstractRandomStrategy<T>> {

    protected static final String        VERIFY_IS_NOT_SUPPORTED = "Verify the random value is not supported!";
    protected static final String        OVERRIDE_METHOD         = "Default method not supported. Please override!";
    protected static final Class<Object> TEST_CLAZZ_OBJECT       = Object.class;

    protected       ST        o2ST;
    protected final Class<ST> strategyClazz;
    protected final Class<T>  valueClazz;

    protected AbstractRandomStrategyTest(Class<ST> strategyClazz, Class<T> valueClazz) {
        this.strategyClazz = strategyClazz;
        this.valueClazz = valueClazz;
    }

    @Before
    public void setUp() {
        o2ST = prepareO2T();
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

    protected ST prepareO2T() {
        return ReflectionHelper.newInstance(strategyClazz);
    }

    protected T rangeStart() {
        throw new UnsupportedOperationException(OVERRIDE_METHOD);
    }

    protected T rangeEnd() {
        throw new UnsupportedOperationException(OVERRIDE_METHOD);
    }

    protected void verifyInRange(T actual) {
        throw new UnsupportedOperationException(VERIFY_IS_NOT_SUPPORTED);
    }
}