package com.glowanet.tools.random.legacy;

import org.apache.commons.lang3.RandomUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Creates random value for type {@code BigDecimal}.
 *
 * @see BigDecimal
 */
public class LegacyStrategyBigDecimal extends AbstractLegacyStrategy {

    @Override
    public Object next(Class<?> type) {
        Object result;
        if (BigInteger.class.equals(type)) {
            result = BigInteger.valueOf(RandomUtils.nextInt());
        } else if (BigDecimal.class.equals(type)) {
            result = BigDecimal.valueOf(RandomUtils.nextInt());
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public List<Type> supportedTypes() {
        return List.of( //
                BigDecimal.class, BigInteger.class //
        );
    }
}
