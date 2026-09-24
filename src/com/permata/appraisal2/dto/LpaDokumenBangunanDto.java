package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class LpaDokumenBangunanDto  implements Serializable {

    private Long id;
    private Long commonId;
    private String nomorImb;
     private Date tanggalImb;
     private String ijinSesuaiImb;
     private Double luasBangunan;
     private Double jumlahLantai;

     private String luasBangunanStr;

    public String getLuasBangunanStr() {
        return luasBangunanStr;
    }

    public void setLuasBangunanStr(String luasBangunanStr) {
        this.luasBangunanStr = luasBangunanStr;
    }

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomorImb() {
        return nomorImb;
    }

    public void setNomorImb(String nomorImb) {
        this.nomorImb = nomorImb;
    }

    public Date getTanggalImb() {
        return tanggalImb;
    }

    public void setTanggalImb(Date tanggalImb) {
        this.tanggalImb = tanggalImb;
    }

    public String getIjinSesuaiImb() {
        return ijinSesuaiImb;
    }

    public void setIjinSesuaiImb(String ijinSesuaiImb) {
        this.ijinSesuaiImb = ijinSesuaiImb;
    }

    public Double getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(Double luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public Double getJumlahLantai() {
        return jumlahLantai;
    }

    public void setJumlahLantai(Double jumlahLantai) {
        this.jumlahLantai = jumlahLantai;
    }
}