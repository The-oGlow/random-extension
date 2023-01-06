package com.glowanet.tools.random;

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
import java.util.Date;
import java.util.List;

/**
 * Representing all types handling any temporal aspect.
 */
public class DateTime {

    /**
     * All types handling any temporal aspect.
     *
     * @see #all()
     */
    private static final List<Class<?>> TEMPORALS = List.of(
            Date.class, Time.class, Timestamp.class,
            LocalDateTime.class, LocalDate.class, LocalTime.class,
            ZonedDateTime.class, OffsetDateTime.class, OffsetTime.class,
            Duration.class, Period.class,
            Instant.class
    );

    private DateTime() {
        //nothing2do
    }

    /**
     * @return list of all temporal java types
     */
    public static List<Class<?>> all() {
        return List.copyOf(TEMPORALS);
    }

    /**
     * @return the amount of temporal types
     */
    public static int size() {
        return all().size();
    }

    /**
     * @param temporalClazz a java type
     *
     * @return true={@code temporalClazz} is a temporal type, else false
     */
    public static boolean isTemporal(Class<?> temporalClazz) {
        if (temporalClazz == null) {
            return false;
        } else {
            return TEMPORALS.contains(temporalClazz);
        }
    }
}
