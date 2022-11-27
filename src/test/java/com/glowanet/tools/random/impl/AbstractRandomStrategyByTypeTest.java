package com.glowanet.tools.random.impl;

import com.glowanet.util.reflect.ReflectionHelper;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;

public abstract class AbstractRandomStrategyByTypeTest<V> { //, ST extends AbstractRandomStrategyByType> {

    //protected static final Class<?> TEST_CLAZZ_OBJECT = Object.class;

    protected       AbstractRandomStrategyByType o2ST;
    protected final Class<?>                     strategyClazz;

    public AbstractRandomStrategyByTypeTest(Class<?> strategyClazz) {
        this.strategyClazz = strategyClazz;
    }

    protected AbstractRandomStrategyByType prepareO2T() {
        return ReflectionHelper.newInstance(strategyClazz);
    }

    @Before
    public void setUp() {
        o2ST = prepareO2T();
        assertThat(o2ST, instanceOf(strategyClazz));
    }

    protected abstract Class<?> isSupportedValues();

    protected abstract Matcher<Boolean> isSupportedExpect();

    @Test
    public void testIsSupported() {
        boolean actual = o2ST.isSupported(isSupportedValues());

        assertThat(String.format("For '%s' exepected support: %s", isSupportedValues(), actual), actual, isSupportedExpect());
    }

    protected abstract Matcher<Iterable<?>> getProvidersExpect();

    @Test
    public void testGetProviders() {
        List<Class<?>> actual = o2ST.getProviders();

        assertThat(actual, getProvidersExpect());
    }

    protected abstract Class<?> loopThruProviderValues();

    protected abstract Matcher<?> loopThruProviderExpect();

    @Test
    public void testLoopThruProvider() {
        Object actual = o2ST.loopThruProvider(loopThruProviderValues());

        assertThat((V) actual, (Matcher<V>) loopThruProviderExpect());
    }

    protected abstract Class<?> nextValueFromProviderValues();

    protected abstract Matcher<?> nextValueFromProviderExpect();

    protected abstract Class<?> nextValueFromProviderProvider();

    @Test
    public void testNextValueFromProvider() {
        Object actual = o2ST.nextValueFromProvider(nextValueFromProviderProvider(), nextValueFromProviderValues());

        assertThat((V) actual, (Matcher<V>) nextValueFromProviderExpect());
    }

    @Test
    public void testNextValueFromProviderWithNullProvider() {
        Object actual = o2ST.nextValueFromProvider(null, nextValueFromProviderValues());

        assertThat((V) actual, nullValue());
    }

    protected abstract Class<?> nextWithClazzValues();

    protected abstract Matcher<?> nextWithClazzExpect();

    @Test
    public void testNextWithClazz() {
        V actual = o2ST.next(nextWithClazzValues());

        assertThat(actual, (Matcher<V>) nextWithClazzExpect());
    }

    protected abstract Matcher<Iterable<?>> supportedTypesExpect();

    @Test
    public void testSupportedTypes() {
        List<Class<?>> actual = o2ST.supportedTypes();

        assertThat(actual, supportedTypesExpect());
    }

    protected abstract List<Class<?>> invalidTestData();

    protected Matcher<?> expectInvalidResult(Class<?> valueClazz) {
        if (invalidTestData().contains(valueClazz)) {
            return nullValue();
        }
        return null;
    }
}