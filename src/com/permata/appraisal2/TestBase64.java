package com.permata.appraisal2;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;

import java.io.*;
import java.util.Arrays;

public class TestBase64 {

    public static void main(String[] arg) {
        new TestBase64();
    }

    public TestBase64() {
        File file = new File("d:/test_base64.txt");
        try {
            byte[] bytes = read(file);
            System.out.println(Arrays.toString(bytes));
            String base64 = Base64.encodeBase64String(bytes);
            System.out.println("-");
            System.out.println(base64);
            System.out.println("-");

            FileUtils.writeByteArrayToFile(new File("d:/test_base64.base64"), base64.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] read(File file) throws IOException {
        byte[] buffer = new byte[(int) file.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(file);
            if (ios.read(buffer) == -1) {
                throw new IOException(
                        "EOF reached while trying to read the whole file");
            }
        } finally {
            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return buffer;
    }
}