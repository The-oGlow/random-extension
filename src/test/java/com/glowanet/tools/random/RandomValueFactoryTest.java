package com.glowanet.tools.random;

import com.glowanet.tools.random.exception.RandomUnsupportedException;
import com.glowanet.tools.random.impl.RandomStrategyObject;
import com.glowanet.util.junit.TestResultHelper;
import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@RunWith(Parameterized.class)
public class RandomValueFactoryTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Parameterized.Parameters(name = "{index}: fallbackActive={0}")
    public static List<Object> data() {
        return Arrays.asList(new Object[]{true, false});
    }

    @Parameterized.Parameter(0)
    public boolean fallbackActive;

    @Before
    public void setUp() {
        RandomValueFactory.getInstance().setFallback(fallbackActive);
    }

    private void verifyFallback(boolean active) {
        LOGGER.info("Running with fallback : {}", active);
        assertThat("fallback must be " + (active ? "active" : "deactivated"), RandomValueFactory.getInstance().isFallback(), is(active));
    }

    private void assertThatDecision(Object actual, Matcher<Object> trueMatcher, Matcher<Object> falseMatcher) {
        if (fallbackActive) {
            assertThat(actual, trueMatcher);
        } else {
            assertThat(actual, falseMatcher);
        }

    }

    private boolean getSilentFlag() {
        return ReflectionHelper.readField("silent", RandomValueFactory.getInstance());
    }

    @Test
    public void testIsFallback() {
        verifyFallback(fallbackActive);
    }

    @Test
    public void testSetSilent() {
        assertThat(getSilentFlag(), equalTo(true));

        RandomValueFactory.getInstance().setSilent(false);
        assertThat(getSilentFlag(), equalTo(false));

        RandomValueFactory.getInstance().setSilent(true);
        assertThat(getSilentFlag(), equalTo(true));

    }

    @Test
    public void testGetInstance_return_instance() {
        Object actual = RandomValueFactory.getInstance();

        assertThat(actual, isA(RandomValueFactory.class));
    }

    @Test
    public void testGetProviderLocation_return_string() {
        String actual = RandomValueFactory.getInstance().getProviderLocation();

        assertThat(actual, not(emptyString()));
    }

    @Test
    public void testGetProvider_withNull_throws_randomUnsupportedException() {
        TestResultHelper.verifyException(() -> RandomValueFactory.getInstance().getProvider(null), RandomUnsupportedException.class);
    }

    @Test
    public void testGetProvider_withObject_return_randomStrategyObject() {
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(Object.class);

        assertThat(actual, isA(RandomStrategyObject.class));
    }

    @Test
    public void testGetProvider_withPrimitive_return_variants() {
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(int.class);

        assertThatDecision(actual, isA(RandomStrategyObject.class), nullValue());
    }

    @Test
    public void testGenerateRandomProvider_withObjectAndNoParameter_return_randomStrategyObject() {
        ICommonStrategy actual = RandomValueFactory.getInstance().generateRandomProvider(Object.class);

        assertThat(actual, isA(RandomStrategyObject.class));
    }

    @Test
    public void testGenerateRandomProvider_withObjectAndNullParameter_return_null() {
        ICommonStrategy actual = RandomValueFactory.getInstance().generateRandomProvider(Object.class, (Class<?>) null);

        assertThat(actual, nullValue());
    }

    @Test
    public void testGenerateRandomProvider_withObjectAndNullArray_return_null() {
        ICommonStrategy actual = RandomValueFactory.getInstance().generateRandomProvider(Object.class, new Class[]{null});

        assertThat(actual, nullValue());
    }

    @Test
    public void testInstantiateProvider_withObject_return_randomStrategyObject() {
        ICommonStrategy actual = RandomValueFactory.getInstance().instantiateProvider(RandomStrategyObject.class, Object.class);

        assertThat(actual, instanceOf(RandomStrategyObject.class));
    }

    @Test
    public void testInstantiateProvider_withObjectAndNullParameter_return_randomStrategyObject() {
        ICommonStrategy actual = RandomValueFactory.getInstance().instantiateProvider(RandomStrategyObject.class, Object.class, (Class<?>) null);

        assertThat(actual, nullValue());
    }

    @Test
    public void testInstantiateProvider_withObjectAndNullArray_return_randomStrategyObject() {
        ICommonStrategy actual = RandomValueFactory.getInstance().instantiateProvider(RandomStrategyObject.class, Object.class, new Class[]{null});

        assertThat(actual, nullValue());
    }

    @Test
    public void testInstantiateProvider_withPrimitive_return_randomStrategyObject() {
        ICommonStrategy actual = RandomValueFactory.getInstance().instantiateProvider(RandomStrategyObject.class, int.class);

        assertThat(actual, instanceOf(RandomStrategyObject.class));
    }
}