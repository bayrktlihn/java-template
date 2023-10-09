package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class InterestRateImplTest {


    @Test
    void createWithMonthlyInterestRate() {
        InterestRate withMonthlyInterestRateImpl = InterestRateImpl.createWithMonthlyInterestRate(new BigDecimal("30"));

        System.out.println(withMonthlyInterestRateImpl);
    }

    @Test
    void createWithDailyInterestRate() {
        InterestRate withDailyInterestRateImpl = InterestRateImpl.createWithDailyInterestRate(new BigDecimal("1"));

        System.out.println(withDailyInterestRateImpl);
    }

    @Test
    void create() {
        InterestRate withYearlyInterestRateImpl = InterestRateImpl.createWithYearlyInterestRate(new BigDecimal("365"));

        System.out.println(withYearlyInterestRateImpl);
    }

    @Test
    void equal() {
        InterestRate withDailyInterestRateImpl = InterestRateImpl.createWithDailyInterestRate(new BigDecimal("1"));
        InterestRate withMonthlyInterestRateImpl = InterestRateImpl.createWithMonthlyInterestRate(new BigDecimal("30.416666666666667"));
        InterestRate withYearlyInterestRateImpl = InterestRateImpl.createWithYearlyInterestRate(new BigDecimal("365"));

        Assertions.assertEquals(withDailyInterestRateImpl, withMonthlyInterestRateImpl);
        Assertions.assertEquals(withDailyInterestRateImpl, withYearlyInterestRateImpl);

    }

}