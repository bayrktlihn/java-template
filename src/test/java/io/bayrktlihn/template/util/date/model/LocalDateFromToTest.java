package io.bayrktlihn.template.util.date.model;

import io.bayrktlihn.template.util.date.LocalDates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class LocalDateFromToTest {

    @Test
    void createFail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LocalDate now = LocalDate.now();
            LocalDateFromTo.create(now, now);
        });
    }

    @Test
    void createNullNull(){
        LocalDateFromTo localDateFromTo = LocalDateFromTo.create(null, null);
        LocalDate from = localDateFromTo.getFrom();
        LocalDate to = localDateFromTo.getTo();
        Assertions.assertEquals(from, LocalDate.MIN);
        Assertions.assertEquals(to, LocalDate.MAX);
    }

}