package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String DEFAULT_DATE_FORMAT="yyyy.MM.dd";

    public static final String FORMULA_REGEX_MIN="[=][M][I][N][(](([A-Z]+\\d+)|(\\d+[.]\\d+[.]\\d+))([,]([A-Z]+\\d+)|(\\d+[.]\\d+[.]\\d+))*[)]";
    public static final String FORMULA_DATE_PLUS_CONST="[=](\\d+[.]\\d+[.]\\d+)[+]\\d+";
    public static final String FORMULA_CONST_REGEX_M="[=](\\d+[.]\\d+[.]\\d+)[-]\\d+";
    public static final String DATE_REGEX="(\\d+[.]\\d+[.]\\d+)";

    public static boolean isDate(String string){
        return string.matches(DATE_REGEX);
    }

    public static boolean isDatePlusConst(String string){
        return string.matches(FORMULA_DATE_PLUS_CONST);
    }

    public static Object parse(String string) {
        Date date;
        try {
            date = dateFormat.parse(string);
            return date;
        } catch (
                ParseException e) {
            return "";
        }
    }


    private static DateFormat dateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public static String format(Date date){
        return dateFormat.format(date);
    }

    public static Date addDays(Date date, int days)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }
    public static Date subDate(Date date1, Date date2) throws IllegalArgumentException
    {
        long diff=date1.getTime()-date2.getTime();
        if(diff<0){
            throw new IllegalArgumentException();
        }
        return new Date(diff);
    }

    public static String formDateString(String year,String month, String day){
        StringBuffer buffer=new StringBuffer();
        buffer.append(year);
        buffer.append(".");
        buffer.append(month);
        buffer.append(".");
        buffer.append(day);
        return buffer.toString();
    }
}
