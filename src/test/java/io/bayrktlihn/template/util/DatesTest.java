package io.bayrktlihn.template.util;

import io.bayrktlihn.template.model.DateFromToObject;
import io.bayrktlihn.template.model.DayMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class DatesTest {

    @Test
    void startOfDay() {
        Date date = Dates.createDate(1995, 10, 4);
        String dateString = Dates.toString(date, "dd.MM.yyyy");
        Assertions.assertEquals("04.10.1995", dateString);
    }

    @Test
    void equalsWithoutTime(){
        Date date = Dates.createStarOfDayDate(1995, 10, 4);
        Date dateAfter12Hours = Date.from(date.toInstant().plus(12, ChronoUnit.HOURS));

        Assertions.assertTrue(Dates.equalsWithoutTime(date, dateAfter12Hours));
    }

    @Test
    void create(){
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

        Date date = Dates.createDate(2021, 10, 29);
        Date fixedDate = Dates.currentDateOrNextWorkDate(date, true, true, holidaysInEveryYear);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fixedDate);

        Assertions.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
        Assertions.assertEquals(11, calendar.get(Calendar.MONTH) + 1);
        Assertions.assertEquals(2021, calendar.get(Calendar.YEAR));

    }

}
