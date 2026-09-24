package com.permata.appraisal2.dto;

import com.permata.appraisal2.model.SurveyStatus;

import java.io.Serializable;
import java.util.Date;

public class DataSurveyDto  implements Serializable {

    private Long id;
    private Long dataDetailId;
    private String debiturName;
    private String contactName;
    private String contactPhone;
    private String day;
    private String note;
    private String appraisalType;
    private String assetType;
    private String assetName;
    private SurveyStatus status;
    private String description;
    private String createby;
    private String createName;
    private Date createdate;
    private String result;
    private String complexApartment;
    private String clusterTower;
    private String blockFloor;
    private String number;
    private String rt;
    private String rw;

    public Long getDataDetailId() {
        return dataDetailId;
    }

    public void setDataDetailId(Long dataDetailId) {
        this.dataDetailId = dataDetailId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDebiturName() {
        return debiturName;
    }

    public void setDebiturName(String debiturName) {
        this.debiturName = debiturName;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAppraisalType() {
        return appraisalType;
    }

    public void setAppraisalType(String appraisalType) {
        this.appraisalType = appraisalType;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public SurveyStatus getStatus() {
        return status;
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComplexApartment() {
        return complexApartment;
    }

    public void setComplexApartment(String complexApartment) {
        this.complexApartment = complexApartment;
    }

    public String getClusterTower() {
        return clusterTower;
    }

    public void setClusterTower(String clusterTower) {
        this.clusterTower = clusterTower;
    }

    public String getBlockFloor() {
        return blockFloor;
    }

    public void setBlockFloor(String blockFloor) {
        this.blockFloor = blockFloor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
}