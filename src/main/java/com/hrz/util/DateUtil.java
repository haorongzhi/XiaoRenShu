package com.hrz.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;

/**
 * @author Administrator
 * @since 2020-01-01 13:50
 */
public class DateUtil {
    /**
     * 判断当前日期是当前月的第几周
     */
    public static int weekOfMonth(LocalDate date){
        WeekFields wfs= WeekFields.of(DayOfWeek.MONDAY, 4);
        int weekInt = date.get(wfs.weekOfMonth());
        return weekInt;
    }

    /**
     * 获取当前日期所在月的第一个周一的日期
     */
    public static LocalDate firstMonday(LocalDate date){
        LocalDate firstMonday = date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        return  firstMonday;
    }

    /**
     * 获取当前日期所在月的第二个周一的日期
     */
    public static LocalDate secondMonday(LocalDate date){
       return firstMonday(date).plusDays(7);
    }
    /**
     * 获取当前日期所在月的第三个周一的日期
     */
    public static LocalDate thirdMonday(LocalDate date){
        return firstMonday(date).plusDays(14);
    }
    /**
     * 获取当前日期所在月的第四个周一的日期
     */
    public static LocalDate fourMonday(LocalDate date){
        return firstMonday(date).plusDays(21);
    }

    /**
     * 获取当前日期所在月下个月的第一个周一的日期
     */
    public static LocalDate firstMondayNextMonth(LocalDate date){

        LocalDate firstMonday = date.plusMonths(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        return  firstMonday;
    }

    /**
     * 当前这个月的第一个周一 到 下个月周一 作为key
     */
    public static String curMonthMonday4NextMonthMonday(LocalDate date){
        LocalDate firstMonday = firstMonday(date);
        LocalDate nextMonthFirstMonday = firstMondayNextMonth(date).minusDays(1);
        return  firstMonday.toString() + "-" + nextMonthFirstMonday.toString();
    }

    /**
     * 当前这个月的   上个月第一个周一 到 这个月第一个周一 作为key
     */
    public static String preMonthMonday4NextMonthMonday(LocalDate date){
        LocalDate firstMonday = firstMonday(date).minusDays(1);
        LocalDate preMonthFirstMonday = firstMonday(date.minusMonths(1));
        return  preMonthFirstMonday + "-" + firstMonday.toString();
    }

    /**
     * 获取当前日期所在的月的第一周周一-周日的时间
     */
    public static String firstMonday2Sunday(LocalDate date){
        LocalDate first = firstMonday(date);
        LocalDate sunday = first.plusDays(6);
        return first.toString() + "-" +sunday.toString();
    }
    /**
     * 获取当前日期所在的月的第二周周一-周日的时间
     */
    public static String secondMonday2Sunday(LocalDate date){
        LocalDate first = secondMonday(date);
        LocalDate sunday = first.plusDays(6);
        return first.toString() + "-" +sunday.toString();
    }
    /**
     * 获取当前日期所在的月的第三周周一-周日的时间
     */
    public static String thirdMonday2Sunday(LocalDate date){
        LocalDate first = thirdMonday(date);
        LocalDate sunday = first.plusDays(6);
        return first.toString() + "-" +sunday.toString();
    }
    /**
     * 获取当前日期所在的月的第四周周一-周日的时间
     */
    public static String fourMonday2Sunday(LocalDate date){
        LocalDate first = fourMonday(date);
        LocalDate sunday = first.plusDays(6);
        return first.toString() + "-" +sunday.toString();
    }
    /**
     * 判断该天是不是同一个月，
     */


}
