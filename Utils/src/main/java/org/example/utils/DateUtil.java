package org.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    //日期转字符串
    public static String dateToString(Date date,String patten){
        SimpleDateFormat sdf=new SimpleDateFormat(patten);
        String dateStr=sdf.format(date);
        return dateStr;
    }
    //字符串转日期
    public static Date stringToDate(String str,String patten) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patten);
        Date date=sdf.parse(str);
        return date;
    }
}
