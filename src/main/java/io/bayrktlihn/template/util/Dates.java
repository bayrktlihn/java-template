package io.bayrktlihn.template.util;

import io.bayrktlihn.template.model.DayMonth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Dates {

    private Dates() throws InstantiationException {
        throw new InstantiationException();
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

    private static boolean isHoliday(Date date, boolean isSaturdayIsHoliday, boolean isSundayIsHoliday, List<DayMonth> holidaysInYear) {
        Calendar calendar = Calendar.getInstance();

        if (isSaturdayIsHoliday && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return true;
        }

        if (isSundayIsHoliday && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }

        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        return holidaysInYear.stream().filter(holidaysInYearItem -> holidaysInYearItem.getDayOfMonth() == dayOfMonth && holidaysInYearItem.getMonth() == month).findFirst().isPresent();
    }

}
