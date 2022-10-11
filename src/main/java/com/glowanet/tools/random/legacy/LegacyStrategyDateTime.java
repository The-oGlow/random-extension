package com.glowanet.tools.random.legacy;

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
public class LegacyStrategyDateTime extends AbstractLegacyStrategy {

    @Override
    public Object next(Class<?> type) {
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
        } else if (Duration.class.equals(type)) {
            result = Duration.of(
                    newRandom().nextInt(Instant.now().get(ChronoField.SECOND_OF_MINUTE)), ChronoUnit.SECONDS
            );
        } else if (Period.class.equals(type)) {
            result = Period.of( //
                    newRandom().nextInt(Instant.now().get(ChronoField.YEAR)),
                    newRandom().nextInt(Instant.now().get(ChronoField.MONTH_OF_YEAR)), //
                    newRandom().nextInt(Instant.now().get(ChronoField.DAY_OF_MONTH)) //
            );
        } else if (Instant.class.equals(type)) {
            result = Instant.now();
        } else {
            result = null;
        }
        return result;
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
