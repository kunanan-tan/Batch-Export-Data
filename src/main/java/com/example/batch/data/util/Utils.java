package com.example.batch.data.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author kunanan.t
 */
public class Utils {

    public static Date convertDateToLocalDateTime(int year, int month, int date){
        LocalDate localDate = LocalDate.of(year,month,date);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertDateImportTxt(String dateString) {
        Date resultDate = null;
        if (!dateString.isBlank()||!dateString.isEmpty()) {
            int day = Integer.parseInt(dateString.substring(0,2));
            int month = Integer.parseInt(dateString.substring(3,5));
            int year = Integer.parseInt(dateString.substring(6,10));
            resultDate = Utils.convertDateToLocalDateTime(year,month,day);
        }
        return resultDate;
    }

    public static long getMinutes(long millis) {
        return TimeUnit.MILLISECONDS.toMinutes(millis);
    }

    public static long getSeconds(long millis) {
        return TimeUnit.MILLISECONDS.toSeconds(millis)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)) ;
    }

    public static BigDecimal getBigDecimal(String value){
        if(null!=value){
            BigDecimal valueOfBigDec=new BigDecimal(value);
            return valueOfBigDec;
        }
        return BigDecimal.ZERO;
    }

    public static String getInteger(Integer value){
        if(null!=value){
            String valueOfInt=String.valueOf(value);
            return valueOfInt;
        }
        return "";
    }

    public static String getString(String value){
        return null!=value?value.toString():"";
    }

}
