package com.permata.appraisal2.dto;

import java.io.Serializable;

public class SurveyBangunanDto  implements Serializable {

    private Long id;
    private Long detailId;
    private Double luas;
    private Double lantai1;
    private Double lantai2;
    private Double lantai3;
    private Double lantai4;
    private Double lantai5;
    private Boolean manual;
    private Integer jumlahLantai;
    private Integer jumlahLantaiTampakDepan;
    private String peruntukan;
    private String kelas;
    private String jenis;
    private Integer tahunDibangun;
    private Integer tahunRenovasi;
    private String kondisiUmum;

    public SurveyBangunanDto() {
    }

    public SurveyBangunanDto(Long detailId) {
        super();
        this.detailId = detailId;
    }

    public static SurveyBangunanDto create(Long detailId) {
        SurveyBangunanDto dto = new SurveyBangunanDto();
        dto.setLuas(null);
        dto.setLantai1(null);
        dto.setLantai2(null);
        dto.setLantai3(null);
        dto.setLantai4(null);
        dto.setLantai5(null);
        dto.setManual(Boolean.FALSE);
        dto.setJumlahLantai(0);
        dto.setJumlahLantaiTampakDepan(0);
        dto.setDetailId(detailId);
        return dto;
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

    public Double getLantai1() {
        return lantai1;
    }

    public void setLantai1(Double lantai1) {
        this.lantai1 = lantai1;
    }

    public Double getLantai2() {
        return lantai2;
    }

    public void setLantai2(Double lantai2) {
        this.lantai2 = lantai2;
    }

    public Double getLantai3() {
        return lantai3;
    }

    public void setLantai3(Double lantai3) {
        this.lantai3 = lantai3;
    }

    public Double getLantai4() {
        return lantai4;
    }

    public void setLantai4(Double lantai4) {
        this.lantai4 = lantai4;
    }

    public Double getLantai5() {
        return lantai5;
    }

    public void setLantai5(Double lantai5) {
        this.lantai5 = lantai5;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Integer getJumlahLantai() {
        return jumlahLantai;
    }

    public void setJumlahLantai(Integer jumlahLantai) {
        this.jumlahLantai = jumlahLantai;
    }

    public Integer getJumlahLantaiTampakDepan() {
        return jumlahLantaiTampakDepan;
    }

    public void setJumlahLantaiTampakDepan(Integer jumlahLantaiTampakDepan) {
        this.jumlahLantaiTampakDepan = jumlahLantaiTampakDepan;
    }

    public String getPeruntukan() {
        return peruntukan;
    }

    public void setPeruntukan(String peruntukan) {
        this.peruntukan = peruntukan;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Integer getTahunDibangun() {
        return tahunDibangun;
    }

    public void setTahunDibangun(Integer tahunDibangun) {
        this.tahunDibangun = tahunDibangun;
    }

    public Integer getTahunRenovasi() {
        return tahunRenovasi;
    }

    public void setTahunRenovasi(Integer tahunRenovasi) {
        this.tahunRenovasi = tahunRenovasi;
    }

    public String getKondisiUmum() {
        return kondisiUmum;
    }

    public void setKondisiUmum(String kondisiUmum) {
        this.kondisiUmum = kondisiUmum;
    }
}