package io.bayrktlihn.template.util;

import io.bayrktlihn.template.util.date.Dates;
import io.bayrktlihn.template.util.date.model.DateFromTo;
import io.bayrktlihn.template.util.date.model.DateFromToObject;
import io.bayrktlihn.template.util.date.model.DayMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class DatesTest {

    @Test
    void createFail() {
        Date date = Dates.create(1, 1, 1);
        System.out.println(date);
    }

    @Test
    void endOfPreviousDay() {
        Date date = Dates.createStartOfDay(1996, 3, 1);
        Date endOfPreviousDay = Dates.endOfPreviousDay(date);

        Assertions.assertEquals(Dates.createEndOfDay(1996, 2, 29), endOfPreviousDay);

    }

    @Test
    void maxYear() {
        System.out.println(Dates.maxYear());
    }

    @Test
    void years() {
        List<Integer> years = Dates.years(1995, 2024);
    }

    @Test
    void yearsDesc() {
        List<Integer> years = Dates.years(1995, 2024, false);
    }

    @Test
    void minYear() {
        System.out.println(Dates.minYear());
    }

    @Test
    void days() {
        Date endOfLastDayOfPreviousCurrentYear = Dates.create(1996, 2, 28);
        Date now = Dates.create(3996, 2, 28);


        long start = System.currentTimeMillis();
        int days = Dates.days(endOfLastDayOfPreviousCurrentYear, now);
        long end = System.currentTimeMillis();

        Assertions.assertTrue(1000 > (end - start));
//        Assertions.assertEquals(366 + 365 * 3, days);

    }

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
    void numberOfDaysInMonth() {
        int countOfDayInMonth = Dates.numberOfDaysInMonth(1996, 2);

        Assertions.assertEquals(29, countOfDayInMonth);
    }

    @Test
    void startOfFirstDayOfMonth() {
        Date date = Dates.startOfFirstDayOfMonth(1995, 10);

        Assertions.assertEquals("01.10.1995 00.00", Dates.toString(date, "dd.MM.YYYY HH.mm"));
    }

    @Test
    void numberOfDaysInYear() {
        int countOfDayInYear = Dates.numberOfDaysInYear(1900);

        Assertions.assertEquals(365, countOfDayInYear);
    }

    @Test
    void isLeapYear() {
        Assertions.assertFalse(Dates.isLeapYear(1900));
    }

    @Test
    void createPeriodItemsYearly_equalsValue_sizeMustBeZero() {
        Date from = Dates.createStartOfDay(2020, 10, 10);
        Date to = Dates.createStartOfDay(2020, 10, 10);

        List<DateFromTo> periodItemsYearly = Dates.createPeriodItemsYearly(from, to, DateFromTo::new);

        Assertions.assertEquals(0, periodItemsYearly.size());
    }


    @Test
    void createPeriodItemsYearly_fromToValid_createSuccessfully() {
        Date from = Dates.createStartOfDay(2020, 10, 10);
        Date to = Dates.createEndOfDay(2022, 10, 8);

        List<DateFromTo> periodItemsYearly = Dates.createPeriodItemsYearly(from, to, (date, date2) -> new DateFromTo(date, date2));

        Assertions.assertEquals(3, periodItemsYearly.size());


        for (int i = 0; i < periodItemsYearly.size(); i++) {
            DateFromTo dateFromTo = periodItemsYearly.get(i);

            if (i == 0) {
                Assertions.assertEquals(from, dateFromTo.getFrom());
                Assertions.assertEquals(Dates.createEndOfLastDayOfYear(2020), dateFromTo.getTo());
            }

            if (i == 1) {
                Assertions.assertEquals(Dates.createStartOfFirsDayOfYear(2021), dateFromTo.getFrom());
                Assertions.assertEquals(Dates.createEndOfLastDayOfYear(2021), dateFromTo.getTo());
            }

            if (i == 2) {
                Assertions.assertEquals(Dates.createStartOfFirsDayOfYear(2022), dateFromTo.getFrom());
                Assertions.assertEquals(dateFromTo.getTo(), dateFromTo.getTo());
            }

        }


    }

    @Test
    void createPeriodItemsYearly() {
        Date from = Dates.createStartOfDay(2020, 10, 10);
        Date to = Dates.createEndOfDay(2022, 10, 8);

        List<DateFromTo> periodItemsYearly = Dates.createPeriodItemsYearly(from, to, (date, date2) -> new DateFromTo(date, date2));


        if (periodItemsYearly != null && periodItemsYearly.size() > 1) {
            Date tmpTo = periodItemsYearly.get(0).getTo();
            for (int i = 1; i < periodItemsYearly.size(); i++) {
                periodItemsYearly.get(i).setFrom((Date) tmpTo.clone());

                tmpTo = periodItemsYearly.get(i).getTo();
            }
        }

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
    void plusDay() {
        Date date = Dates.createStartOfDay(1996, 12, 31);
        Date addedOneDay = Dates.plusDay(date, 1);
        Assertions.assertEquals(Dates.createStartOfDay(1997, 1, 1), addedOneDay);
    }

    @Test
    void create_withHourMinuteSecondWithoutMilliSecond() {
        Date date = Dates.create(1996, 2, 29, 16, 10, 10);
        System.out.println(date);

    }

    @Test
    void currentDateOrNextWorkDate() {
        String fileContent = ClassPathUtil.getFileContent("holidays_in_every_year_tr.json");

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
