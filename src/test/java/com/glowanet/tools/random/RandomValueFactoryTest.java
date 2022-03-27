package com.glowanet.tools.random;

import com.glowanet.tools.random.impl.RandomStrategyObject;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class RandomValueFactoryTest {

    @Before
    public void setUp() {
        RandomValueFactory.getInstance().setAutoLegacy(true);
    }

    private void verifyAutoLegacyActive() {
        assertThat("autoLegacy must be active", RandomValueFactory.getInstance().isAutoLegacy(), is(true));
    }

    private void verifyAutoLegacyDeactive() {
        assertThat("autoLegacy must be deactivated", RandomValueFactory.getInstance().isAutoLegacy(), is(false));
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
    public void testGetProvider_withNull_return_null() {
        verifyAutoLegacyActive();
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(null);

        assertThat(actual, isA(RandomStrategyObject.class));
    }

    @Test
    public void testGetProvider_withNullNoAutoLegacy_return_null() {
        RandomValueFactory.getInstance().setAutoLegacy(false);
        verifyAutoLegacyDeactive();
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(null);

        assertThat(actual, nullValue());
    }

    @Test
    public void testGetProvider_withObject_return_provider() {
        verifyAutoLegacyActive();
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(Object.class);

        assertThat(actual, isA(RandomStrategyObject.class));
    }

    @Test
    public void testGetProvider_withObjectNoAutoLegacy_return_null() {
        RandomValueFactory.getInstance().setAutoLegacy(false);
        verifyAutoLegacyDeactive();
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(Object.class);

        assertThat(actual, isA(RandomStrategyObject.class));
    }

    @Test
    public void testGetProvider_withString_return_provider() {
        verifyAutoLegacyActive();
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(Integer.class);

        assertThat(actual, isA(RandomStrategyObject.class));
    }

    @Test
    public void testGetProvider_withStringNoAutoLegacy_return_null() {
        RandomValueFactory.getInstance().setAutoLegacy(false);
        verifyAutoLegacyDeactive();
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(Integer.class);

        assertThat(actual, nullValue());
    }

}