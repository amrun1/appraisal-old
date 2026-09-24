package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PendingJobsByUsersDto  implements Serializable {
    private String nama;
    private String posisi;
    private BigDecimal bd5;
    private BigDecimal bd4;
    private BigDecimal bd3;
    private BigDecimal bd2;
    private BigDecimal bd1;
    private BigDecimal bd0;
    private Integer param;
    //customers
    private String orders;
    private String alamat;
    private String kota;
    private Integer proses;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public BigDecimal getBd5() {
        return bd5;
    }

    public void setBd5(BigDecimal bd5) {
        this.bd5 = bd5;
    }

    public BigDecimal getBd4() {
        return bd4;
    }

    public void setBd4(BigDecimal bd4) {
        this.bd4 = bd4;
    }

    public BigDecimal getBd3() {
        return bd3;
    }

    public void setBd3(BigDecimal bd3) {
        this.bd3 = bd3;
    }

    public BigDecimal getBd2() {
        return bd2;
    }

    public void setBd2(BigDecimal bd2) {
        this.bd2 = bd2;
    }

    public BigDecimal getBd1() {
        return bd1;
    }

    public void setBd1(BigDecimal bd1) {
        this.bd1 = bd1;
    }

    public BigDecimal getBd0() {
        return bd0;
    }

    public void setBd0(BigDecimal bd0) {
        this.bd0 = bd0;
    }

    public Integer getParam() {
        return param;
    }

    public void setParam(Integer param) {
        this.param = param;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public Integer getProses() {
        return proses;
    }

    public void setProses(Integer proses) {
        this.proses = proses;
    }
}
