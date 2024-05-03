package io.bayrktlihn.template.util.date.model;

import io.bayrktlihn.template.util.date.Dates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DateFromTo {
    private  Date from;
    private  Date to;

    public DateFromTo(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public boolean include(Date date){
        return from.equals(date) || to.equals(date) || (Dates.isAfter(date, from) && Dates.isBefore(date, to));
    }


    public static List<DateFromTo> createPeriodItems(Date from, Date to, Long periodValue, TemporalUnit periodValueUnit) {
        List<DateFromTo> result = Dates.createPeriodItems(from, to, periodValue, periodValueUnit, 0, periodValueUnit, (date, date2) -> new DateFromTo(date, date2));
        return result;
    }

    public static List<DateFromTo> createPeriodItemsYearly(Date from, Date to) {
        List<DateFromTo> result = Dates.createPeriodItemsYearly(from, to, (date, date2) -> new DateFromTo(date, date2));
        return result;
    }

    public static List<DateFromTo> createPeriodItemsYearlyNextFromEqualsCurrentTo(Date from, Date to) {
        List<DateFromTo> result = Dates.createPeriodItemsYearly(from, to, (date, date2) -> new DateFromTo(date, date2));

        if (result != null && result.size() > 1) {
            Date tmpTo = result.get(0).getTo();
            for (int i = 1; i < result.size(); i++) {
                result.get(i).setFrom((Date) tmpTo.clone());

                tmpTo = result.get(i).getTo();
            }
        }

        return result;


    }


}
