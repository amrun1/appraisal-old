package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Abdul Rochman on 4/25/2017.
 */
public class ViewReportJenisJaminanDto  implements Serializable {
    private String statusjaminan;
    private int bulan;
    private int tahun;
    private String groupmastercabang;
    private int sumorder;
    private Date tglgrouporder;

    public String getStatusjaminan() {
        return statusjaminan;
    }

    public void setStatusjaminan(String statusjaminan) {
        this.statusjaminan = statusjaminan;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getGroupmastercabang() {
        return groupmastercabang;
    }

    public void setGroupmastercabang(String groupmastercabang) {
        this.groupmastercabang = groupmastercabang;
    }

    public int getSumorder() {
        return sumorder;
    }

    public void setSumorder(int sumorder) {
        this.sumorder = sumorder;
    }

    public Date getTglgrouporder() {
        return tglgrouporder;
    }

    public void setTglgrouporder(Date tglgrouporder) {
        this.tglgrouporder = tglgrouporder;
    }
}
