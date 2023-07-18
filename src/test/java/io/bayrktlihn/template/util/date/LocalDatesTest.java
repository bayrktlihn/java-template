package io.bayrktlihn.template.util.date;

import io.bayrktlihn.template.util.FileUtil;
import io.bayrktlihn.template.util.JSON;
import io.bayrktlihn.template.util.PathUtil;
import io.bayrktlihn.template.util.date.model.DayMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

class LocalDatesTest {

    @Test
    void create() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LocalDates.create(Year.MAX_VALUE + 1, 12, 30);
        });
    }

    @Test
    void currentDateOrNextWorkDate() {

        Path path = PathUtil.getPathFromClasspath("holidays_in_every_year_tr.json");
        String fileContent = FileUtil.readAll(path.toFile());

        List<DayMonth> holidaysInEveryYear = JSON.parseList(fileContent, DayMonth.class);


        LocalDate localDate = LocalDates.create(2018, 10, 27);

        LocalDate nextLocalDate = LocalDates.currentDateOrNextWorkDate(localDate, true, holidaysInEveryYear);

        System.out.println(nextLocalDate);
    }

}