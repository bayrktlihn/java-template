package io.bayrktlihn.template.util.date;

import io.bayrktlihn.template.util.date.model.DayMonth;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LocalDates {

    private LocalDates() throws InstantiationException {
        throw new InstantiationException();
    }


    public static LocalDate now() {
        return LocalDate.now();
    }

    public static LocalDate firstDayOfYear(int year) {
        return LocalDate.of(year, 1, 1);
    }

    public static LocalDate lastDayOfYear(int year) {
        return LocalDate.of(year, 12, 31);
    }

    public static int minYear() {
        return Year.MIN_VALUE;
    }

    public static int maxYear() {
        return Year.MAX_VALUE;
    }

    public static boolean afterOrIsEqual(LocalDate localDate, LocalDate otherLocalDate){
        return localDate.isEqual(otherLocalDate) || localDate.isAfter(otherLocalDate);
    }

    public static boolean beforeOrIsEqual(LocalDate localDate, LocalDate otherLocalDate){
        return localDate.isEqual(otherLocalDate) || localDate.isBefore(otherLocalDate);
    }

    public static LocalDate create(int year, int month, int dayOfMonth) {
        int minYear = minYear();
        if (maxYear() < year || minYear - 1 > year) {
            throw new IllegalArgumentException(String.format("Year must be between %s and %s", minYear, Year.MAX_VALUE));
        }

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(String.format("Month must be between %s and %s", 1, 12));
        }

        int maximumDayOfMonth = Dates.maximumDayOfMonth(year, month);

        if (dayOfMonth < 1 || dayOfMonth > maximumDayOfMonth) {
            throw new IllegalArgumentException(
                    String.format("Day of month must be between %s and %s", 1, maximumDayOfMonth));
        }
        return LocalDate.of(year, month, dayOfMonth);
    }

    public static Period period(LocalDate from, LocalDate to) {
        return Period.between(from, to);
    }

    public static LocalDate firstDayOfMonth(int year, int month) {
        return LocalDate.of(year, month, 1);
    }

    public static LocalDate firstDayOfMonth(LocalDate localDate) {
        return firstDayOfMonth(localDate.getYear(), localDate.getMonthValue());
//        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate lastDayOfMonth(int year, int month) {
        int maximumDayOfMonth = Dates.maximumDayOfMonth(year, month);
        return LocalDate.of(year, month, maximumDayOfMonth);
//        LocalDate firstDayOfMonth = firstDayOfMonth(year, month);
//        return firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate lastDayOfMonth(LocalDate localDate) {
        return lastDayOfMonth(localDate.getYear(), localDate.getMonthValue());
//        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate currentDateOrNextWorkDate(LocalDate localDate) {
        return currentDateOrNextWorkDate(localDate, true, true, new ArrayList<>());
    }

    public static LocalDate currentDateOrNextWorkDate(LocalDate localDate, boolean weekendIsHoliday) {
        return currentDateOrNextWorkDate(localDate, weekendIsHoliday, weekendIsHoliday, new ArrayList<>());
    }

    public static LocalDate currentDateOrNextWorkDate(LocalDate localDate, boolean weekendIsHoliday, List<DayMonth> holidaysInEveryYear) {
        return currentDateOrNextWorkDate(localDate, weekendIsHoliday, weekendIsHoliday, holidaysInEveryYear);
    }

    public static LocalDate currentDateOrNextWorkDate(LocalDate localDate, List<DayMonth> holidaysInEveryYear) {
        return currentDateOrNextWorkDate(localDate, true, true, holidaysInEveryYear);
    }

    public static LocalDate currentDateOrNextWorkDate(LocalDate localDate, boolean isSaturdayIsHoliday, boolean isSundayIsHoliday, List<DayMonth> holidaysInEveryYear) {
        if (isHoliday(localDate, isSaturdayIsHoliday, isSundayIsHoliday, holidaysInEveryYear)) {
            LocalDate addedOneDay = localDate.plus(1, ChronoUnit.DAYS);
            return currentDateOrNextWorkDate(addedOneDay, isSaturdayIsHoliday, isSundayIsHoliday, holidaysInEveryYear);
        }
        return localDate;
    }

    private static boolean isHoliday(LocalDate localDate, boolean isSaturdayIsHoliday, boolean isSundayIsHoliday, List<DayMonth> holidaysInYear) {

        DayOfWeek dayOfWeek = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));

        if (isSaturdayIsHoliday && dayOfWeek.equals(DayOfWeek.SATURDAY)) {
            return true;
        }

        if (isSundayIsHoliday && dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            return true;
        }

        int month = localDate.get(ChronoField.MONTH_OF_YEAR);
        int dayOfMonth = localDate.get(ChronoField.DAY_OF_MONTH);


        return holidaysInYear.stream().anyMatch(holidaysInYearItem -> holidaysInYearItem.getDayOfMonth() == dayOfMonth && holidaysInYearItem.getMonth() == month);
    }


}
