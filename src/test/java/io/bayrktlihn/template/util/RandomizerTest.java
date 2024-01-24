package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class RandomizerTest {

    @Test
    void generateRandomByDateTime() {
        // 100_000 kişi aynı anda istek yaptıgını düşün hepsi farklı olmalı
        Set<String> items = new HashSet<>();
        int expectedDifferenceNumbers = 100_000;
        for (int i = 0; i < expectedDifferenceNumbers; i++) {
            String s = Randomizer.generateByDateTime();

            items.add(s);
        }

        int expectedLength = "yyyyMMddHHmmssSSS".length() + (999_999_999_999L + "").length();

        for (String item : items) {

            Assertions.assertEquals(expectedLength, item.length());
        }


    }
}