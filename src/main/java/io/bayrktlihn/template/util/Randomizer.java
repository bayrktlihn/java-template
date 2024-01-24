package io.bayrktlihn.template.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public Randomizer() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String generateByDateTime() {
        return generateByDateTime(999_999_999_999L);
    }

    public static String generateByDateTime(long maxRandomNumber) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        long randomNumber = ThreadLocalRandom.current().nextLong(1L, maxRandomNumber);
        String format = "%0" + numberOfDigits(maxRandomNumber) + "d";
        return dateTimeFormatter.format(LocalDateTime.now()) + String.format(format, randomNumber);
    }

    private static int numberOfDigits(long number) {
        return (number + "").length();
    }
}
