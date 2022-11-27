package com.glowanet.tools.random.impl;

import com.glowanet.util.reflect.ReflectionHelper;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Base clazz for all tests.
 *
 * @param <V> the type of the random value
 */
public abstract class AbstractRandomStrategyTest<V> {

    protected static final String        VERIFY_IS_NOT_SUPPORTED = "Verify the random value is not supported!";
    protected static final String        OVERRIDE_METHOD         = "Default method not supported. Please override!";
    protected static final Class<Object> TEST_CLAZZ_OBJECT       = Object.class;
    protected static final Class<?>      TEST_CLAZZ_STRING       = String.class;
    protected static final Class<?>      TEST_CLAZZ_PRIMITIVE    = long.class;

    protected       AbstractRandomStrategy<V> o2ST;
    protected final Class<?>                  strategyClazz;

    protected AbstractRandomStrategyTest(Class<?> strategyClazz) {
        this.strategyClazz = strategyClazz;
    }

    protected AbstractRandomStrategy<V> prepareO2T() {
        return ReflectionHelper.newInstance(strategyClazz);
    }

    @Before
    public void setUp() {
        o2ST = prepareO2T();
        assertThat(o2ST, instanceOf(strategyClazz));
    }

    protected abstract Matcher<Iterable<?>> getProvidersExpect();

    @Test
    public void testGetProviders() {
        List<Class<?>> actual = o2ST.getProviders();

        assertThat(actual, getProvidersExpect());
    }

    protected abstract Class<?> isSupportedValue();

    protected abstract Matcher<Boolean> isSupportedExpect();

    @Test
    public void testIsSupported() {
        boolean actual = o2ST.isSupported(isSupportedValue());

        assertThat(String.format("For '%s' exepected support:", isSupportedValue()), actual, isSupportedExpect());
    }

    @Test
    public void testNext() {
        V prev = null;
        for (int idx = 0; idx < 2; idx++) {
            V actual = o2ST.next();
            assertThat(actual, not(equalTo(prev)));
            prev = actual;
        }
    }

    protected V valuesRangeStart() {
        throw new UnsupportedOperationException(OVERRIDE_METHOD);
    }

    protected V valuesRangeEnd() {
        throw new UnsupportedOperationException(OVERRIDE_METHOD);
    }

    protected Matcher<V> nextWithRangeExpect() {
        throw new UnsupportedOperationException(VERIFY_IS_NOT_SUPPORTED);
    }

    @Test
    public void testNextWithRange() {
        V actual = o2ST.next(valuesRangeStart(), valuesRangeEnd());

        assertThat(actual, nextWithRangeExpect());
    }

    @Test
    public void testNewRandom() {
        Random actual = o2ST.newRandom();

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(SecureRandom.class));
    }

    protected abstract Matcher<Iterable<?>> supportedTypesExpect();

    @Test
    public void testSupportedTypes() {
        List<Class<?>> actual = o2ST.supportedTypes();

        assertThat(actual, supportedTypesExpect());
    }

    protected abstract Matcher<V> valueByStaticDefinitionExpect();

    @Test
    public void testValueByStaticDefinition() {
        V actual = o2ST.valueByStaticDefinition();

        assertThat(actual, valueByStaticDefinitionExpect());
    }

}