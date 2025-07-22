package br.com.pegasus.api.restful.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;

public class DateConverter {

    private static final ZoneId ZONE_ID;
    private static final ZoneRules ZONE_RULES;

    static {
        ZONE_ID = ZoneId.systemDefault();
        ZONE_RULES = ZONE_ID.getRules();
    }

    public static class InstantConverter {

        public static Instant to(LocalDate date) {
            if (date == null) {
                throw new RuntimeException("LocalDate é null");
            }
            return date.atStartOfDay(ZONE_ID).toInstant();
        }

        public static Instant to(LocalDateTime dateTime) {
            if (dateTime == null) {
                throw new RuntimeException("LocalDateTime é null");
            }
            return dateTime.atZone(ZONE_ID).toInstant();
        }

        public static Instant to(OffsetDateTime offsetDateTime) {
            if (offsetDateTime == null) {
                throw new RuntimeException("OffsetDateTime é null");
            }
            return offsetDateTime.toInstant();
        }

        public static Instant to(ZonedDateTime zonedDateTime) {
            if (zonedDateTime == null) {
                throw new RuntimeException("ZonedDateTime é null");
            }
            return zonedDateTime.toInstant();
        }

        public static Instant to(LocalDate date, ZoneId zoneId) {
            if (date == null) {
                throw new RuntimeException("LocalDate é null");
            }
            if (zoneId == null) {
                throw new RuntimeException("ZoneId é null");
            }
            return date.atStartOfDay(zoneId).toInstant();
        }

        public static Instant to(LocalDateTime dateTime, ZoneId zoneId) {
            if (dateTime == null) {
                throw new RuntimeException("LocalDateTime é null");
            }
            if (zoneId == null) {
                throw new RuntimeException("ZoneId é null");
            }
            return dateTime.atZone(zoneId).toInstant();
        }

        public static Instant to(ZonedDateTime zonedDateTime, ZoneId zoneId) {
            if (zonedDateTime == null) {
                throw new RuntimeException("ZonedDateTime é null");
            }
            if (zoneId == null) {
                throw new RuntimeException("ZoneId é null");
            }
            return zonedDateTime.withZoneSameInstant(zoneId).toInstant();
        }
    }

    public static class LocalDateConverter {
        public static LocalDate to(Instant instant, ZoneId zoneId) {
            if (instant == null) {
                throw new RuntimeException("Instant é null");
            }
            if (zoneId == null) {
                throw new RuntimeException("ZoneId é null");
            }
            return instant.atZone(zoneId).toLocalDate();
        }

        public static LocalDate to(Instant instant) {
            if (instant == null) {
                throw new RuntimeException("Instant é null");
            }
            return instant.atZone(ZONE_ID).toLocalDate();
        }
    }

    public static class LocalDateTimeConverter {

        public static LocalDateTime to(Instant instant) {
            if (instant == null) {
                throw new RuntimeException("Instant é null");
            }
            return instant.atZone(ZONE_ID).toLocalDateTime();
        }

        public static LocalDateTime to(Instant instant, ZoneId zoneId) {
            if (instant == null) {
                throw new RuntimeException("Instant é null");
            }
            if (zoneId == null) {
                throw new RuntimeException("ZoneId é null");
            }
            return instant.atZone(zoneId).toLocalDateTime();
        }

    }

    public static class OffsetDateTimeConverter {

        public static OffsetDateTime to(Instant instant) {
            if (instant == null) {
                throw new RuntimeException("Instant é null");
            }
            return instant.atOffset(ZONE_RULES.getOffset(instant));
        }

        public static OffsetDateTime to(Instant instant, ZoneId zoneId) {
            if (instant == null) {
                throw new RuntimeException("Instant é null");
            }
            if (zoneId == null) {
                throw new RuntimeException("ZoneId é null");
            }
            return instant.atOffset(ZONE_RULES.getOffset(instant));
        }

    }

    public static class ZonedDateTimeConverter {
        public static ZonedDateTime to(Instant instant) {
            if (instant == null) {
                throw new RuntimeException("Instant é null");
            }
            return instant.atZone(ZONE_ID);
        }

        public static ZonedDateTime to(Instant instant, ZoneId zoneId) {
            if (instant == null) {
                throw new RuntimeException("Instant é null");
            }
            if (zoneId == null) {
                throw new RuntimeException("ZoneId é null");
            }
            return instant.atZone(zoneId);
        }

    }

}



