package io.bayrktlihn.template.util;

import io.bayrktlihn.template.util.date.Dates;
import io.bayrktlihn.template.util.date.model.DateFromToObject;
import io.bayrktlihn.template.util.date.model.DayMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class DatesTest {

    @Test
    void startOfDay() {
        Date date = Dates.create(1995, 10, 4);
        String dateString = Dates.toString(date, "dd.MM.yyyy");
        Assertions.assertEquals("04.10.1995", dateString);
    }

    @Test
    void equalsWithoutTime() {
        Date date = Dates.createStartOfDay(1995, 10, 4);
        Date dateAfter12Hours = Date.from(date.toInstant().plus(12, ChronoUnit.HOURS));

        Assertions.assertTrue(Dates.equalsWithoutTime(date, dateAfter12Hours));
    }

    @Test
    void period() {
        Date birthDate = Dates.create(1995, 7, 14);
        Date endDate = Dates.createStartOfDay(2000, 8, 13);

        Period period = Dates.period(birthDate, endDate);

        Assertions.assertEquals(5, period.getYears());
        Assertions.assertEquals(0, period.getMonths());
        Assertions.assertEquals(30, period.getDays());
    }

    @Test
    void numberOfDaysInMonth(){
        int countOfDayInMonth = Dates.numberOfDaysInMonth(1996, 2);

        Assertions.assertEquals(29, countOfDayInMonth);
    }

    @Test
    void startOfFirstDayOfMonth(){
        Date date = Dates.startOfFirstDayOfMonth(1995, 10);

        Assertions.assertEquals("01.10.1995 00.00", Dates.toString(date, "dd.MM.YYYY HH.mm"));
    }

    @Test
    void numberOfDaysInYear(){
        int countOfDayInYear = Dates.numberOfDaysInYear(1900);

        Assertions.assertEquals(365, countOfDayInYear);
    }

    @Test
    void isLeapYear() {
        Assertions.assertFalse(Dates.isLeapYear(1900));
    }

    @Test
    void create() {
        Instant now = Instant.now();
        Instant addOneHours = now.plus(1, ChronoUnit.HOURS);

        Date from = Date.from(now);
        Date to = Date.from(addOneHours);

        List<DateFromToObject<String>> result = Dates.createPeriodItems(from, to, 15, ChronoUnit.MINUTES, 15, ChronoUnit.MINUTES, (date, date2) -> new DateFromToObject<>(date, date2, "alihan"));

        for (DateFromToObject<String> stringDateFromToObject : result) {
            System.out.println(Dates.toString(stringDateFromToObject.getFrom(), "dd.MM.yyyy HH.mm.ss"));
            System.out.println(Dates.toString(stringDateFromToObject.getTo(), "dd.MM.yyyy HH.mm.ss"));
            System.out.println(stringDateFromToObject.getObject());
            System.out.println();
        }
    }

    @Test
    void currentDateOrNextWorkDate() {

        Path path = PathUtil.getPathFromClasspath("holidays_in_every_year_tr.json");
        String fileContent = FileUtil.readAll(path.toFile());

        List<DayMonth> holidaysInEveryYear = JSON.parseList(fileContent, DayMonth.class);

        Date date = Dates.create(2021, 10, 29);
        Date fixedDate = Dates.currentDateOrNextWorkDate(date, true, true, holidaysInEveryYear);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fixedDate);

        Assertions.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
        Assertions.assertEquals(11, calendar.get(Calendar.MONTH) + 1);
        Assertions.assertEquals(2021, calendar.get(Calendar.YEAR));

    }

}
