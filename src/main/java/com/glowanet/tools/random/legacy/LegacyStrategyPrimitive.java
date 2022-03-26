package com.glowanet.tools.random.legacy;

import org.apache.commons.lang3.RandomUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Creates random values for primitive types, using {@code RandomUtils}.
 *
 * @see RandomUtils
 */
public class LegacyStrategyPrimitive extends AbstractLegacyStrategy {

    @Override
    public Object next(Class<?> type) {
        Object result;
        if (Boolean.TYPE.equals(type)) {
            result = RandomUtils.nextBoolean();
        } else if (Integer.TYPE.equals(type)) {
            result = RandomUtils.nextInt();
        } else if (Double.TYPE.equals(type)) {
            result = RandomUtils.nextDouble();
        } else if (Float.TYPE.equals(type)) {
            result = RandomUtils.nextFloat();
        } else if (Long.TYPE.equals(type)) {
            result = RandomUtils.nextLong();
        } else if (Byte.TYPE.equals(type)) {
            result = RandomUtils.nextBytes(1);
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public List<Type> supportedTypes() {
        return List.of( //
                Boolean.class, Integer.class, Double.class, Float.class, Long.class, Byte.class //
        );
    }
}
