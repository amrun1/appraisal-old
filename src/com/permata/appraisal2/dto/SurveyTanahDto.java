package com.permata.appraisal2.dto;

import java.io.Serializable;

public class SurveyTanahDto  implements Serializable {

    private Long id;
    private Long detailId;
    private Double luas;
    private String bentuk;
    private String kontur;
    private String ketinggian;
    private String batas;
    private String posisi;
    private String kondisi;
    private String jenis;
    private String utara;
    private String selatan;
    private String timur;
    private String barat;

    private String peruntukanTanah;

    public String getPeruntukanTanah() {
        return peruntukanTanah;
    }

    public void setPeruntukanTanah(String peruntukanTanah) {
        this.peruntukanTanah = peruntukanTanah;
    }

    public SurveyTanahDto() {

    }

    public SurveyTanahDto(Long detailId) {
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

    public Double getLuas() {
        return luas;
    }

    public void setLuas(Double luas) {
        this.luas = luas;
    }

    public String getBentuk() {
        return bentuk;
    }

    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }

    public String getKontur() {
        return kontur;
    }

    public void setKontur(String kontur) {
        this.kontur = kontur;
    }

    public String getKetinggian() {
        return ketinggian;
    }

    public void setKetinggian(String ketinggian) {
        this.ketinggian = ketinggian;
    }

    public String getBatas() {
        return batas;
    }

    public void setBatas(String batas) {
        this.batas = batas;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getUtara() {
        return utara;
    }

    public void setUtara(String utara) {
        this.utara = utara;
    }

    public String getSelatan() {
        return selatan;
    }

    public void setSelatan(String selatan) {
        this.selatan = selatan;
    }

    public String getTimur() {
        return timur;
    }

    public void setTimur(String timur) {
        this.timur = timur;
    }

    public String getBarat() {
        return barat;
    }

    public void setBarat(String barat) {
        this.barat = barat;
    }
}