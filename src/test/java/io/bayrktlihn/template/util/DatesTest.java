package io.bayrktlihn.template.util;

import io.bayrktlihn.template.model.DayMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
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
