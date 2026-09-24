package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SurveyKendaraanDto  implements Serializable {

    private Long id;
    @NotNull private Long detailId;
    private String category;
    @NotNull private String jenisKendaraan;
    @NotNull private String merkKendaraan;
    @NotNull private String modelType;
    @NotNull private Integer isiSilinder;
    @NotNull private Integer tahunPembuatan;
    @NotNull private String warna;
    @NotNull private String penggunaan;
    @NotNull private Double kilometer;
    @NotNull private String noPolisi;
    @NotNull private String noRangka;
    @NotNull private String noMesin;
    @NotNull private String bahanBakar;
    @NotNull private String catatan;

    public SurveyKendaraanDto(){
        super();
    }

    public SurveyKendaraanDto(Long detailId){
        this.detailId = detailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getMerkKendaraan() {
        return merkKendaraan;
    }

    public void setMerkKendaraan(String merkKendaraan) {
        this.merkKendaraan = merkKendaraan;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public Integer getIsiSilinder() {
        return isiSilinder;
    }

    public void setIsiSilinder(Integer isiSilinder) {
        this.isiSilinder = isiSilinder;
    }

    public Integer getTahunPembuatan() {
        return tahunPembuatan;
    }

    public void setTahunPembuatan(Integer tahunPembuatan) {
        this.tahunPembuatan = tahunPembuatan;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getPenggunaan() {
        return penggunaan;
    }

    public void setPenggunaan(String penggunaan) {
        this.penggunaan = penggunaan;
    }

    public Double getKilometer() {
        return kilometer;
    }

    public void setKilometer(Double kilometer) {
        this.kilometer = kilometer;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getNoRangka() {
        return noRangka;
    }

    public void setNoRangka(String noRangka) {
        this.noRangka = noRangka;
    }

    public String getNoMesin() {
        return noMesin;
    }

    public void setNoMesin(String noMesin) {
        this.noMesin = noMesin;
    }

    public String getBahanBakar() {
        return bahanBakar;
    }

    public void setBahanBakar(String bahanBakar) {
        this.bahanBakar = bahanBakar;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}