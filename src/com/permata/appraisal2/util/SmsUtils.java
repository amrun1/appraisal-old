package com.permata.appraisal2.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import org.apache.log4j.Logger;

public class SmsUtils {

    protected final Logger logger = Logger.getLogger(getClass());

    public String sendSMS(String smsUrl, String smsUserId, String smsPassword, String recipient, String message) {
        URL url = null;
        HttpURLConnection connHttp = null;
        HttpsURLConnection connHttps = null;
        Scanner s = null;
        String requestUrl = "";
        String response = "9999";

        try {
            requestUrl = smsUrl
                    + "u=" + URLEncoder.encode(smsUserId, "UTF-8")
                    + "&p=" + URLEncoder.encode(smsPassword, "UTF-8")
                    + "&d=" + URLEncoder.encode(recipient, "UTF-8")
                    + "&m=" + URLEncoder.encode(message, "UTF-8");

            url = new URL(requestUrl);

            if (smsUrl.substring(0, 5).equalsIgnoreCase("https")) {
                connHttps = (HttpsURLConnection) url.openConnection();
                connHttps.setRequestMethod("GET");

                if (connHttps.getResponseCode() != 200) {
                    s = new Scanner(connHttps.getErrorStream());
                } else {
                    s = new Scanner(connHttps.getInputStream());
                }

                s.useDelimiter("\\Z");
                response = s.next();
//                connHttps.disconnect();
            } else if (smsUrl.substring(0, 5).equalsIgnoreCase("http:")) {
                connHttp = (HttpURLConnection) url.openConnection();
                connHttp.setRequestMethod("GET");

                if (connHttp.getResponseCode() != 200) {
                    s = new Scanner(connHttp.getErrorStream());
                } else {
                    s = new Scanner(connHttp.getInputStream());
                }

                s.useDelimiter("\\Z");
                response = s.next();
//                connHttp.disconnect();
            }

            // System.out.println("SMS Notification >> MSISDN : " + recipient + " >> Message : " + message + " >> Response Content From SPRINT System : " + response);
        } catch (Exception ex) {
            logger.error("SMS Notification Error >> " + ex.getMessage());
        } finally {
            if(s!=null)s.close();
            if(connHttp!=null)connHttp.disconnect();
            if(connHttps!=null)connHttps.disconnect();
        }

        return response;
    }

    public boolean isBigDecimal(String x) {
        try {
            new java.math.BigDecimal(x);
            return true;
        } catch (Exception ex) {
        }
        return false;
    }

    public static String toInitCap(String param) {
        if (param != null && param.length() > 0) {
            param = param.toLowerCase();
            char[] charArray = param.toCharArray(); // convert into char array
            charArray[0] = Character.toUpperCase(charArray[0]); // set capital letter to first postion
            return new String(charArray); // return desired output
        } else {
            return "";
        }
    }

    public static String getFileImageExt(String ext) {
        String newExt = "";

        try {
            String split[] = ext.split("\\.");
            if (split.length > 0) {
                newExt = split[split.length - 1]; //jadi ada .jpg atau jpg saja, maka yg keambil tetep element index yg trakir
            }
        } catch (Exception ex) {
            newExt = "jpg";
        }

        return newExt;
    }
}
