package com.glowanet.tools.random.impl;

import com.glowanet.reflect.Primitive;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * Creates random values for {@link Primitive}, using {@link RandomUtils}.
 *
 * @see Primitive
 * @see RandomUtils
 */
public class RandomStrategyPrimitive extends AbstractRandomStrategyByType {

    public static final List<Class<?>> SUPP_TYPES = Primitive.all();

    @SuppressWarnings({"java:S126", "UnusedAssignment"})
    @Override
    public <V> V next(Class<?> valueClazz) {
        Object result = null;
        if (isSupported(valueClazz)) {
            Class<?> objValueClazz = null;
            if (Primitive.isPrimitive(valueClazz)) {
                objValueClazz = Primitive.primToObj(valueClazz);
            } else {
                objValueClazz = valueClazz;
            }
            if (Boolean.class.equals(objValueClazz)) {
                result = newRandom().nextBoolean();
            } else if (Integer.class.equals(objValueClazz)) {
                result = newRandom().nextInt();
            } else if (Double.class.equals(objValueClazz)) {
                result = newRandom().nextDouble();
            } else if (Float.class.equals(objValueClazz)) {
                result = newRandom().nextFloat();
            } else if (Long.class.equals(objValueClazz)) {
                result = newRandom().nextLong();
            } else if (Character.class.equals(objValueClazz)) {
                byte[] bytes = new byte[1];
                newRandom().nextBytes(bytes);
                result = (char) bytes[0];
            } else if (Byte.class.equals(objValueClazz)) {
                byte[] bytes = new byte[1];
                newRandom().nextBytes(bytes);
                result = bytes[0];
            }
        }
        //noinspection unchecked
        return (V) result;
    }

    @Override
    public List<Class<?>> supportedTypes() {
        return SUPP_TYPES;
    }
}
