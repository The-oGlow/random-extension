package com.glowa_net.tools.random;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomValueObject<T> extends AbstractRandomValue<T> {

    public static final int COUNT_ALPHA = 5;

    RandomValueObject(Class<T> typeOfT) {
        super(typeOfT);
    }

    @Override
    public T randomValue() {
        throw new IllegalCallerException("Method is not allowed to call!");
    }

    public Object randomValue(Class<?> type) {
        return retrieveDefaultValueLegacy(type);
    }

    private Object retrieveDefaultValueLegacy(Class<?> type) {
        Object result;

        if (Boolean.class.equals(type)) {
            result = RandomUtils.nextBoolean();
        } else if (Number.class.equals(type)) {
            result = RandomUtils.nextInt();
        } else if (String.class.equals(type)) {
            result = RandomStringUtils.randomAlphanumeric(COUNT_ALPHA);
        } else {
            result = null;
        }

        if (result == null) {
            result = retrieveDefaultBig(type);
        }
        if (result == null) {
            result = retrieveDefaultPrimitive(type);
        }
        if (result == null) {
            result = retrieveDefaultTime(type);
        }
        return result;
    }


    private Object retrieveDefaultBig(Class<?> type) {
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

    private Object retrieveDefaultPrimitive(Class<?> type) {
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

    private Object retrieveDefaultTime(Class<?> type) {
        Object result;
        if (LocalDateTime.class.equals(type)) {
            result = LocalDateTime.now();
        } else if (LocalDate.class.equals(type)) {
            result = LocalDate.now();
        } else if (LocalTime.class.equals(type)) {
            result = LocalTime.now();
        } else if (Date.class.equals(type)) {
            result = Date.from(Instant.now());
        } else if (Time.class.equals(type)) {
            result = Date.from(Instant.now());
        } else {
            result = null;
        }
        return result;
    }
}
