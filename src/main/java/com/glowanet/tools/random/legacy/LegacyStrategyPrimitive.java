package com.glowanet.tools.random.legacy;

import com.glowanet.tools.random.Primitive;
import org.apache.commons.lang3.RandomUtils;

import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates random values for primitive types, using {@code RandomUtils}.
 *
 * @see RandomUtils
 */
public class LegacyStrategyPrimitive extends AbstractLegacyStrategy {

    private final SecureRandom random = new SecureRandom();

    @Override
    public Object next(Class<?> type) {
        Object result;
        if (Boolean.TYPE.equals(type)) {
            result = random.nextBoolean();
        } else if (Integer.TYPE.equals(type)) {
            result = random.nextInt();
        } else if (Double.TYPE.equals(type)) {
            result = random.nextDouble();
        } else if (Float.TYPE.equals(type)) {
            result = random.nextFloat();
        } else if (Long.TYPE.equals(type)) {
            result = random.nextLong();
        } else if (Character.TYPE.equals(type)) {
            byte[] bytes = new byte[1];
            random.nextBytes(bytes);
            //noinspection UnnecessaryBoxing
            result = Character.valueOf((char) bytes[0]);
        } else if (Byte.TYPE.equals(type)) {
            byte[] bytes = new byte[1];
            random.nextBytes(bytes);
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
