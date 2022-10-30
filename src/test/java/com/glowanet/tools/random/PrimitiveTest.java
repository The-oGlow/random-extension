package com.glowanet.tools.random;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

public class PrimitiveTest {

    private static final int NO_OF_PRIMITIVES = 14;

    @Test
    public void testTypesOfPrimitive() {
        List<Type> actual = Primitive.typesOfPrimitive();

        assertThat(actual, Matchers.notNullValue());
        assertThat(actual, hasSize(greaterThanOrEqualTo(NO_OF_PRIMITIVES)));
    }

    @Test
    public void testSize() {
        int actual = Primitive.size();

        assertThat(actual, greaterThanOrEqualTo(NO_OF_PRIMITIVES));
    }
}