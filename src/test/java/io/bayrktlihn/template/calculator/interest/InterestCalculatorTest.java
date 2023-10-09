package io.bayrktlihn.template.calculator.interest;

import io.bayrktlihn.template.util.InterestRate;
import io.bayrktlihn.template.util.InterestRateImpl;
import io.bayrktlihn.template.util.date.LocalDates;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class InterestCalculatorTest {

    @Test
    void calculate() {
        InterestRate interestRate = InterestRateImpl.createWithMonthlyInterestRate(new BigDecimal("2.5"));
        LocalDate from = LocalDates.create(2019, 7, 1);

        LocalDate to = from.plus(1, ChronoUnit.YEARS);
        to = to.plus(24, ChronoUnit.MONTHS);
        to = to.plus(24, ChronoUnit.DAYS);

        InterestCalculator interestCalculator = new InterestCalculator(interestRate, from, to);


        BigDecimal calculated = interestCalculator.calculate(new BigDecimal("100"));

        System.out.println(calculated);


    }

}