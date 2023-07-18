package io.bayrktlihn.template.util;

import io.bayrktlihn.template.calculator.interest.InterestCalculator;
import io.bayrktlihn.template.util.date.LocalDates;
import io.bayrktlihn.template.util.date.model.LocalDateFromTo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MyTests {

    @Test
    void test() {
        LocalDate start = LocalDates.create(2009, 12, 11);
        LocalDate today = LocalDates.create(2009, 12, 12);

        InterestRate i1 = InterestRate.createWithMonthlyInterestRate(new BigDecimal("2.5"));
        InterestRate i2 = InterestRate.createWithMonthlyInterestRate(new BigDecimal("2.5"));
        InterestRate i3 = InterestRate.createWithMonthlyInterestRate(new BigDecimal("1.95"));
        InterestRate i4 = InterestRate.createWithMonthlyInterestRate(new BigDecimal("1.5"));

        List<LocalDateFromToObject<InterestRate>> fromToList = new ArrayList<>();
        fromToList.add(new LocalDateFromToObject<>(LocalDateFromTo.createWithTo(LocalDates.create(2006, 4, 20)), i1));
        fromToList.add(new LocalDateFromToObject<>(LocalDateFromTo.create(LocalDates.create(2006, 4, 21), LocalDates.create(2009, 11, 18)), i2));
        fromToList.add(new LocalDateFromToObject<>(LocalDateFromTo.create(LocalDates.create(2009, 11, 19), LocalDates.create(2010, 10, 18)), i3));
        fromToList.add(new LocalDateFromToObject<>(LocalDateFromTo.createWithFrom(LocalDates.create(2010, 10, 18)), i4));


        List<LocalDateFromToObject> collect = fromToList.stream().filter(item -> {
            LocalDateFromTo fromTo = item.getFromTo();
            LocalDate to = fromTo.getTo() == null ? LocalDate.MAX : fromTo.getTo();
            LocalDate from = fromTo.getFrom() == null ? LocalDate.MIN : fromTo.getFrom();

            boolean c1 = to.isAfter(start);
            boolean c2 = from.isBefore(today);
            if (c1 && c2) {
                return true;
            }

            if (from.isAfter(start) && LocalDates.equalOrIsBefore(to, today)) {
                return true;
            }

            if (from.isBefore(today) && (to.isAfter(today))) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        int firstIndex = 0;
        int lastIndex = collect.size() - 1;
        collect.get(firstIndex).getFromTo().setFrom(start);
        collect.get(lastIndex).getFromTo().setTo(today);

        BigDecimal mainAmount = new BigDecimal("300");
        BigDecimal total = new BigDecimal("0");
        for (int i = 0; i < collect.size(); i++) {
            LocalDateFromToObject<InterestRate> localDateFromToObject = collect.get(i);
            LocalDateFromTo fromTo = localDateFromToObject.getFromTo();

            LocalDate from = i != firstIndex ? fromTo.getFrom().minus(1, ChronoUnit.DAYS) : fromTo.getFrom();

            InterestCalculator interestCalculator = new InterestCalculator(localDateFromToObject.getObject(), from, fromTo.getTo());
            total = total.add(interestCalculator.calculate(mainAmount));
        }

        System.out.println(total.setScale(2, RoundingMode.UP));


    }
}
