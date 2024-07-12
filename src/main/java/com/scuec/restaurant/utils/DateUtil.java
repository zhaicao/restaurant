package com.scuec.restaurant.utils;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {
    private static final String datePattern = "yyyy-MM-dd";
    private static final String fullDatePattern = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(datePattern);
    private static final DateTimeFormatter fullDateFormat = DateTimeFormatter.ofPattern(fullDatePattern);

    public static List<String> getDateList(String startDate, int gap) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < gap; i++) {
            LocalDate newDate = LocalDate.parse(startDate, dateFormat).plusDays(i);
            String dateString = dateFormat.format(newDate);
            res.add(dateString);
        }
        return res;
    }

    public static List<String> getDateList(String startDate, String endDate) {
        List<String> res = new ArrayList<>();
        LocalDate newStartDate = LocalDate.parse(startDate, dateFormat).minusDays(1); //需要先减1，才包含startDate
        LocalDate newEndDate = LocalDate.parse(endDate, dateFormat);
        while (!newStartDate.equals(newEndDate)) {
            newStartDate = newStartDate.plusDays(1);
            String dateString = dateFormat.format(newStartDate);
            res.add(dateString);
        }
        return res;
    }

    public static List<String> getDateList(int gap, String endDate) {
        List<String> res = new ArrayList<>();
        for (int i = gap - 1; i >= 0; i--) {
            LocalDate newDate = LocalDate.parse(endDate, dateFormat).minusDays(i);
            String dateString = dateFormat.format(newDate);
            res.add(dateString);
        }
        return res;
    }

    /**
     * 计算两日期相差天数
     * @param dateStr1
     * @param dateStr2
     * @return
     * @throws ParseException
     */
    public static int daysGap(String dateStr1, String dateStr2) {
        long gap = 0;
        if("".equals(dateStr1) || dateStr1 == null || "".equals(dateStr2) || dateStr2 == null)
            return (int) gap;
        LocalDate date1 = LocalDate.parse(dateStr1, dateFormat);
        LocalDate date2 = LocalDate.parse(dateStr2, dateFormat);
        gap = ChronoUnit.DAYS.between(date1, date2);
        return (int) gap;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentDate() {
        return fullDateFormat.format(LocalDateTime.now());
    }

    public static void main(String[] args) {
        System.out.println(daysGap("2022-11-25", "2022-11-25"));
        System.out.println(getCurrentDate());
    }
}
