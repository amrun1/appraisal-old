package com.permata.appraisal2.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class JasperUtils {
    public static byte[] generate(Map<String, Object> params, String jasperFile, BasicDataSource dataSource) throws JRException, SQLException {
        JasperPrint jasperPrint=null;
        Connection conn = dataSource.getConnection();
//        JasperDesign jasperDesign = null;
//        JasperReport jasperReport = null;
        jasperFile = StringUtils.replace(jasperFile, "jrxml", "jasper");//split(jasperFile, ".")[0]+".jasper";
        try {
//            if(StringUtils.split(jasperFile, ".")[1].equalsIgnoreCase("jasper")){
                jasperPrint = JasperFillManager.fillReport(jasperFile, params, conn);
//            } else {
//                jasperDesign = JRXmlLoader.load(jasperFile);
//                jasperReport = JasperCompileManager.compileReport(jasperDesign);
//                jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
//            }
            return JasperExportManager.exportReportToPdf(jasperPrint);
//            byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
//            System.out.println("jasperUtil ===== " + reportFile.length);
//            System.out.println("jasperUtil ===== " + reportFile);
//            return reportFile;
//            byte[] bytes = exportReport(jasperPrint);
//            writeByteToFile(jasperFile + " .pdf", bytes);
//            return bytes;
        }catch (JRException e){
            System.out.println("Error JRException:"+e.getMessage());
            return null;
        }finally {
            //Bugfix proses Tak kunjung Selesai
            if (conn != null) {
                try {
                    conn.close();
                    jasperPrint = null;
//                    jasperDesign = null;
//                    jasperReport = null;
//                    if(!dataSource.isClosed())dataSource.close();
                } catch (SQLException e) {
                    System.out.println("GAGAL TUTUP KONEKSI:"+e.getMessage());
                }
            }
        }
    }

//    public static void writeToFile(String fileName, Map<String, Object> params, String jasperFile, BasicDataSource dataSource) {
//        try {
//            File file = FileUtils.getFile(fileName);
//            FileUtils.writeByteArrayToFile(file, JasperUtils.generate(params, jasperFile, dataSource));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

//    public static void writeByteToFile(String fileName, byte[] bytes) {
//        try {
//            File file = FileUtils.getFile(fileName);
//            FileUtils.writeByteArrayToFile(file, bytes);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private static byte[] exportReport(JasperPrint jasperPrint) {
//        JRPdfExporter exporter = new JRPdfExporter();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
//
//        //configuration
//        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
//        configuration.setCompressed(true);
//
//        //set the configuration
//        exporter.setConfiguration(configuration);
//        //finally exporting the PDF
//        try {
//            exporter.exportReport();
//            return baos.toByteArray();
//        } catch (JRException e) {
//            System.out.println("Error exporting Report");
//            e.printStackTrace();
//            return null;
//        } finally {
//            exporter = null;
//            try {
//                if(baos!=null) {
//                    baos.flush();
//                    baos.close();
//                }
//            } catch (IOException e) {
//                System.out.println("Failed to close stream JasperExportReport");
//                e.printStackTrace();
//            }
//        }
//    }
}