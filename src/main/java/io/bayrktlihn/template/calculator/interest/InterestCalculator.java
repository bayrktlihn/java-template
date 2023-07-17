package io.bayrktlihn.template.calculator.interest;

import io.bayrktlihn.template.util.InterestRate;
import io.bayrktlihn.template.util.date.LocalDates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


public class InterestCalculator {

    private InterestRate interestRate;
    private LocalDate from;
    private LocalDate to;

    public InterestCalculator(InterestRate interestRate, LocalDate from, LocalDate to) {
        this.interestRate = interestRate;
        this.from = from;
        this.to = to;
    }

    public BigDecimal calculate(BigDecimal amount){
        Period period = LocalDates.period(from, to);
        int totalMonth = period.getYears() * 12 + period.getMonths();
        int totalDay = period.getDays();

        BigDecimal totalMonthlyRate = interestRate.getMonthly().multiply(new BigDecimal(String.valueOf(totalMonth)));
        BigDecimal totalDailyRate = interestRate.getDaily().multiply(new BigDecimal(String.valueOf(totalDay)));

        BigDecimal totalMonthlyInterest = amount.multiply(totalMonthlyRate.divide(new BigDecimal("100"), 15, BigDecimal.ROUND_UP));
        BigDecimal totalDailyInterest = amount.multiply(totalDailyRate.divide(new BigDecimal("100"), 15, BigDecimal.ROUND_UP));

        return totalMonthlyInterest.add(totalDailyInterest);
    }
}
