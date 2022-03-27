package com.glowanet.tools.random;

import com.glowanet.tools.random.legacy.LegacyStrategyPrimitive;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class LegacyValueFactoryTest {

    @Test
    public void testGetInstance_return_instance() {
        Object actual = LegacyValueFactory.getInstance();

        assertThat(actual, isA(LegacyValueFactory.class));
    }

    @Test
    public void testGetProviderLocation_return_string() {
        String actual = LegacyValueFactory.getInstance().getProviderLocation();

        assertThat(actual, not(emptyString()));
    }

    @Test
    public void testGetProvider_withNull_return_null() {
        ILegacyStrategy actual = LegacyValueFactory.getInstance().getProvider(null);

        assertThat(actual, nullValue());
    }

    @Test
    public void testGetProvider_withObject_return_null() {
        ILegacyStrategy actual = LegacyValueFactory.getInstance().getProvider(Object.class);

        assertThat(actual, nullValue());
    }

    @Test
    public void testGetProvider_withInteger_return_null() {
        ILegacyStrategy actual = LegacyValueFactory.getInstance().getProvider(Integer.class);

        assertThat(actual, isA(LegacyStrategyPrimitive.class));
    }

    @Test
    public void testGetProvider_withint_return_null() {
        ILegacyStrategy actual = LegacyValueFactory.getInstance().getProvider(int.class);

        assertThat(actual, isA(LegacyStrategyPrimitive.class));
    }

}