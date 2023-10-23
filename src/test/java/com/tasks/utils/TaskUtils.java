package com.tasks.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskUtils {
    public static String asJsonString(final Object obj) {
        try {
            String s =  new ObjectMapper().writeValueAsString(obj);

            return s;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Date getCurrentDate(int days){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return date;
    }
    public static Date getDate(int year,int month,int hour,int day){
        Calendar cal = Calendar.getInstance(); // Calendar constructor
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date d = cal.getTime();
        return d;
    }
}
