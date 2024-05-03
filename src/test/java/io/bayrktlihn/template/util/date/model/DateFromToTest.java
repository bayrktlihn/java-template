package io.bayrktlihn.template.util.date.model;

import io.bayrktlihn.template.util.date.Dates;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DateFromToTest {

    @Test
    void test() {
        Date start = Dates.createStartOfDay(1996, 2, 28);
        Date end = Dates.createEndOfDay(2000, 2, 28);


        Map<Integer, BigDecimal> yearlyAmounts = new HashMap<>();
        yearlyAmounts.put(1996, new BigDecimal("1"));
        yearlyAmounts.put(1997, new BigDecimal("10"));
        yearlyAmounts.put(1998, new BigDecimal("100"));
        yearlyAmounts.put(1999, new BigDecimal("1000"));
        yearlyAmounts.put(2000, new BigDecimal("10000"));

        List<DateFromTo> periodItemsYearlyNextFromEqualsCurrentTo = DateFromTo.createPeriodItemsYearlyNextFromEqualsCurrentTo(start, end);


        BigDecimal totalAmount = periodItemsYearlyNextFromEqualsCurrentTo.stream().map(item -> {

            Date itemTo = item.getTo();
            int year = Dates.getYear(itemTo);


            int days = Dates.days(item.getFrom(), item.getTo());

            System.out.println(days);

            BigDecimal yearlyAmount = yearlyAmounts.get(year);


            BigDecimal multiply = yearlyAmount.multiply(new BigDecimal(String.valueOf(days)));
            System.out.println(multiply);
            System.out.println();
            return multiply;


        }).reduce(BigDecimal.ZERO, (acc, item) -> acc.add(item));

        System.out.println(totalAmount);


    }

}
