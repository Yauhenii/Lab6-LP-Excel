package com.company;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date addDays(Date date, int days)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }
    public static Date subDays(Date date, int days) throws IllegalArgumentException
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setLenient(false);
        calendar.add(Calendar.DATE, -days);
        if(false){
            throw new IllegalArgumentException();
        }
        return calendar.getTime();
    }


}
