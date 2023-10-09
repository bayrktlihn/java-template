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
        BigDecimal monthlyInterestRate = yearlyInterestRate.divide(new BigDecimal("12"), 15, RoundingMode.HALF_UP);

        InterestRate interestRate = new InterestRate();
        interestRate.setDaily(dailyInterestRate);
        interestRate.setYearly(yearlyInterestRate);
        interestRate.setMonthly(monthlyInterestRate);

        return interestRate;
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

    public static InterestRate createWithMonthlyInterestRate(BigDecimal monthlyInterestRate) {
        monthlyInterestRate = monthlyInterestRate.setScale(15, RoundingMode.HALF_UP);
        BigDecimal yearlyInterestRate = monthlyInterestRate.multiply(new BigDecimal("12"));
        BigDecimal dailyInterestRate = yearlyInterestRate.divide(new BigDecimal("365"), 15, RoundingMode.HALF_UP);

        InterestRate interestRate = new InterestRate();
        interestRate.setMonthly(monthlyInterestRate);
        interestRate.setYearly(yearlyInterestRate);
        interestRate.setDaily(dailyInterestRate);
        return interestRate;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InterestRate) {
            InterestRate interestRate = (InterestRate) obj;
            return interestRate.getDaily().setScale(2, RoundingMode.HALF_UP).equals(getDaily().setScale(2, RoundingMode.HALF_UP)) && interestRate.getYearly().setScale(2, RoundingMode.HALF_UP).equals(getYearly().setScale(2, RoundingMode.HALF_UP)) && interestRate.getMonthly().setScale(2, RoundingMode.HALF_UP).equals(getMonthly().setScale(2, RoundingMode.HALF_UP));
        }
        return false;
    }
}
