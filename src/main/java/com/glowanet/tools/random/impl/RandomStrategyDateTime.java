package com.glowanet.tools.random.impl;

import java.lang.reflect.Type;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * Creates random value for a bunch of date and time types.
 */
public class RandomStrategyDateTime extends AbstractRandomStrategyByType {

    @Override
    public <T> T next(Class<?> valueClazz) {
        Object result;
        if (LocalDateTime.class.equals(valueClazz)) {
            result = LocalDateTime.now();
        } else if (LocalDate.class.equals(valueClazz)) {
            result = LocalDate.now();
        } else if (LocalTime.class.equals(valueClazz)) {
            result = LocalTime.now();
        } else if (Date.class.equals(valueClazz)) {
            result = Date.from(Instant.now());
        } else if (Time.class.equals(valueClazz)) {
            result = Date.from(Instant.now());
        } else if (Duration.class.equals(valueClazz)) {
            result = Duration.of(
                    newRandom().nextInt(Instant.now().get(ChronoField.SECOND_OF_MINUTE)), ChronoUnit.SECONDS
            );
        } else if (Period.class.equals(valueClazz)) {
            result = Period.of( //
                    newRandom().nextInt(Instant.now().get(ChronoField.YEAR)),
                    newRandom().nextInt(Instant.now().get(ChronoField.MONTH_OF_YEAR)), //
                    newRandom().nextInt(Instant.now().get(ChronoField.DAY_OF_MONTH)) //
            );
        } else if (Instant.class.equals(valueClazz)) {
            result = Instant.now();
        } else {
            result = null;
        }
        //noinspection unchecked
        return (T) result;
    }

    @Override
    public List<Type> supportedTypes() {
        return List.of( //
                LocalDateTime.class, LocalDate.class, LocalTime.class, //
                Date.class, Time.class, //
                Duration.class, Period.class, Instant.class //
        );
    }
}
