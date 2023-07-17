package io.bayrktlihn.template.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@ToString
public class InterestRate {
    private BigDecimal daily;
    private BigDecimal yearly;
    private BigDecimal monthly;


    private InterestRate() {

    }


    public static InterestRate createWithDailyInterestRate(BigDecimal dailyInterestRate) {
        InterestRate interestRate = new InterestRate();
        interestRate.setDaily(dailyInterestRate);
        interestRate.setYearly(dailyInterestRate.multiply(new BigDecimal("365")));
        interestRate.setMonthly(interestRate.getMonthly().divide(new BigDecimal("12"), 15, RoundingMode.HALF_UP));

        return interestRate;
    }

    public static InterestRate createWithYearlyInterestRate(BigDecimal yearlyInterestRate) {
        InterestRate interestRate = new InterestRate();
        interestRate.setYearly(yearlyInterestRate);
        interestRate.setMonthly(yearlyInterestRate.divide(new BigDecimal("12"), 15, RoundingMode.HALF_UP));
        interestRate.setDaily(yearlyInterestRate.divide(new BigDecimal("365"), 15, RoundingMode.HALF_UP));
        return interestRate;
    }

    public static InterestRate createWithMonthlyInterestRate(BigDecimal monthlyInterestRate) {
        InterestRate interestRate = new InterestRate();
        interestRate.setMonthly(monthlyInterestRate);
        interestRate.setYearly(monthlyInterestRate.multiply(new BigDecimal("12")));
        interestRate.setDaily(interestRate.getYearly().divide(new BigDecimal("365"), 15, RoundingMode.HALF_UP));
        return interestRate;
    }


}
