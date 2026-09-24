package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class SurveyCommonDto  implements Serializable {

    private Long id;
    @NotNull private Long detailId;
    @NotNull private String hasil;
    @NotNull private String jenisObject;
    @NotNull private String lokasi;
    private Long jenisPenilaian;
    private String category;
    private String activa;
    private String dipergunakan;
    private String dihuni;
    private String ditemui;
    private String ditinjau;
    @NotNull private Date tanggal;
    private String catatanSurvey;
    private String catatan;

    public SurveyCommonDto() {

    }

    public SurveyCommonDto(Long detailId) {
        this.detailId = detailId;
    }

    public SurveyCommonDto(Long detailId, String category, String marketing) {
        this.detailId = detailId;
        this.category = category;
        this.ditinjau = marketing;
    }

    public Long getJenisPenilaian() {
        return jenisPenilaian;
    }

    public void setJenisPenilaian(Long jenisPenilaian) {
        this.jenisPenilaian = jenisPenilaian;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
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

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getJenisObject() {
        return jenisObject;
    }

    public void setJenisObject(String jenisObject) {
        this.jenisObject = jenisObject;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDipergunakan() {
        return dipergunakan;
    }

    public void setDipergunakan(String dipergunakan) {
        this.dipergunakan = dipergunakan;
    }

    public String getDihuni() {
        return dihuni;
    }

    public void setDihuni(String dihuni) {
        this.dihuni = dihuni;
    }

    public String getDitemui() {
        return ditemui;
    }

    public void setDitemui(String ditemui) {
        this.ditemui = ditemui;
    }

    public String getDitinjau() {
        return ditinjau;
    }

    public void setDitinjau(String ditinjau) {
        this.ditinjau = ditinjau;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }


    public String getCatatanSurvey() {
        return catatanSurvey;
    }

    public void setCatatanSurvey(String catatanSurvey) {
        this.catatanSurvey = catatanSurvey;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

}