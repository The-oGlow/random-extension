package com.glowanet.tools.random;

import org.junit.Test;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

public class DateTimeTest {

    private static final int NO_OF_PRIMITIVES_ALL = 12;

    @Test
    public void testAll() {
        List<Class<?>> actual = DateTime.all();

        assertThat(actual, hasSize(greaterThanOrEqualTo(NO_OF_PRIMITIVES_ALL)));
    }

    @Test
    public void testSize() {
        int actual = DateTime.size();

        assertThat(actual, greaterThanOrEqualTo(NO_OF_PRIMITIVES_ALL));
    }

    @Test
    public void testIsTemporalWithTemporalIsTrue() {
        boolean actual = DateTime.isTemporal(Instant.class);

        assertThat(actual, equalTo(true));
    }

    @Test
    public void testIsTemporalWithoutTemporalIsFalse() {
        boolean actual = DateTime.isTemporal(Integer.class);

        assertThat(actual, equalTo(false));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testIsTemporalWithNullIsFalse() {
        boolean actual = DateTime.isTemporal(null);

        assertThat(actual, equalTo(false));
    }

}