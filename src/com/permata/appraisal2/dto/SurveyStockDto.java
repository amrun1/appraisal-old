package com.permata.appraisal2.dto;

import java.io.Serializable;


public class SurveyStockDto  implements Serializable {

    private Long id;
    private Long idDetail;
    private String jenisPersedian;
    private String penempatanBarang;
    private String kemasanBarang;
    private String kondisiBarang;
    private String metodePemeriksaan;
    private String jumlahPersedian;
    private String asalPembuatan;
    private String kondisiPenyimpanan;
    private String lingkunganSekitar;
    private String peralatanGudang;
    private String catatan;

    public SurveyStockDto() {
    }

    public SurveyStockDto(Long idDetail) {
        this.idDetail = idDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public String getJenisPersedian() {
        return jenisPersedian;
    }

    public void setJenisPersedian(String jenisPersedian) {
        this.jenisPersedian = jenisPersedian;
    }

    public String getPenempatanBarang() {
        return penempatanBarang;
    }

    public void setPenempatanBarang(String penempatanBarang) {
        this.penempatanBarang = penempatanBarang;
    }

    public String getKemasanBarang() {
        return kemasanBarang;
    }

    public void setKemasanBarang(String kemasanBarang) {
        this.kemasanBarang = kemasanBarang;
    }

    public String getKondisiBarang() {
        return kondisiBarang;
    }

    public void setKondisiBarang(String kondisiBarang) {
        this.kondisiBarang = kondisiBarang;
    }

    public String getMetodePemeriksaan() {
        return metodePemeriksaan;
    }

    public void setMetodePemeriksaan(String metodePemeriksaan) {
        this.metodePemeriksaan = metodePemeriksaan;
    }

    public String getJumlahPersedian() {
        return jumlahPersedian;
    }

    public void setJumlahPersedian(String jumlahPersedian) {
        this.jumlahPersedian = jumlahPersedian;
    }

    public String getAsalPembuatan() {
        return asalPembuatan;
    }

    public void setAsalPembuatan(String asalPembuatan) {
        this.asalPembuatan = asalPembuatan;
    }

    public String getKondisiPenyimpanan() {
        return kondisiPenyimpanan;
    }

    public void setKondisiPenyimpanan(String kondisiPenyimpanan) {
        this.kondisiPenyimpanan = kondisiPenyimpanan;
    }

    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    public String getPeralatanGudang() {
        return peralatanGudang;
    }

    public void setPeralatanGudang(String peralatanGudang) {
        this.peralatanGudang = peralatanGudang;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
