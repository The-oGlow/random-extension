package com.glowanet.tools.random;

import java.lang.reflect.Type;
import java.util.List;

/**
 * A class representing all primitive types.
 */
public class Primitive {

    private Primitive() {
    }

    /**
     * @return list of all primitive java types
     */
    public static List<Type> typesOfPrimitive() {
        return List.of( //
                Boolean.class, Integer.class, Double.class, Float.class, Long.class, Character.class, Byte.class, //
                boolean.class, int.class, double.class, float.class, long.class, char.class, byte.class
        );
    }
}
