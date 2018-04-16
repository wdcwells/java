package com.wdc.learnning.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by wangdachong on 2017/6/17.
 */
public class DateUtil {
    //当前毫秒
    public static Long currentMillionires(){
        return new Date().getTime();
    }

    //今日零时
    public static Long today0Time() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        return new Date(simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()).getTime();
    }

    //昨日零时
    public static Long yestoday0Time() throws ParseException {
        return new Date(today0Time() - 3600*24*1000).getTime();
    }



    public static void main(String[] args) throws Exception{
        LocalDate parse = LocalDate.parse("2018-01-31", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(parse.getYear());
    }
}
