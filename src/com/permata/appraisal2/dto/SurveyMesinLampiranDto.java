package com.permata.appraisal2.dto;

import java.io.Serializable;

public class SurveyMesinLampiranDto  implements Serializable {

    private Long id;
    private Long idSurveyMesin;
    private String jenis;
    private String merk;
    private String typeModel;
    private String noSeri;
    private String tahun;
    private String asal;
    private Integer jumlah;
    private String kegunaan;
    private String kondisi;
    private String rangkaian;
    private String penggunaan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSurveyMesin() {
        return idSurveyMesin;
    }

    public void setIdSurveyMesin(Long idSurveyMesin) {
        this.idSurveyMesin = idSurveyMesin;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTypeModel() {
        return typeModel;
    }

    public void setTypeModel(String typeModel) {
        this.typeModel = typeModel;
    }

    public String getNoSeri() {
        return noSeri;
    }

    public void setNoSeri(String noSeri) {
        this.noSeri = noSeri;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(String kegunaan) {
        this.kegunaan = kegunaan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getRangkaian() {
        return rangkaian;
    }

    public void setRangkaian(String rangkaian) {
        this.rangkaian = rangkaian;
    }

    public String getPenggunaan() {
        return penggunaan;
    }

    public void setPenggunaan(String penggunaan) {
        this.penggunaan = penggunaan;
    }
}