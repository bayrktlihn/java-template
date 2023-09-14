package io.bayrktlihn.template.util.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

class LocalDatesTest {

    @Test
    void workDays() {
        long l = LocalDates.workDays(LocalDate.of(2023, 9, 14), LocalDate.of(2023, 9, 25));
        System.out.println(l);
    }

    @Test
    void numberOfDays() {
        LocalDate localDate = LocalDate.of(2023, 9, 16);
        LocalDate addedLocalDate = localDate.plus(750, ChronoUnit.DAYS);
        long l = LocalDates.numberOfDays(localDate, addedLocalDate);
        System.out.println(l);
    }

    @Test
    void nextSaturday() {
        LocalDate localDate = LocalDate.of(2023, 9, 16);
        LocalDate nextDate = LocalDates.nextSaturday(localDate);
        System.out.println(nextDate);
    }

    @Test
    void previousSaturday() {
        LocalDate localDate = LocalDate.of(2023, 9, 2);
        LocalDate nextDate = LocalDates.previousSaturday(localDate);
        System.out.println(nextDate);
    }

    @Test
    void create() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LocalDates.create(Year.MAX_VALUE + 1, 12, 30);
        });
    }

    @Test
    void currentDateOrNextWorkDate() {

        LocalDate localDate = LocalDates.create(2024, 10, 28);

        ArrayList<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDates.create(2024,10, 28));
        holidays.add(LocalDates.create(2024,10, 30));

        LocalDate nextLocalDate = LocalDates.currentDateOrNextWorkDate(localDate, true, LocalDates.turkishHolidays(), holidays);

        System.out.println(nextLocalDate);
    }

}