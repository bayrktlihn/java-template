package io.bayrktlihn.template.calculator.interest;

import io.bayrktlihn.template.util.InterestRate;
import io.bayrktlihn.template.util.date.LocalDates;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class InterestCalculatorTest {

    @Test
    void calculate() {
        InterestRate interestRate = InterestRate.createWithMonthlyInterestRate(new BigDecimal("2.5"));
        LocalDate from = LocalDates.createLocalDate(2019, 7, 1);
        LocalDate to = LocalDates.createLocalDate(2019, 9, 29);
        LocalDate toIter = to.minus(6, ChronoUnit.DAYS);

        while(toIter.isBefore(to)){
            InterestCalculator interestCalculator = new InterestCalculator(interestRate, from, toIter);

            BigDecimal amount = new BigDecimal("3131");
            BigDecimal calculated = interestCalculator.calculate(amount);
            System.out.println(calculated.add(amount));

            toIter = toIter.plus(1, ChronoUnit.DAYS);
        }


    }

}