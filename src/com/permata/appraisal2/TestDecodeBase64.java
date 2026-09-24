package com.permata.appraisal2;

import com.permata.appraisal2.util.ParserUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;

import java.io.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TestDecodeBase64 {
    public static void main(String[] args){
        new TestDecodeBase64();
    }

    public TestDecodeBase64(){
        Date date = new Date();
        date.setDate(20);
        date.setMonth(8);
        date.setYear(1999);
//        data1.setTGL_BERAKHIRNYA_HAK(ParserUtils.parseToCalendar(tanah.getTanggalBerakhirHak()))

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        System.out.println("Date " + date);
        System.out.println("Calendar " + cal);
        System.out.println("util calendar local " + ParserUtils.parseToCalendar(date));
//        File file = new File("d:/test_base64.base64");try {
//            byte[] bytes = read(file);
//            System.out.println(Arrays.toString(bytes));
//            byte[] base64 = Base64.decodeBase64(bytes);
//            System.out.println("-");
//            System.out.println(base64);
//            System.out.println("-");
//
//            FileUtils.writeByteArrayToFile(new File("d:/test_base64_decode.txt"), base64);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        }
    }


//    public byte[] read(File file) throws IOException {
//        byte[] buffer = new byte[(int) file.length()];
//        InputStream ios = null;
//        try {
//            ios = new FileInputStream(file);
//            if (ios.read(buffer) == -1) {
//                throw new IOException(
//                        "EOF reached while trying to read the whole file");
//            }
//        } finally {
//            try {
//                if (ios != null)
//                    ios.close();
//            } catch (IOException e) {
//            }
//        }
//        return buffer;
//    }
//}
