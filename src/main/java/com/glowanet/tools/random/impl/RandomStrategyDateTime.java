package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.DateTime;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * Creates random value for a bunch of date and time types.
 */
public class RandomStrategyDateTime extends AbstractRandomStrategyByType {

    public static final List<Class<?>> SUPP_TYPES = DateTime.all();

    @Override
    public <V> V next(Class<?> valueClazz) {
        Object result = null;
        if (isSupported(valueClazz)) {
            result = nextPrimitives(valueClazz);
            if (result == null) {
                result = nextInstant(valueClazz);
            }
            if (result == null) {
                result = nextLocal(valueClazz);
            }
            if (result == null) {
                result = nextZone(valueClazz);
            }
            if (result == null) {
                result = nextSql(valueClazz);
            }
            if (result == null) {
                result = nextInterval(valueClazz);
            }
        }
        //noinspection unchecked
        return (V) result;
    }

    protected Object nextPrimitives(Class<?> valueClazz) {
        Object result;
        if (Date.class.equals(valueClazz)) {
            result = Date.from(Instant.now());
        } else {
            result = null;
        }
        return result;
    }

    protected Object nextLocal(Class<?> valueClazz) {
        Object result;
        if (LocalDateTime.class.equals(valueClazz)) {
            result = LocalDateTime.now();
        } else if (LocalDate.class.equals(valueClazz)) {
            result = LocalDate.now();
        } else if (LocalTime.class.equals(valueClazz)) {
            result = LocalTime.now();
        } else {
            result = null;
        }
        return result;
    }

    protected Object nextSql(Class<?> valueClazz) {
        Object result;
        if (Time.class.equals(valueClazz)) {
            result = Time.valueOf(LocalTime.now());
        } else if (Timestamp.class.equals(valueClazz)) {
            result = Timestamp.valueOf(LocalDateTime.now());
        } else {
            result = null;
        }
        return result;
    }

    protected Object nextZone(Class<?> valueClazz) {
        Object result;
        if (ZonedDateTime.class.equals(valueClazz)) {
            result = ZonedDateTime.now();
        } else if (OffsetDateTime.class.equals(valueClazz)) {
            result = OffsetDateTime.now();
        } else if (OffsetTime.class.equals(valueClazz)) {
            result = OffsetTime.now();
        } else {
            result = null;
        }
        return result;
    }

    protected Object nextInterval(Class<?> valueClazz) {
        Object result;
        ZonedDateTime zdT = ZonedDateTime.now();
        if (Duration.class.equals(valueClazz)) {
            result = Duration.of(newRandom().nextInt(zdT.getSecond()), ChronoUnit.SECONDS);
        } else if (Period.class.equals(valueClazz)) {
            result = Period.of(zdT.getYear(), zdT.getMonthValue(), zdT.getDayOfMonth());
        } else {
            result = null;
        }
        return result;
    }

    protected Object nextInstant(Class<?> valueClazz) {
        Object result;
        if (Instant.class.equals(valueClazz)) {
            result = Instant.now();
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public List<Class<?>> supportedTypes() {
        return SUPP_TYPES;
    }
}
