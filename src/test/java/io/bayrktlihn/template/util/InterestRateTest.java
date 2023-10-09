package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

class InterestRateTest {


    @Test
    void createWithMonthlyInterestRate() {
        InterestRate withMonthlyInterestRate = InterestRate.createWithMonthlyInterestRate(new BigDecimal("365").divide(new BigDecimal("12"), 15, RoundingMode.HALF_UP));

        System.out.println(withMonthlyInterestRate);
    }

    @Test
    void createWithDailyInterestRate() {
        InterestRate withDailyInterestRate = InterestRate.createWithDailyInterestRate(new BigDecimal("1"));

        System.out.println(withDailyInterestRate);
    }

    @Test
    void create() {
        InterestRate withYearlyInterestRate = InterestRate.createWithYearlyInterestRate(new BigDecimal("365"));

        System.out.println(withYearlyInterestRate);
    }

    @Test
    void equal(){
        InterestRate withDailyInterestRate = InterestRate.createWithDailyInterestRate(new BigDecimal("1"));
        InterestRate withMonthlyInterestRate = InterestRate.createWithMonthlyInterestRate(new BigDecimal("30.416666666666667"));
        InterestRate withYearlyInterestRate = InterestRate.createWithYearlyInterestRate(new BigDecimal("365"));

        Assertions.assertEquals(withDailyInterestRate, withMonthlyInterestRate);
        Assertions.assertEquals(withDailyInterestRate, withYearlyInterestRate);

    }

}