package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.Date;

public class SecurityLogDto  implements Serializable {

    private Long id;
    private String personId;
    private Date dateTime;
    private String ipAddress;
    private Long logCode;
    private String keterangan;
    private String dateFormatJson;

    private String computerName;
    private String detail;
    private String userAffected;
    private String appName;


    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUserAffected() {
        return userAffected;
    }

    public void setUserAffected(String userAffected) {
        this.userAffected = userAffected;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDateFormatJson() {
        return dateFormatJson;
    }

    public void setDateFormatJson(String dateFormatJson) {
        this.dateFormatJson = dateFormatJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getLogCode() {
        return logCode;
    }

    public void setLogCode(Long logCode) {
        this.logCode = logCode;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}