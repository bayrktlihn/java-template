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
        dailyInterestRate = dailyInterestRate.setScale(15, RoundingMode.HALF_UP);
        BigDecimal yearlyInterestRate = dailyInterestRate.multiply(new BigDecimal("365"));
        return createWithYearlyInterestRate(yearlyInterestRate);
    }


    public static InterestRate createWithMonthlyInterestRate(BigDecimal monthlyInterestRate) {
        monthlyInterestRate = monthlyInterestRate.setScale(15, RoundingMode.HALF_UP);
        BigDecimal yearlyInterestRate = monthlyInterestRate.multiply(new BigDecimal("12"));
        return createWithYearlyInterestRate(yearlyInterestRate);
    }

    public static InterestRate createWithYearlyInterestRate(BigDecimal yearlyInterestRate) {
        yearlyInterestRate = yearlyInterestRate.setScale(15, RoundingMode.HALF_UP);
        BigDecimal monthlyInterestRate = yearlyInterestRate.divide(new BigDecimal("12"), 15, RoundingMode.HALF_UP);
        BigDecimal dailyInsterestRate = yearlyInterestRate.divide(new BigDecimal("365"), 15, RoundingMode.HALF_UP);

        InterestRate interestRate = new InterestRate();
        interestRate.setYearly(yearlyInterestRate);
        interestRate.setMonthly(monthlyInterestRate);
        interestRate.setDaily(dailyInsterestRate);
        return interestRate;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InterestRate) {
            InterestRate interestRate = (InterestRate) obj;
            boolean equalsDailyInterestRate = interestRate.getDaily().setScale(2, RoundingMode.HALF_UP).equals(getDaily().setScale(2, RoundingMode.HALF_UP));
            boolean equalsYearlyInterestRate = interestRate.getYearly().setScale(2, RoundingMode.HALF_UP).equals(getYearly().setScale(2, RoundingMode.HALF_UP));
            boolean equalsMonthlyInterestRate = interestRate.getMonthly().setScale(2, RoundingMode.HALF_UP).equals(getMonthly().setScale(2, RoundingMode.HALF_UP));
            return equalsDailyInterestRate && equalsYearlyInterestRate && equalsMonthlyInterestRate;
        }
        return false;
    }
}
