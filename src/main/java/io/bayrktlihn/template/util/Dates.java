package io.bayrktlihn.template.util;

import io.bayrktlihn.template.model.DayMonth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

public class Dates {

    private Dates() throws InstantiationException {
        throw new InstantiationException();
    }

    public static Date starOfDayOfToday(){
        return startOfDay(new Date());
    }

    public static Date endOfDayOfToday(){
        return endOfDay(new Date());
    }

    public static Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    public static <T> List<T> createPeriodItems(Date from, Date to, long periodValue, TemporalUnit periodUnit, long delayValue, TemporalUnit delayUnit, BiFunction<Date, Date, T> toBeGenerateObject) {
        List<T> result = new ArrayList<>();

        Instant periodEndInstant = from.toInstant().plus(periodValue, periodUnit);

        Date periodStart = from;
        Date periodEnd = Date.from(periodEndInstant);

        while (periodEnd.compareTo(to) <= 0) {
            T t = toBeGenerateObject.apply(periodStart, periodEnd);
            result.add(t);

            Instant periodStartInstant = periodEnd.toInstant().plus(delayValue, delayUnit);
            periodEndInstant = periodStartInstant.plus(periodValue, periodUnit);

            periodStart = Date.from(periodStartInstant);
            periodEnd = Date.from(periodEndInstant);
        }

        return result;


    }

    public static Date createDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();

        int maximumYear = calendar.getActualMaximum(Calendar.YEAR);
        if (year < 0 || year > maximumYear) {
            throw new IllegalArgumentException(String.format("Year must be between %s and %s", 1, maximumYear));
        }
        calendar.set(Calendar.YEAR, year);

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(String.format("Month must be between %s and %s", 1, 12));
        }
        calendar.set(Calendar.MONTH, month - 1);

        int maximumDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (dayOfMonth < 1 || dayOfMonth > maximumDayOfMonth) {
            throw new IllegalArgumentException(
                    String.format("Day of month must be between %s and %s", 1, maximumDayOfMonth));
        }
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return calendar.getTime();

    }

    public static Date currentDateOrNextWorkDate(Date date, boolean isSaturdayIsHoliday, boolean isSundayIsHoliday, List<DayMonth> holidaysInEveryYear) {
        if (isHoliday(date, isSaturdayIsHoliday, isSundayIsHoliday, holidaysInEveryYear)) {
            Instant addedOneDay = date.toInstant().plus(1, ChronoUnit.DAYS);
            return currentDateOrNextWorkDate(Date.from(addedOneDay), isSaturdayIsHoliday, isSundayIsHoliday, holidaysInEveryYear);
        }
        return date;
    }


    public static Date createStarOfDayDate(int year, int month, int dayOfMonth) {
        Date date = createDate(year, month, dayOfMonth);
        return startOfDay(date);
    }

    public static Date createEndOfDayDate(int year, int month, int dayOfMonth) {
        Date date = createDate(year, month, dayOfMonth);
        return endOfDay(date);
    }

    public static String toString(Date date, String pattern) {
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static boolean equalsWithoutTime(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(d1);
        c2.setTime(d2);

        int c1Year = c1.get(Calendar.YEAR);
        int c2Year = c2.get(Calendar.YEAR);
        if (c1Year != c2Year) {
            return false;
        }

        int c1Month = c1.get(Calendar.MONTH);
        int c2Month = c2.get(Calendar.MONTH);
        if (c1Month != c2Month) {
            return false;
        }

        int c1DayOfMonth = c1.get(Calendar.DAY_OF_MONTH);
        int c2DayOfMonth = c2.get(Calendar.DAY_OF_MONTH);
        if (c1DayOfMonth != c2DayOfMonth) {
            return false;
        }
        return true;
    }

    private static boolean isHoliday(Date date, boolean isSaturdayIsHoliday, boolean isSundayIsHoliday, List<DayMonth> holidaysInYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (isSaturdayIsHoliday && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return true;
        }

        if (isSundayIsHoliday && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }

        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        return holidaysInYear.stream().anyMatch(holidaysInYearItem -> holidaysInYearItem.getDayOfMonth() == dayOfMonth && holidaysInYearItem.getMonth() == month);
    }

}
