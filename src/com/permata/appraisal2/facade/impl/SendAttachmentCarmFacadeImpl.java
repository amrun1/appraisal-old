package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.facade.DataCommonFacade;
import com.permata.appraisal2.facade.SendAttachmentCarmFacade;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.CarmAttachmentClientUtils;
import com.permata.appraisal2.util.JasperUtils;
import com.permata.appraisal2.ws.client.carm.Attachment;
import com.permata.appraisal2.ws.client.carm.Response;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sendAttachmentCarmFacade")
@Transactional(readOnly = true)
public class SendAttachmentCarmFacadeImpl implements SendAttachmentCarmFacade {

//    @Autowired
//    private DataCommonFacade dataCommonFacade;
//
//    @Override
    public void sendAttachment(DataCommon e,SendToSourceLog logParent, String url, String username, String password, Boolean sendDokTanah, Boolean sendDokBangunan) {
////        try {
//            LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
////            LpaTanahBangunan lpaTanahBangunan = lpaUmum.getTanahBangunan().iterator().next();
////            Boolean sendDokTanah = listDokumenTanahCheck(lpaTanahBangunan.getId());
////            Boolean sendDokBangunan = listDokumenBangunanCheck(lpaTanahBangunan.getId());
//            Boolean isExternal = e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL);
//
//            Integer fileNumber = 1;
//            List<SurveyImage> docWs = getSurveyImageDao().findWorkshetByLpa(lpaUmum.getId());
//            List<SurveyImage> docPelengkap = getSurveyImageDao().findDokPelengkapByLpa(lpaUmum.getId());
//            Integer totalFile = 3 + (sendDokBangunan?1:0) + (sendDokTanah?1:0) +docWs.size()+docPelengkap.size();
//
////            Boolean lpaReportSucceed = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password);
//            Boolean coreReportStatus = false;
//            Boolean lampiranDocTanahStatus = true;
//            Boolean lampiranDocBangunanStatus = true;
//            Boolean worksheetStatus = true;
////            Boolean next2 = true ; //lpaReportSucceed;
////            Boolean next3 = false;
////            Boolean next4 = false;
////            Boolean next5 = false;
////            Boolean next6 = false;
////            Boolean nextXLS = false;
////            Boolean allSucceed = true;
////            Boolean completed = false;
//            // Notes  : This all 3 core Report to Send (LPA, ImageSurvey, Peta/Denah)
//            while(fileNumber<=3) {
//                coreReportStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, e.getDataDetail().getAssetType().getCategoryType().getCode());
//                if(!coreReportStatus) break;
//                if(fileNumber<3) fileNumber++;
            }
//            // End Notes  : This all 3 core Report to Send (LPA, ImageSurvey, Peta/Denah)
//            if (coreReportStatus && totalFile == fileNumber) {
//                e.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
//                getDao().save(e);
//            } else if (coreReportStatus) {
//                // Notes : only for Appraisal (Internal & External)
//                Boolean loop = true; //dummyboolean
//                while (loop) { // only for break out
//                    if (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.APPRAISAL)) {
//                        // Notes :  Pemisahan Jenis Aktiva
//                        if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.LNB)) {
//                            if (sendDokTanah) { // Tanah Bangunan
//                                fileNumber++;
//                                // // TODO: 11/8/2017 send Lampiran doc tanah here
//                                lampiranDocTanahStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, "docTanah");
//                            }
//                            if(failedOrComplete(lampiranDocTanahStatus, totalFile, fileNumber, e)) break;
//                            if (sendDokBangunan ) {
//                                fileNumber++;
//                                // TODO: 11/8/2017 send Lampiran doc Bangunan here
//                                lampiranDocBangunanStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, "docBangunan");
//                            }
//                            if(failedOrComplete(lampiranDocBangunanStatus, totalFile, fileNumber, e)) break;
//                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.KENDARAAN)) {
//                            // Kendaraan
//                            if (sendDokTanah) { // Tanah Bangunan
//                                fileNumber++;
//                                lampiranDocTanahStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, "lampKendaraan");
//                            }
//                            if(failedOrComplete(lampiranDocTanahStatus, totalFile, fileNumber, e)) break;
//                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.MESIN)) {
//                            // Mesin
//                            if (sendDokTanah) { // Tanah Bangunan
//                                fileNumber++;
//                                lampiranDocTanahStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, "lampMesin");
//                            }
//                            if(failedOrComplete(lampiranDocTanahStatus, totalFile, fileNumber, e)) break;
////                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.STOCK)) {
////                            // Stok
////                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.VESEL)) {
////                            // Kapal
//                        }
//                        if(docWs.size()>0) {
//                            // // TODO: 11/8/2017 Call method who gather WS dan Send it
//                            worksheetStatus = sendWorksheet(e, docWs,fileNumber, totalFile, logParent, url, username, password);
//                            fileNumber+=docWs.size();
//                            docWs.clear();
//                            docWs = null;
//                        }
//                        if(failedOrComplete(worksheetStatus, totalFile, fileNumber, e)) break;
//                        sendDokumenPelengkap(e, docPelengkap,fileNumber, totalFile, logParent, url, username, password);
//                        break;
//                    }
//                    loop = false; //dummy loop to stop infinite loops
//                }
//            } else {
//                e.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
//                getDao().save(e);
//            }
//    }
//
//    private Boolean sendWorksheet(DataCommon dataCommon, List<SurveyImage> docWs, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url, String username, String password) {
//        Boolean status = true;
//        Response responseAtt;
//        Attachment worksheet;
//        for (SurveyImage image: docWs){
//            fileNumber++;
//            String namaFile = String.format(image.getId()+"Worksheet-%s.xls", dataCommon.getOrderNumber());
//            SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
//            sendToSourceAttachment.setSendToSourceLog(logParent);
//            sendToSourceAttachment.setIdCommon(dataCommon.getId());
//            sendToSourceAttachment.setTanggal(new Date());
//            sendToSourceAttachment.setFileName(namaFile);
////            String nameFileFix ="";
//            try {
//                worksheet = new Attachment();
//                worksheet.setACL_ID(dataCommon.getSourceId());
//                worksheet.setORDERID(Long.parseLong(dataCommon.getOrderNumber()));
//                worksheet.setFILENAME(namaFile);
//                worksheet.setFILEEXTENSION("xls");
//                worksheet.setFILECONTENT(image.getImage());
//                worksheet.setFILENUMBER(fileNumber);
//                worksheet.setFILETOTAL(totalFile);
//                responseAtt = CarmAttachmentClientUtils.getInstance(url, username, password, worksheet);
//
//                sendToSourceAttachment.setStatus(responseAtt.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ATTACH_CARM)?0:2);
//                sendToSourceAttachment.setMessage(responseAtt.getStatus());
//                sendToSourceAttachment.setResponse(responseAtt.getError_Code());
//                status = sendToSourceAttachment.getStatus()==0;
//                if(!status) break;
//            } catch (MalformedURLException e) {
//                sendToSourceAttachment.setStatus(2);
//                sendToSourceAttachment.setMessage("Error Malformed URL Exception");
//                sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//                e.printStackTrace();
//                status=false;
//                break;
//            } catch (RemoteException e) {
//                sendToSourceAttachment.setStatus(2);
//                sendToSourceAttachment.setMessage("Error Remote Exception");
//                sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//                e.printStackTrace();
//                status=false;
//                break;
//            } finally {
//                getSendToSourceLogAttachmenDao().save(sendToSourceAttachment);
//                worksheet = null;
//                responseAtt = null;
//            }
//        }
//        if(docWs!=null)docWs.clear();
//        return status;
//    }
//
//    private void sendDokumenPelengkap (DataCommon dataCommon, List<SurveyImage> docPelengkap, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url, String username, String password) {
////                List<SurveyImage> docWs = surveyImageDao.findBySurveyXLS(lpaUmum.getId());
//        Boolean status = true;
//        if (docPelengkap.size()>0){
//            Response responseAtt;
//            Attachment attachment;
//            for (SurveyImage imagePelengkap: docPelengkap){
//                SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
//                sendToSourceAttachment.setSendToSourceLog(logParent);
//                sendToSourceAttachment.setIdCommon(dataCommon.getId());
//                sendToSourceAttachment.setTanggal(new Date());
//                try {
//                    fileNumber++;
//                    DocumentLpaType typeDoc = imagePelengkap.getDocumentLpaType();
//                    String namaFile = typeDoc.getName() + "-" + imagePelengkap.getId();
//                    String nameFileFix ="";
//                    attachment = new Attachment();
//                    attachment.setACL_ID(dataCommon.getSourceId());
//                    attachment.setORDERID(Long.parseLong(dataCommon.getOrderNumber()));
//                    if (imagePelengkap.getContentType()!=null && imagePelengkap.getContentType().length() > 0 ){
//                        String type = imagePelengkap.getContentType();
//                        if (type.equals(getFormatDocOthers()[4])){
//                            nameFileFix = namaFile.concat(".pdf");
//                            attachment.setFILEEXTENSION("pdf");
//                        } else if (type.equals(getFormatDocOthers()[0])) {
//                            nameFileFix = namaFile.concat(".jpeg");
//                            attachment.setFILEEXTENSION("jpeg");
//                        } else if (type.equals(getFormatDocOthers()[1])) {
//                            nameFileFix = namaFile.concat(".png");
//                            attachment.setFILEEXTENSION("png");
//                        }
//                        attachment.setFILENAME(String.format("%s-"+nameFileFix, dataCommon.getOrderNumber()));
//                    }else{
//                        nameFileFix = namaFile;
//                        attachment.setFILENAME(String.format("%s-"+nameFileFix, dataCommon.getOrderNumber()));
//                        attachment.setFILEEXTENSION("unknown");
//                    }
//                    attachment.setFILECONTENT(imagePelengkap.getImage());
//                    attachment.setFILENUMBER(fileNumber);
//                    attachment.setFILETOTAL(totalFile);
//
//                    sendToSourceAttachment.setFileName(nameFileFix);
//
//                    responseAtt = CarmAttachmentClientUtils.getInstance(url, username, password, attachment);
//
//
//                    sendToSourceAttachment.setStatus(responseAtt.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ATTACH_CARM)?0:2);
//                    sendToSourceAttachment.setMessage(responseAtt.getStatus());
//                    sendToSourceAttachment.setResponse(responseAtt.getError_Code());
//                    status = sendToSourceAttachment.getStatus()==0;
//                    Boolean update = status && totalFile==fileNumber;
//                    if (update){
//                        sendToSourceAttachment.setStatus(1);
//                        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
//                        getDao().save(dataCommon);
//                        attachment = null;
//                        responseAtt = null;
//                        break;
//                    } else if (!status){
//                        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
//                        getDao().save(dataCommon);
//                        attachment = null;
//                        responseAtt = null;
////                        getSendToSourceLogAttachmenDao().save(sendToSourceAttachment);
//                        break;
//                    }
//                } catch (MalformedURLException e) {
//                    sendToSourceAttachment.setStatus(2);
//                    sendToSourceAttachment.setMessage("Error Malformed URL Exception");
//                    sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//                    e.printStackTrace();
//                } catch (RemoteException e) {
//                    sendToSourceAttachment.setStatus(2);
//                    sendToSourceAttachment.setMessage("Error Remote Exception");
//                    sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
//                    e.printStackTrace();
//                } finally {
//                    getSendToSourceLogAttachmenDao().save(sendToSourceAttachment);
//                    attachment = null;
//                    responseAtt = null;
//
//                }
//            }
//        }
//        if(docPelengkap!=null)docPelengkap.clear();
//    }
//
//    private Boolean sendLPAReport(DataCommon dataCommon, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url, String username, String password, String reportName) {
//        String jasperName = "";
//        String fileName = "";
//        byte[] reportByte = null;
//        Response responseAtt = null;
//        Attachment rptFormatLpaInt = null;
//        if(fileNumber==1) {  // LPA Report
//            jasperName = getJasperNameAppraisalType(dataCommon.getAppraisalTypeCategory(), reportName);
//            fileName = dataCommon.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)?"Report-LPA-External-%s.pdf":"Report-LPA-%s.pdf";
//        } else if (fileNumber==2) { // Image Survey
//            jasperName = "rptLampiranImageSurvey.jasper";
//            fileName = "Report-LPA-Image-survey-%s.pdf";
//        } else if (fileNumber==3) { //  Peta / Denah
//            jasperName = "rptLampiranImagePeta.jasper";
//            fileName = "Report-LPA-Image-Peta-%s.pdf";
//        } else {
//            if(StringUtils.equalsIgnoreCase(reportName, "docTanah")) {
//                jasperName = "rptLampiranDocTanah.jasper";
//                fileName = "Lampiran-tanah-%s.pdf";
//            } else if(StringUtils.equalsIgnoreCase(reportName, "docBangunan")) {
//                jasperName = "rptLampiranDocBangunan.jasper";
//                fileName = "Lampiran-bangunan-%s.pdf";
//            } else if(StringUtils.equalsIgnoreCase(reportName, "lampKendaraan")) {
//                jasperName = "rptLampiranKendaraan.jasper";
//                fileName = "Lampiran-Kendaraan-%s.pdf";
//            } else if(StringUtils.equalsIgnoreCase(reportName, "lampMesin")) {
//                jasperName = "rptLampiranMesin.jasper";
//                fileName = "Lampiran-Mesin-%s.pdf";
//            }
//        }
//        SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
//        sendToSourceAttachment.setSendToSourceLog(logParent);
//        sendToSourceAttachment.setIdCommon(dataCommon.getId());
//        sendToSourceAttachment.setFileName(String.format(fileName, dataCommon.getOrderNumber()));
//        sendToSourceAttachment.setTanggal(new Date());
//        Map<String, Object> parameterMap = null;
//
//        try {
//            parameterMap = new HashMap<String, Object>();
//            String jasperFile = String.format("%s%s%s%s%s%s%s", getContext().getRealPath(""), File.separator, "WEB-INF",
//                    File.separator, "report", File.separator, jasperName);
//            String jasperDir = String.format("%s%s%s%s%s%s", getContext().getRealPath(""), File.separator, "WEB-INF",
//                    File.separator, "report", File.separator);
//            parameterMap.put("SUBREPORT_DIR", jasperDir);
//            parameterMap.put("datasource", getDataSource());
//            parameterMap.put("PIdLpaUmum", dataCommon.getDataDetail().getLpaUmum().iterator().next().getId());
//            reportByte = JasperUtils.generate(parameterMap, jasperFile, getDataSource());
//
//            rptFormatLpaInt = new Attachment();
//            rptFormatLpaInt.setACL_ID(dataCommon.getSourceId());
//            rptFormatLpaInt.setORDERID(Long.parseLong(dataCommon.getOrderNumber()));
//            rptFormatLpaInt.setFILENAME(String.format(fileName, dataCommon.getOrderNumber()));
//            rptFormatLpaInt.setFILEEXTENSION("pdf");
//            rptFormatLpaInt.setFILECONTENT(reportByte);
//            rptFormatLpaInt.setFILENUMBER(fileNumber);
//            rptFormatLpaInt.setFILETOTAL(totalFile);
//
//            responseAtt = CarmAttachmentClientUtils.getInstance(url, username, password, rptFormatLpaInt);
//            Boolean sendStatus = responseAtt.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ATTACH_CARM);
//            sendToSourceAttachment.setStatus(sendStatus ? (totalFile==fileNumber?1:0) : 2);
//            sendToSourceAttachment.setMessage(responseAtt.getStatus());
//            sendToSourceAttachment.setResponse(responseAtt.getError_Code());
//            return sendStatus;
//        } catch (RemoteException e1) {
//            sendToSourceAttachment.setStatus(99);
//            sendToSourceAttachment.setMessage("Error Remote Exception");
//            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
//            e1.printStackTrace();
//            return false;
//        } catch (SQLException e1) {
//            sendToSourceAttachment.setStatus(99);
//            sendToSourceAttachment.setMessage("Error SQL Exception");
//            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
//            e1.printStackTrace();
//            return false;
//        } catch (MalformedURLException e1) {
//            sendToSourceAttachment.setStatus(99);
//            sendToSourceAttachment.setMessage("Error Malformed URL Exception");
//            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
//            e1.printStackTrace();
//            return false;
//        } catch (JRException e1) {
//            sendToSourceAttachment.setStatus(99);
//            sendToSourceAttachment.setMessage("Error Jasper Report Exception");
//            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
//            e1.printStackTrace();
//            return false;
//        } finally {
//            getSendToSourceLogAttachmenDao().save(sendToSourceAttachment);
//            if(rptFormatLpaInt != null) rptFormatLpaInt = null;
//            if(reportByte != null) reportByte = null;
//            if(parameterMap!=null) parameterMap.clear();
//        }
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
