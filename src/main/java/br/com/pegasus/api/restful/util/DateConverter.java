package br.com.pegasus.api.restful.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConverter {

    // Métodos com ZoneId padrão (systemDefault)
    public static Instant toInstant(LocalDate date) {
        if (date == null) throw new RuntimeException("LocalDate é null");
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public static Instant toInstant(LocalDateTime dateTime) {
        if (dateTime == null) throw new RuntimeException("LocalDateTime é null");
        return dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static Instant toInstant(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) throw new RuntimeException("OffsetDateTime é null");
        return offsetDateTime.toInstant();
    }

    public static Instant toInstant(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) throw new RuntimeException("ZonedDateTime é null");
        return zonedDateTime.toInstant();
    }

    public static LocalDate toLocalDate(Instant instant) {
        if (instant == null) throw new RuntimeException("Instant é null");
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        if (instant == null) throw new RuntimeException("Instant é null");
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static OffsetDateTime toOffsetDateTime(Instant instant) {
        if (instant == null) throw new RuntimeException("Instant é null");
        return instant.atOffset(ZoneId.systemDefault().getRules().getOffset(instant));
    }

    public static ZonedDateTime toZonedDateTime(Instant instant) {
        if (instant == null) throw new RuntimeException("Instant é null");
        return instant.atZone(ZoneId.systemDefault());
    }

    // Métodos com ZoneId passado por parâmetro
    public static Instant toInstant(LocalDate date, ZoneId zoneId) {
        if (date == null) throw new RuntimeException("LocalDate é null");
        if (zoneId == null) throw new RuntimeException("ZoneId é null");
        return date.atStartOfDay(zoneId).toInstant();
    }

    public static Instant toInstant(LocalDateTime dateTime, ZoneId zoneId) {
        if (dateTime == null) throw new RuntimeException("LocalDateTime é null");
        if (zoneId == null) throw new RuntimeException("ZoneId é null");
        return dateTime.atZone(zoneId).toInstant();
    }

    public static Instant toInstant(OffsetDateTime offsetDateTime, ZoneId zoneId) {
        if (offsetDateTime == null) throw new RuntimeException("OffsetDateTime é null");
        if (zoneId == null) throw new RuntimeException("ZoneId é null");
        // ZoneId não altera OffsetDateTime instant, mas incluído para assinatura uniforme
        return offsetDateTime.toInstant();
    }

    public static Instant toInstant(ZonedDateTime zonedDateTime, ZoneId zoneId) {
        if (zonedDateTime == null) throw new RuntimeException("ZonedDateTime é null");
        if (zoneId == null) throw new RuntimeException("ZoneId é null");
        // Pode alterar a zona, então converte para zoneId antes de Instant
        return zonedDateTime.withZoneSameInstant(zoneId).toInstant();
    }

    public static LocalDate toLocalDate(Instant instant, ZoneId zoneId) {
        if (instant == null) throw new RuntimeException("Instant é null");
        if (zoneId == null) throw new RuntimeException("ZoneId é null");
        return instant.atZone(zoneId).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Instant instant, ZoneId zoneId) {
        if (instant == null) throw new RuntimeException("Instant é null");
        if (zoneId == null) throw new RuntimeException("ZoneId é null");
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static OffsetDateTime toOffsetDateTime(Instant instant, ZoneId zoneId) {
        if (instant == null) throw new RuntimeException("Instant é null");
        if (zoneId == null) throw new RuntimeException("ZoneId é null");
        return instant.atOffset(zoneId.getRules().getOffset(instant));
    }

    public static ZonedDateTime toZonedDateTime(Instant instant, ZoneId zoneId) {
        if (instant == null) throw new RuntimeException("Instant é null");
        if (zoneId == null) throw new RuntimeException("ZoneId é null");
        return instant.atZone(zoneId);
    }
}



