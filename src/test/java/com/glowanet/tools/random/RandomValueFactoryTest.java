package com.glowanet.tools.random;

import com.glowanet.tools.random.exception.RandomUnsupportedException;
import com.glowanet.tools.random.impl.RandomStrategyObject;
import com.glowanet.tools.random.impl.RandomStrategyPrimitive;
import com.glowanet.util.junit.TestResultHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
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

    @Parameterized.Parameters(name = "{index}: fallbackActive={0},silent={1}")
    public static List<Object> data() {
        return Arrays.asList(
                new Object[][]{{true, true}, {true, false}, {false, true}, {false, false}});
    }

    @Parameterized.Parameter
    public boolean fallbackActive;

    @Parameterized.Parameter(1)
    public boolean silent;

    @Before
    public void setUp() {
        RandomValueFactory.getInstance().setFallback(fallbackActive);
        RandomValueFactory.getInstance().setSilent(silent);
    }

    private void verifyFallback(boolean active) {
        LOGGER.info("Running with fallback : {}", active);
        assertThat("fallback must be " + (active ? "active" : "deactivated"), RandomValueFactory.getInstance().isFallback(), is(active));
    }

    private void verifySilent(boolean silent) {
        LOGGER.info("Running with silent : {}", silent);
        assertThat("silent must be " + (silent ? "active" : "deactivated"), RandomValueFactory.getInstance().isSilent(), is(silent));
    }

    private void assertThatDecision(Object actual, Matcher<Object> trueMatcher, Matcher<Object> falseMatcher) {
        if (fallbackActive) {
            assertThat(actual, trueMatcher);
        } else {
            assertThat(actual, falseMatcher);
        }

    }

    private boolean getSilentFlag() {
        return RandomValueFactory.getInstance().isSilent();
    }

    @Test
    public void testIsFallback() {
        verifyFallback(fallbackActive);
    }

    @Test
    public void testIsSilent() {
        verifySilent(silent);
    }

    @Test
    public void testSetSilent() {
        assertThat(getSilentFlag(), equalTo(silent));

        RandomValueFactory.getInstance().setSilent(!silent);
        assertThat(getSilentFlag(), equalTo(!silent));

        RandomValueFactory.getInstance().setSilent(silent);
        assertThat(getSilentFlag(), equalTo(silent));

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

    @SuppressWarnings("ConstantConditions")
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

        assertThatDecision(actual, isA(RandomStrategyPrimitive.class), nullValue());
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

    @Test
    public void testConvertPrimitive_withInt_return_integer() {
        Class<?> actual = RandomValueFactory.getInstance().convertPrimitive(int.class);
        assertThat(actual, equalTo(Integer.class));
    }

    @Test
    public void testConvertPrimitive_withInteger_return_integer() {
        Class<?> actual = RandomValueFactory.getInstance().convertPrimitive(Integer.class);
        assertThat(actual, equalTo(Integer.class));
    }

    @Test
    public void testConvertPrimitive_withNonPrimitive_return_NonePrimitive() {
        Class<?> actual = RandomValueFactory.getInstance().convertPrimitive(BigDecimal.class);
        assertThat(actual, equalTo(BigDecimal.class));
    }

}