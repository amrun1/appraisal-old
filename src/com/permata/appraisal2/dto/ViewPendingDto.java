package com.permata.appraisal2.dto;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Abdul Rochman on 4/25/2017.
 */
public class ViewPendingDto  implements Serializable {
    private String cabang;
    private String statusjaminan;
    private int tahun;
    private int bulan;
    private Date tglgrouporder;
    private List<DataDto> data;
    private String tglstring;

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getStatusjaminan() {
        return statusjaminan;
    }

    public void setStatusjaminan(String statusjaminan) {
        this.statusjaminan = statusjaminan;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public Date getTglgrouporder() {
        return tglgrouporder;
    }

    public void setTglgrouporder(Date tglgrouporder) {
        this.tglgrouporder = tglgrouporder;
    }

    public List<DataDto> getData() {
        return data;
    }

    public void setData(List<DataDto> data) {
        this.data = data;
    }

    public String getTglstring() {
        return tglstring;
    }

    public void setTglstring(String tglstring) {
        this.tglstring = tglstring;
    }
}
