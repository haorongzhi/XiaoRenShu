package com.hrz.util;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;

/**
 * @author Administrator
 * @since 2019-12-29 20:19
 */
public class User implements Serializable {

    private String province;

    private String id;

    private LocalDate date;

    private String type;

    private String hours;

    private String monthKey;

    public String getMonthKey() {
        return monthKey;
    }

    public void setMonthKey() {
        //看当前日期是不是 第一个周一之前的几天。如果是把key设置为上个月的key，如果不是就是当前月的key

        String monthKey ="";
        if(0 < DateUtil.weekOfMonth(this.date)){
            monthKey = DateUtil.curMonthMonday4NextMonthMonday(this.date);
        }else{
            monthKey = DateUtil.preMonthMonday4NextMonthMonday(this.date);
        }
        this.monthKey = monthKey;




    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public User(String province, String id, LocalDate date, String type, String hours) {
        this.province = province;
        this.id = id;
        this.date = date;
        this.type = type;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "User{" +
                "province='" + province + '\'' +
                ", id='" + id + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", hours='" + hours + '\'' +
                ", monthKey='" + monthKey + '\'' +
                '}';
    }

    public static void main(String[] args) {
//        LocalDate firstMonday = LocalDate.parse("2019-12-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//        System.out.println(firstMonday.toString());
//        System.out.println(firstMonday.plusDays(7));
        WeekFields wfs= WeekFields.of(DayOfWeek.MONDAY, 4);
        int weekInt = LocalDate.parse("2019-12-09").get(wfs.weekOfMonth());
        System.out.println(weekInt);

    }
}
