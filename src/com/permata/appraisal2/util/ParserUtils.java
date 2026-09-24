package com.permata.appraisal2.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ParserUtils {

    public static String parseToDate(SimpleDateFormat sdf, Date date, String defaultVal) {
        if (date != null) {
            return sdf.format(date);
        }
        return defaultVal;
    }

    public static String parseToDate(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }





    public static Calendar parseToCalendar(Date date) {
        if (date==null){
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        TimeZone tz = cal.getTimeZone();
        //Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
        long msFromEpochGmt = date.getTime();

        //gives you the current offset in ms from GMT at the current date
        int offsetFromUTC = tz.getOffset(msFromEpochGmt);

        //create a new calendar in GMT timezone, set to this date and add the offset
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.setTime(date);
        gmtCal.add(Calendar.MILLISECOND, offsetFromUTC);

        return gmtCal;
    }

    public static Float parseToFloat(Object obj) {
        Float result = 0f;
        if (obj != null) {
            if (obj instanceof Float) {
                result = (Float) obj;
            } else if (obj instanceof BigDecimal) {
                result = ((BigDecimal) obj).floatValue();
            } else if (obj instanceof Long) {
                result = ((Long) obj).floatValue();
            } else if (obj instanceof Double) {
                result = ((Double) obj).floatValue();
            } else {
                result = Float.valueOf(obj.toString());
            }
        }
        return result;
    }

    public static Double parseToDouble(Object obj) {
        Double result = 0d;
        if (obj != null) {
            if (obj instanceof Long) {
                result = Double.valueOf(((Long) obj).doubleValue());
            } else if (obj instanceof BigDecimal) {
                result = ((BigDecimal) obj).doubleValue();
            } else {
                result = Double.valueOf(obj.toString());
            }
        }
        return result;
    }

    public static Boolean parseToBoolean(Object obj) {
        Boolean result = Boolean.FALSE;
        if (obj != null) {
            if (obj instanceof Boolean) {
                result = (Boolean) obj;
            } else {
                result = Boolean.parseBoolean(obj.toString());
            }
        }
        return result;
    }

    public static BigDecimal parseToDecimal(Object obj) {
        if (obj != null) {
            if (obj instanceof BigDecimal) {
                return (BigDecimal) obj;
            } else if (obj instanceof String) {
                try {
                    return new BigDecimal(obj.toString());
                } catch(Exception e){
                    System.out.println(obj.toString());
                    return BigDecimal.ZERO;
                }
            }
        }
        return BigDecimal.ZERO;
    }

    public static Integer parseToInteger(Object obj) {
        if (obj != null) {
            if (obj instanceof Integer) {
                return (Integer) obj;
            } else if (obj instanceof String) {
                return Integer.valueOf((String) obj);
            }
        }
        return 0;
    }
}