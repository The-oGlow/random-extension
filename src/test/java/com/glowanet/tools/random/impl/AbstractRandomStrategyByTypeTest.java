package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.exception.RandomUnsupportedException;
import com.glowanet.util.junit.TestResultHelper;
import com.glowanet.util.reflect.ReflectionHelper;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public abstract class AbstractRandomStrategyByTypeTest<T, ST extends AbstractRandomStrategyByType> {

    protected static final String   VERIFY_IS_NOT_SUPPORTED = "Verify the random value is not supported!";
    protected static final String   OVERRIDE_METHOD         = "Default method not supported. Please override!";
    protected static final Class<?> TEST_CLAZZ_OBJECT       = Object.class;
    protected static final Class<?> TEST_CLAZZ_STRING       = String.class;
    protected static final Class<?> TEST_CLAZZ_PRIMITIVE    = long.class;

    protected       ST        o2ST;
    protected final Class<ST> strategyClazz;
    protected final Class<T>  valueClazz;

    public AbstractRandomStrategyByTypeTest(Class<ST> strategyClazz, Class<T> valueClazz) {
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
        TestResultHelper.verifyException(() -> o2ST.getTypeOfT(), RandomUnsupportedException.class);
    }

    @Test
    public void testIsSupported() {
        boolean actual = o2ST.isSupported(valuesIsSupported());

        assertThat(String.format("For '%s' exepected support:", valuesIsSupported()), actual, expectedIsSupported());
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
    public void testNextWithClazz() {
        T actual = o2ST.next(valuesNextWithClazz());

        assertThat(actual, expectedNextWithClazz());
    }

    @Test
    public void testSupportedTypes() {
        List<Class<?>> actual = o2ST.supportedTypes();

        assertThat(actual, expectedSupportedTypes());
    }

    @Test
    public void testGetProviders() {
        List<Class<?>> actual = o2ST.getProviders();

        assertThat(actual, expectedGetProviders());
    }

    @Test
    public void testLoopThruProvider() {
        Object actual = o2ST.loopThruProvider(TEST_CLAZZ_OBJECT);

        assertThat(actual, nullValue());
    }

    @Test
    public void testNewRandom() {
        SecureRandom actual = o2ST.newRandom();

        assertThat(actual, notNullValue());
    }

    @Test
    public void testNextValueFromProvider() {
        T actual = (T) o2ST.nextValueFromProvider(RandomStrategyObject.class, valuesNextValueFromProvider());

        assertThat(actual, expectedNextValueFromProvider());
    }

    @Test
    public void testValueByStaticDefinition() {
        Object actual = o2ST.valueByStaticDefinition(TEST_CLAZZ_OBJECT);

        assertThat(actual, nullValue());
    }

    protected abstract Matcher<Boolean> expectedIsSupported();

    protected abstract Class<?> valuesIsSupported();

    protected abstract Matcher<Iterable<?>> expectedSupportedTypes();

    protected abstract Matcher<Iterable<?>> expectedGetProviders();

    protected abstract Matcher<T> expectedNextWithClazz();

    protected abstract Class<?> valuesNextWithClazz();

    protected abstract Matcher<T> expectedNextValueFromProvider();

    protected abstract Class<?> valuesNextValueFromProvider();

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