package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class InterestRateTest {


    @Test
    void createWithMonthlyInterestRate() {
        InterestRate withMonthlyInterestRate = InterestRate.createWithMonthlyInterestRate(new BigDecimal("137"));

        System.out.println(withMonthlyInterestRate);
    }

}