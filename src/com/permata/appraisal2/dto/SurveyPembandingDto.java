package com.permata.appraisal2.dto;

import java.io.Serializable;

public class SurveyPembandingDto  implements Serializable {

    private Long id;
    private Long detailId;
    private String nama;
    private String telepon;
    private String jenisObject;
    private String alamat;
    private String kondisiBangunan;
    private String kondisiLingkungan;
    private String keterangan;
    private byte[] image;

    public SurveyPembandingDto() {
    }

    public SurveyPembandingDto(Long detailId) {
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getJenisObject() {
        return jenisObject;
    }

    public void setJenisObject(String jenisObject) {
        this.jenisObject = jenisObject;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKondisiBangunan() {
        return kondisiBangunan;
    }

    public void setKondisiBangunan(String kondisiBangunan) {
        this.kondisiBangunan = kondisiBangunan;
    }

    public String getKondisiLingkungan() {
        return kondisiLingkungan;
    }

    public void setKondisiLingkungan(String kondisiLingkungan) {
        this.kondisiLingkungan = kondisiLingkungan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}