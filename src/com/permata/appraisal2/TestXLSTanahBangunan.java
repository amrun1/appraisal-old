package com.permata.appraisal2;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

public class TestXLSTanahBangunan {

    public static void main(String[] arg) {
        poi();
        jxl();
    }

    public  static void poi() {
        File file = new File("C:/Users/rans/Downloads/LEMBAR KERJA_2017 Ver082017-01_FINAL.12.xls");
        try {
//            ByteArrayInputStream bis = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
            InputStream bis = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(bis);
            HSSFSheet sheet = workbook.getSheetAt(0);

            HSSFRow row = sheet.getRow(2);
            HSSFCell cell = row.getCell(10);
            System.out.println("Nilai Doc Tanah : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());
            cell = row.getCell(17);
            System.out.println("Nilai Fisik Tanah : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());

            row = sheet.getRow(3);
            cell = row.getCell(10);
            System.out.println("Nilai Doc bangunan : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());
            cell = row.getCell(17);
            System.out.println("Nilai Fisik bangunan : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());

            row = sheet.getRow(4);
            cell = row.getCell(10);
            System.out.println("Total Doc : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());
            cell = row.getCell(17);
            System.out.println("Total Fisik : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());

            row = sheet.getRow(6);
            cell = row.getCell(10);
            System.out.println("Likuidasi Doc tanah : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());
            cell = row.getCell(17);
            System.out.println("Likuidasi fisik tanah : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());

            row = sheet.getRow(7);
            cell = row.getCell(10);
            System.out.println("Likuidasi Doc bangunan : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());
            cell = row.getCell(17);
            System.out.println("Likuidasi fisik bangunan : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());

            row = sheet.getRow(8);
            cell = row.getCell(10);
            System.out.println("Total Likuidasi Doc : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());
            cell = row.getCell(17);
            System.out.println("Total Likuidasi fisik : " + BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jxl(){
        String EXCEL_FILE_LOCATION = "C:\\Users\\rans\\Downloads\\LEMBAR KERJA_2017 Ver082017-01_FINAL.1.xls";
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));
            Sheet sheet = workbook.getSheet(0);

            Cell cell = sheet.getCell(10,2);
            System.out.println("Nilai Doc Tanah : " + (new BigDecimal(cell.getContents())).toPlainString());
            cell = sheet.getCell(17,2);
            System.out.println("Nilai Fisik Tanah : " + (new BigDecimal(cell.getContents())).toPlainString());

            cell = sheet.getCell(10,3);
            System.out.println("Nilai Doc bangunan : " + (new BigDecimal(cell.getContents())).toPlainString());
            cell = sheet.getCell(17,3);
            System.out.println("Nilai Fisik bangunan : " + (new BigDecimal(cell.getContents())).toPlainString());

            cell = sheet.getCell(10,4);
            System.out.println("Total Doc : " + (new BigDecimal(cell.getContents())).toPlainString());
            cell = sheet.getCell(17,4);
            System.out.println("Total Fisik : " + (new BigDecimal(cell.getContents())).toPlainString());

            cell = sheet.getCell(10,6);
            System.out.println("Likuidasi Doc tanah : " + (new BigDecimal(cell.getContents())).toPlainString());
            cell = sheet.getCell(17,6);
            System.out.println("Likuidasi fisik tanah : " + (new BigDecimal(cell.getContents())).toPlainString());

            cell = sheet.getCell(10,7);
            System.out.println("Likuidasi Doc bangunan : " + (new BigDecimal(cell.getContents())).toPlainString());
            cell = sheet.getCell(17,7);
            System.out.println("Likuidasi fisik bangunan : " + (new BigDecimal(cell.getContents())).toPlainString());

            cell = sheet.getCell(10,8);
            System.out.println("Total Likuidasi Doc : " + (new BigDecimal(cell.getContents())).toPlainString());
            cell = sheet.getCell(17,8);
            System.out.println("Total Likuidasi fisik : " + (new BigDecimal(cell.getContents())).toPlainString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}