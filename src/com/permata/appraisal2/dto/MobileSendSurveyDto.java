package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileSendSurveyDto  implements Serializable {

    private String orderNo;
    private String collateralId;
    private String surveyCode;
    private String contactName;
    private String contactPhone;
    private String surveyAddress;
    private String debiturName;
    private String objectType;
    private String notes;
    private String jenisAppraisal;
    private String perumahanApartemen;
    private String clusterTower;
    private String blockLantai;
    private String nomor;
    private String rt;
    private String rw;
    private String status;

    //form
    private Date createdDate;
    private Date closedDate;
    private String closedBy;
    private String appraisalTypeName;

    private Date surveyDate;


    private String noorder;
    private String svyadd;
    private String svycode;
    private Date tanggal;
    private BigDecimal jml;
    private String surveyDateString;
    private String valuer;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCollateralId() {
        return collateralId;
    }

    public void setCollateralId(String collateralId) {
        this.collateralId = collateralId;
    }

    public String getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getSurveyAddress() {
        return surveyAddress;
    }

    public void setSurveyAddress(String surveyAddress) {
        this.surveyAddress = surveyAddress;
    }

    public String getDebiturName() {
        return debiturName;
    }

    public void setDebiturName(String debiturName) {
        this.debiturName = debiturName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getJenisAppraisal() {
        return jenisAppraisal;
    }

    public void setJenisAppraisal(String jenisAppraisal) {
        this.jenisAppraisal = jenisAppraisal;
    }

    public String getPerumahanApartemen() {
        return perumahanApartemen;
    }

    public void setPerumahanApartemen(String perumahanApartemen) {
        this.perumahanApartemen = perumahanApartemen;
    }

    public String getClusterTower() {
        return clusterTower;
    }

    public void setClusterTower(String clusterTower) {
        this.clusterTower = clusterTower;
    }

    public String getBlockLantai() {
        return blockLantai;
    }

    public void setBlockLantai(String blockLantai) {
        this.blockLantai = blockLantai;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }

    public String getAppraisalTypeName() {
        return appraisalTypeName;
    }

    public void setAppraisalTypeName(String appraisalTypeName) {
        this.appraisalTypeName = appraisalTypeName;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }


    public String getNoorder() {
        return noorder;
    }

    public void setNoorder(String noorder) {
        this.noorder = noorder;
    }

    public String getSvyadd() {
        return svyadd;
    }

    public void setSvyadd(String svyadd) {
        this.svyadd = svyadd;
    }

    public String getSvycode() {
        return svycode;
    }

    public void setSvycode(String svycode) {
        this.svycode = svycode;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getJml() {
        return jml;
    }

    public void setJml(BigDecimal jml) {
        this.jml = jml;
    }

    public String getSurveyDateString() {
        return surveyDateString;
    }

    public void setSurveyDateString(String surveyDateString) {
        this.surveyDateString = surveyDateString;
    }

    public String getValuer() {
        return valuer;
    }

    public void setValuer(String valuer) {
        this.valuer = valuer;
    }
}