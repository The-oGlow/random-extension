package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.Primitive;
import org.apache.commons.lang3.RandomUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates random values for primitive types, using {@code RandomUtils}.
 *
 * @see RandomUtils
 */
public class RandomStrategyPrimitive extends AbstractRandomStrategyByType {

    @Override
    public Object next(Class<Object> type) {
        Object result;
        if (Boolean.TYPE.equals(type)) {
            result = newRandom().nextBoolean();
        } else if (Integer.TYPE.equals(type)) {
            result = newRandom().nextInt();
        } else if (Double.TYPE.equals(type)) {
            result = newRandom().nextDouble();
        } else if (Float.TYPE.equals(type)) {
            result = newRandom().nextFloat();
        } else if (Long.TYPE.equals(type)) {
            result = newRandom().nextLong();
        } else if (Character.TYPE.equals(type)) {
            byte[] bytes = new byte[1];
            newRandom().nextBytes(bytes);
            result = Character.valueOf((char) bytes[0]);
        } else if (Byte.TYPE.equals(type)) {
            byte[] bytes = new byte[1];
            newRandom().nextBytes(bytes);
            result = bytes[0];
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public List<Type> supportedTypes() {
        List<Type> types = new ArrayList<>();
        types.add(Primitive.class);
        types.addAll(Primitive.typesOfPrimitive());
        return types;
    }
}
