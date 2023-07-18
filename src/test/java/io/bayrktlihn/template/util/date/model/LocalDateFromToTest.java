package io.bayrktlihn.template.util.date.model;

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

}