package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.facade.DataCommonFacade;
import com.permata.appraisal2.facade.SendAttachmentPlusFacade;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.JasperUtils;
import com.permata.appraisal2.util.PlusClientUtils;
import com.permata.appraisal2.ws.client.plus.AttachModel;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.rpc.holders.IntegerWrapperHolder;
import javax.xml.rpc.holders.StringHolder;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

//@Service
@Component
public class SendAttachmentPlusFacadeImpl implements SendAttachmentPlusFacade {

//    @Autowired
//    private DataCommonFacade dataCommonFacade;
//
//    @Override
    public void sendAttachments(DataCommon dataCommon, SendToSourceLog logParent, String url, Boolean sendDokTanah, Boolean sendDokBangunan) {
//        DataDetail detail = dataCommon.getDataDetail();
//        // LPA Umum
//        LpaUmum lpaUmum = dataCommon.getDataDetail().getLpaUmum().iterator().next();
//
//        Integer fileNumber = 1;
//        List<SurveyImage> docWs = getSurveyImageDao().findWorkshetByLpa(lpaUmum.getId());
//        List<SurveyImage> docPelengkap = getSurveyImageDao().findDokPelengkapByLpa(lpaUmum.getId());
//        Integer totalFile;// = 3 + (sendDokBangunan?1:0) + (sendDokTanah?1:0) +docWs.size()+docPelengkap.size();
//
////            Boolean lpaReportSucceed = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password);
//        Integer coreReportStatus = 0;
//        Integer lampiranDocTanahStatus = 0;
//        Integer lampiranDocBangunanStatus = 0;
//        Boolean worksheetStatus = true;
//        Integer counter;
//        if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)
//                || dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
//            counter = 2;
//            totalFile = 2;
//        } else {
//            counter = 3;
//            totalFile = 3 + (sendDokBangunan?1:0) + (sendDokTanah?1:0) +docWs.size()+docPelengkap.size();
//        }
//            while(fileNumber<=counter) {
//            coreReportStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url,dataCommon.getDataDetail().getAssetType().getCategoryType().getCode());
//            if(coreReportStatus>0) break;
//            if(fileNumber<counter) fileNumber++;
//        }
//        // End Notes  : This all 3 core Report to Send (LPA, ImageSurvey, Peta/Denah)
//        if (coreReportStatus == 0) {
//            // Notes : only for Appraisal (Internal & External)
//            Boolean loop = true; //dummyboolean
//            while (loop) { // only for break out
//                if (dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.APPRAISAL)) {
//                    // Notes :  Pemisahan Jenis Aktiva
//                    if (dataCommon.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.LNB)) {
//                        if (sendDokTanah) { // Tanah Bangunan
//                            fileNumber++;
//                            // // TODO: 11/8/2017 send Lampiran doc tanah here
//                            lampiranDocTanahStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url, "docTanah");
//                        }
//                        if(lampiranDocTanahStatus != 0) break;
//                        if (sendDokBangunan ) {
//                            fileNumber++;
//                            // TODO: 11/8/2017 send Lampiran doc Bangunan here
//                            lampiranDocBangunanStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url, "docBangunan");
//                        }
//                        if(lampiranDocBangunanStatus != 0) break;
//                    } else if (dataCommon.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.KENDARAAN)) {
//                        // Kendaraan
//                        if (sendDokTanah) { // Tanah Bangunan
//                            fileNumber++;
//                            lampiranDocTanahStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url, "lampKendaraan");
//                        }
//                        if(lampiranDocTanahStatus != 0) break;
//                    } else if (dataCommon.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.MESIN)) {
//                        // Mesin
//                        if (sendDokTanah) { // Tanah Bangunan
//                            fileNumber++;
//                            lampiranDocTanahStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url, "lampMesin");
//                        }
//                        if(lampiranDocTanahStatus != 0) break;
////                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.STOCK)) {
////                            // Stok
////                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.VESEL)) {
////                            // Kapal
//                    }
//                    if(docWs.size()>0) {
//                        // // TODO: 11/8/2017 Call method who gather WS dan Send it
//                        worksheetStatus = sendWorksheet(dataCommon, docWs,fileNumber, totalFile, logParent, url);
//                        fileNumber+=docWs.size();
//                        docWs.clear();
//                        docWs = null;
//                    }
//                    if(failedOrComplete(worksheetStatus, totalFile, fileNumber, dataCommon)) break;
//                    sendDokumenPelengkap(dataCommon, docPelengkap,fileNumber, totalFile, logParent, url);
//                    break;
//                }
//                loop = false; //dummy loop to prevent from infinite loops
//            }
//        } else if (coreReportStatus != 1) {
//            dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
//            getDao().save(dataCommon);
//        }
    }
//
//    private Integer sendLPAReport(DataCommon dataCommon, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url, String reportName) {
//        // Notes : Return 0 success send, 1 success and complete, other failed/error
//        String jasperName = "";
//        String docCode = "";
//        String fileName = "";
//        Integer responseStatus = 99;
//        byte[] reportByte = null;
////        List<AttachModel> listAttachment = null;
//        AttachModel rptLaporan = null;
//        StringHolder stringHolderAttact = null;
//        IntegerWrapperHolder integerWrapperHolderAttact = null;
//        SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
//        Map<String, Object> parameterMap = null;
//        if(fileNumber==1) {  // LPA Report
//            if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)) {
//                jasperName = "rptLaporanBVBox.jasper";
//                docCode = "Report LPA BV";
//                fileName = String.format("report-lpa-BV-%s.pdf", dataCommon.getOrderNumber());
//            } else if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
//                jasperName = "rptLaporanRVBox.jasper";
//                docCode = "Report LPA RV";
//                fileName = String.format("report-lpa-RV-%s.pdf", dataCommon.getOrderNumber());
//            } else if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RAB)) {
//                jasperName = "rptFormatLpaRab.jasper";
//                docCode = "Report LPA RAB";
//                fileName = String.format(String.format("report-RAB-%s.pdf", dataCommon.getOrderNumber()));
//            } else if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.DESKTOP)) {
//                jasperName = "rptFormatLpaDesktop.jasper";
//                docCode = "Report LPA Desktop";
//                fileName = String.format(String.format("report-LPA-Desktop-%s.pdf", dataCommon.getOrderNumber()));
//            } else {
//                jasperName = getJasperNameAppraisalType(dataCommon.getAppraisalTypeCategory(), reportName);
//                fileName = dataCommon.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)?"Report-LPA-External-%s.pdf":"Report-LPA-%s.pdf";
//                docCode = "Report LPA";
//            }
//        } else if (fileNumber==2) { // Image Survey
//            if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)
//                    || dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
//                jasperName = "rptLampiranImageOther.jasper";
//                fileName = "report-LPA-Image-other-%s.pdf";
//                docCode = "Report LPA Image Survey";
//            } else {
//                jasperName = "rptLampiranImageSurvey.jasper";
//                fileName = "Report-LPA-Image-survey-%s.pdf";
//                docCode = "Report LPA Image Survey";
//            }
//        } else if (fileNumber==3) { //  Peta / Denah
//            jasperName = "rptLampiranImagePeta.jasper";
//            docCode = "Report LPA Image Peta";
//            fileName = "Report-LPA-Image-Peta-%s.pdf";
//        } else {
//            if(StringUtils.equalsIgnoreCase(reportName, "docTanah")) {
//                jasperName = "rptLampiranDocTanah.jasper";
//                docCode = "Report Lampiran Tanah";
//                fileName = "Lampiran-tanah-%s.pdf";
//            } else if(StringUtils.equalsIgnoreCase(reportName, "docBangunan")) {
//                jasperName = "rptLampiranDocBangunan.jasper";
//                docCode = "Report Lampiran Bangunan";
//                fileName = "Lampiran-bangunan-%s.pdf";
//            } else if(StringUtils.equalsIgnoreCase(reportName, "lampKendaraan")) {
//                jasperName = "rptLampiranKendaraan.jasper";
//                docCode = "Report Lampiran Kendaraan";
//                fileName = "Lampiran-Kendaraan-%s.pdf";
//            } else if(StringUtils.equalsIgnoreCase(reportName, "lampMesin")) {
//                jasperName = "rptLampiranMesin.jasper";
//                docCode = "Report Lampiran Mesin";
//                fileName = "Lampiran-Mesin-%s.pdf";
//            }
//        }
//        sendToSourceAttachment.setSendToSourceLog(logParent);
//        sendToSourceAttachment.setIdCommon(dataCommon.getId());
//        sendToSourceAttachment.setFileName(String.format(fileName, dataCommon.getOrderNumber()));
//        sendToSourceAttachment.setTanggal(new Date());
//        try {
//            stringHolderAttact = new StringHolder();
//            integerWrapperHolderAttact = new IntegerWrapperHolder();
//            parameterMap = new HashMap<String, Object>();
//            stringHolderAttact = new StringHolder();
//            integerWrapperHolderAttact = new IntegerWrapperHolder();
//            String jasperFile = String.format("%s%s%s%s%s%s%s", getContext().getRealPath(""), File.separator, "WEB-INF",
//                    File.separator, "report", File.separator, jasperName);
//            String jasperDir = String.format("%s%s%s%s%s%s", getContext().getRealPath(""), File.separator, "WEB-INF",
//                    File.separator, "report", File.separator);
//            parameterMap.put("SUBREPORT_DIR", jasperDir);
//            parameterMap.put("datasource", getDataSource());
//            parameterMap.put("PIdLpaUmum", dataCommon.getDataDetail().getLpaUmum().iterator().next().getId());
//            reportByte = JasperUtils.generate(parameterMap, jasperFile, getDataSource());
////            listAttachment = new ArrayList<AttachModel>();
//            rptLaporan = new AttachModel();
//            rptLaporan.setByteData(reportByte);
//            rptLaporan.setContentType("application/pdf");
//            rptLaporan.setDocCode(docCode);
//            rptLaporan.setFileName(fileName);
////            listAttachment.add(rptLaporan);
//            if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)
//                    || dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
//                PlusClientUtils.getInstance(url).getiSyncReply().submitBvrvAttachment(new AttachModel[]{rptLaporan}, dataCommon.getOrderNumber(), stringHolderAttact, integerWrapperHolderAttact);
//            } else {
//                PlusClientUtils.getInstance(url).getiSyncReply().submitLPAResultAttachment(new AttachModel[]{rptLaporan}, dataCommon.getOrderNumber(), stringHolderAttact, integerWrapperHolderAttact);
//            }
//            sendToSourceAttachment.setMessage(stringHolderAttact.value);
//            sendToSourceAttachment.setStatus(integerWrapperHolderAttact.value);
//            sendToSourceAttachment.setResponse(String.valueOf(integerWrapperHolderAttact.value));
//            responseStatus = integerWrapperHolderAttact.value;
//            if(integerWrapperHolderAttact.value==1) {
//                // cek udah semua apa belum karena notif dari PLUS sudah selesai. jika belum berarti ada yang salah
//                // jika sudah terakhir update data umum menjadi send sukses
//                if(fileNumber != totalFile){
//                    sendToSourceAttachment.setMessage(sendToSourceAttachment.getMessage() + ", But Not All Attachment send");
//                    dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
//                    getDao().save(dataCommon);
//                    responseStatus = 98;
//                } else {
//                    dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
//                    getDao().save(dataCommon);
//                }
//            } else if(integerWrapperHolderAttact.value!=0) {
//                dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
//                getDao().save(dataCommon);
//            }
//        } catch (JRException e) {
//            responseStatus = 99;
//            sendToSourceAttachment.setMessage("Error Jasper Report Exception");
//            sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//            e.printStackTrace();
//        } catch (SQLException e) {
//            responseStatus = 99;
//            sendToSourceAttachment.setMessage("Error SQL Exception");
//            sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//            e.printStackTrace();
//        } catch (RemoteException e) {
//            responseStatus = 99;
//            if(StringUtils.containsIgnoreCase(e.getCause().getMessage(),"Bad envelope tag")) {
//                sendToSourceAttachment.setMessage("Failed send : File too large....");
//            } else {
//                sendToSourceAttachment.setMessage("Error Remote Exception");
//            }
//            sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            responseStatus = 99;
//            sendToSourceAttachment.setMessage("Error Malformed URL Exception");
//            sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//            e.printStackTrace();
//        } finally {
//            getSendToSourceLogAttachmenDao().save(sendToSourceAttachment);
//            if(reportByte!=null)reportByte=null;
//            if(rptLaporan!=null)rptLaporan=null;
//            if(parameterMap!=null) parameterMap.clear();
//            return  responseStatus;
//        }
//    }
//
//    private Boolean sendWorksheet(DataCommon dataCommon, List<SurveyImage> docWs, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url) {
//        Boolean status = true;
//        Boolean complete = false;
//        AttachModel worksheet = null;
//        StringHolder stringHolderAttact = null;
//        IntegerWrapperHolder integerWrapperHolderAttact = null;
//        for (SurveyImage image: docWs){
//            stringHolderAttact = new StringHolder();
//            integerWrapperHolderAttact = new IntegerWrapperHolder();
//            fileNumber++;
//            String namaFile = String.format(image.getId()+"Worksheet-%s.xls", dataCommon.getOrderNumber());
//            SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
//            sendToSourceAttachment.setSendToSourceLog(logParent);
//            sendToSourceAttachment.setIdCommon(dataCommon.getId());
//            sendToSourceAttachment.setTanggal(new Date());
//            sendToSourceAttachment.setFileName(namaFile);
////            String nameFileFix ="";
//            try {
//                worksheet = new AttachModel();
//                worksheet.setByteData(image.getImage());
//                worksheet.setContentType(image.getContentType());
//                worksheet.setDocCode("Attach LPA Worksheeet");
//                worksheet.setFileName(namaFile);
////            listAttachment.add(rptLaporan);
//                PlusClientUtils.getInstance(url).getiSyncReply().submitLPAResultAttachment(new AttachModel[]{worksheet}, dataCommon.getOrderNumber(), stringHolderAttact, integerWrapperHolderAttact);
//
//                sendToSourceAttachment.setStatus(integerWrapperHolderAttact.value);
//                sendToSourceAttachment.setMessage(stringHolderAttact.value);
//                sendToSourceAttachment.setResponse(String.valueOf(integerWrapperHolderAttact.value));
//                status = sendToSourceAttachment.getStatus()==0;
//                complete = sendToSourceAttachment.getStatus()==1;
//                if(complete) {
//                    status = true;
//                    break;
//                } else if(!status) break;
//            } catch (MalformedURLException e) {
//                sendToSourceAttachment.setStatus(99);
//                sendToSourceAttachment.setMessage("Error Malformed URL Exception");
//                sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//                e.printStackTrace();
//                status=false;
//                break;
//            } catch (RemoteException e) {
//                sendToSourceAttachment.setStatus(99);
//                sendToSourceAttachment.setMessage("Error Remote Exception");
//                sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//                e.printStackTrace();
//                status=false;
//                break;
//            } finally {
//                getSendToSourceLogAttachmenDao().save(sendToSourceAttachment);
//                worksheet = null;
//            }
//        }
//        if(docWs!=null)docWs.clear();
//        return status;
//    }
//
//    private void sendDokumenPelengkap (DataCommon dataCommon, List<SurveyImage> docPelengkap, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url) {
////                List<SurveyImage> docWs = surveyImageDao.findBySurveyXLS(lpaUmum.getId());
//        Boolean status = true;
//        StringHolder stringHolderAttact = null;
//        IntegerWrapperHolder integerWrapperHolderAttact = null;
//        if (docPelengkap.size()>0){
//            AttachModel otherAttach;
//            for (SurveyImage imagePelengkap: docPelengkap){
//                stringHolderAttact = new StringHolder();
//                integerWrapperHolderAttact = new IntegerWrapperHolder();
//                SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
//                sendToSourceAttachment.setSendToSourceLog(logParent);
//                sendToSourceAttachment.setIdCommon(dataCommon.getId());
//                sendToSourceAttachment.setTanggal(new Date());
//                try {
//                    fileNumber++;
//                    DocumentLpaType typeDoc = imagePelengkap.getDocumentLpaType();
//                    String namaFile = typeDoc.getName() + "-" + imagePelengkap.getId();
//                    String nameFileFix ="";
//                    otherAttach = new AttachModel();
//                    otherAttach.setByteData(imagePelengkap.getImage());
//                    otherAttach.setContentType(imagePelengkap.getContentType());
//                    otherAttach.setDocCode("Attach LPA LNB Others");
//                    if (imagePelengkap.getContentType()!=null && imagePelengkap.getContentType().length() > 0 ){
//                        String type = imagePelengkap.getContentType();
//                        if (type.equals(getFormatDocOthers()[4])){
//                            nameFileFix = namaFile.concat(".pdf");
//                        } else if (type.equals(getFormatDocOthers()[0])) {
//                            nameFileFix = namaFile.concat(".jpeg");
//                        } else if (type.equals(getFormatDocOthers()[1])) {
//                            nameFileFix = namaFile.concat(".png");
//                        }
//                        otherAttach.setFileName(String.format("%s-"+nameFileFix, dataCommon.getOrderNumber()));
//                    }else{
//                        nameFileFix = namaFile;
//                        otherAttach.setFileName(String.format("%s-"+nameFileFix, dataCommon.getOrderNumber()));
//                    }
//                    PlusClientUtils.getInstance(url).getiSyncReply().submitLPAResultAttachment(new AttachModel[]{otherAttach}, dataCommon.getOrderNumber(), stringHolderAttact, integerWrapperHolderAttact);
//                    sendToSourceAttachment.setStatus(integerWrapperHolderAttact.value);
//                    sendToSourceAttachment.setMessage(stringHolderAttact.value);
//                    sendToSourceAttachment.setResponse(String.valueOf(integerWrapperHolderAttact.value));
//                    status = sendToSourceAttachment.getStatus()==0;
//                    Boolean complete = sendToSourceAttachment.getStatus()==1;
//                    if(complete) {
//                        sendToSourceAttachment.setStatus(1);
//                        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
//                        getDao().save(dataCommon);
//                        break;
//                    } else if(!status) {
//                        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
//                        getDao().save(dataCommon);
//                        break;
//                    }
//                } catch (MalformedURLException e) {
//                    sendToSourceAttachment.setStatus(99);
//                    sendToSourceAttachment.setMessage("Error Malformed URL Exception");
//                    sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//                    e.printStackTrace();
//                } catch (RemoteException e) {
//                    sendToSourceAttachment.setStatus(99);
//                    sendToSourceAttachment.setMessage("Error Remote Exception");
//                    sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//                    e.printStackTrace();
//                } finally {
//                    getSendToSourceLogAttachmenDao().save(sendToSourceAttachment);
//                    otherAttach = null;
//
//                }
//            }
//        }
//        if(docPelengkap!=null)docPelengkap.clear();
//    }
//
//    private void saveCompletedAttachment(DataCommon dataCommon) {
//        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
//        getDao().save(dataCommon);
//    }
//
//    private Boolean failedOrComplete(Boolean status, Integer totalFile, Integer fileNumber, DataCommon dataCommon) {
//        if(!status) {
//            dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
//            getDao().save(dataCommon);
//            return true;
//        } else if (totalFile == fileNumber) {
//            saveCompletedAttachment(dataCommon);
//            return true;
//        }
//        return  false;
//    }
}
