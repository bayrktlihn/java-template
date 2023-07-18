package io.bayrktlihn.template.util.date;

import io.bayrktlihn.template.util.FileUtil;
import io.bayrktlihn.template.util.JSON;
import io.bayrktlihn.template.util.PathUtil;
import io.bayrktlihn.template.util.date.model.DayMonth;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalDatesTest {

    @Test
    void currentDateOrNextWorkDate2(){

        Path path = PathUtil.getPathFromClasspath("holidays_in_every_year_tr.json");
        String fileContent = FileUtil.readAll(path.toFile());

        List<DayMonth> holidaysInEveryYear = JSON.parseList(fileContent, DayMonth.class);


        LocalDate localDate = LocalDates.createLocalDate(2018, 10, 27);

        LocalDate nextLocalDate = LocalDates.currentDateOrNextWorkDate(localDate, true, holidaysInEveryYear);

        System.out.println(nextLocalDate);
    }

}