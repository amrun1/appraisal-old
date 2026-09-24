package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SurveyRvDto  implements Serializable {

    // additional
    private Long idJenisAppraisal;
    private Long commonId;
    private Long dataCommonId;

    private Long id;
    private Long detailId;
    private String alamat;
    private String vendor;
    private String ditinjau;
    private String umur;
    private String namaPasangan;
    private Boolean lokasiDitemukan;
    private Boolean sudahPindah;
    private String jam;

    // informasi 1 (S1)
    private Boolean s1Ditemukan;
    private String s1Nama;
    private String s1Kelamin;
    private String s1Hubungan;
    private Boolean s1Kooperatif;

    // informasi 2 (S2)
    private Boolean s2Ditemukan;
    private String s2Nama;
    private String s2Kelamin;
    private Boolean s2Kenal;
    private String s2Hubungan;
    private Boolean s2Kooperatif;

    // informasi 3 (S3)
    private Boolean s3Ditemukan;
    private String s3Nama;
    private String s3Kelamin;
    private Boolean s3Kenal;
    private String s3Hubungan;
    private Boolean s3Kooperatif;// informasi rumah
    private String surveyorDiterimaDi;
    private Boolean kondisiRumah;
    private String penghuni;
    private String lingkunganSekitar;
    private String luasBangunan;
    private String luasTanah;
    private String jenisBangunan;
    private String telepon;
    private Boolean garasi;
    private Boolean carport;
    private String lamaPenempatan;
    private String statusKepemilikan;

    // informasi tambahan
    private Boolean dikenalNamaSama;
    private String namaPanggilan;
    private String didatangiDebtCollector;
    private List<String> fotoPada;
    private String infoS1;
    private String infoS2;
    private String infoS3;
    private String note;

    private String createdBy;
    private Date createdDate;

    // foto-foto
    private LpaImageDto foto1;
    private LpaImageDto foto2;
    private LpaImageDto foto3;
    private LpaImageDto foto4;
    private LpaImageDto foto5;
    private LpaImageDto foto6;
    private LpaImageDto foto7;
    private LpaImageDto foto8;
    private LpaImageDto foto9;
    private LpaImageDto foto10;

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getNamaPasangan() {
        return namaPasangan;
    }

    public void setNamaPasangan(String namaPasangan) {
        this.namaPasangan = namaPasangan;
    }

    public Boolean getLokasiDitemukan() {
        return lokasiDitemukan;
    }

    public void setLokasiDitemukan(Boolean lokasiDitemukan) {
        this.lokasiDitemukan = lokasiDitemukan;
    }

    public Boolean getSudahPindah() {
        return sudahPindah;
    }

    public void setSudahPindah(Boolean sudahPindah) {
        this.sudahPindah = sudahPindah;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public Boolean getS1Ditemukan() {
        return s1Ditemukan;
    }

    public void setS1Ditemukan(Boolean s1Ditemukan) {
        this.s1Ditemukan = s1Ditemukan;
    }

    public String getS1Nama() {
        return s1Nama;
    }

    public void setS1Nama(String s1Nama) {
        this.s1Nama = s1Nama;
    }

    public String getS1Kelamin() {
        return s1Kelamin;
    }

    public void setS1Kelamin(String s1Kelamin) {
        this.s1Kelamin = s1Kelamin;
    }

    public String getS1Hubungan() {
        return s1Hubungan;
    }

    public void setS1Hubungan(String s1Hubungan) {
        this.s1Hubungan = s1Hubungan;
    }

    public Boolean getS1Kooperatif() {
        return s1Kooperatif;
    }

    public void setS1Kooperatif(Boolean s1Kooperatif) {
        this.s1Kooperatif = s1Kooperatif;
    }

    public Boolean getS2Ditemukan() {
        return s2Ditemukan;
    }

    public void setS2Ditemukan(Boolean s2Ditemukan) {
        this.s2Ditemukan = s2Ditemukan;
    }

    public String getS2Nama() {
        return s2Nama;
    }

    public void setS2Nama(String s2Nama) {
        this.s2Nama = s2Nama;
    }

    public String getS2Kelamin() {
        return s2Kelamin;
    }

    public void setS2Kelamin(String s2Kelamin) {
        this.s2Kelamin = s2Kelamin;
    }

    public Boolean getS2Kenal() {
        return s2Kenal;
    }

    public void setS2Kenal(Boolean s2Kenal) {
        this.s2Kenal = s2Kenal;
    }

    public String getS2Hubungan() {
        return s2Hubungan;
    }

    public void setS2Hubungan(String s2Hubungan) {
        this.s2Hubungan = s2Hubungan;
    }

    public Boolean getS2Kooperatif() {
        return s2Kooperatif;
    }

    public void setS2Kooperatif(Boolean s2Kooperatif) {
        this.s2Kooperatif = s2Kooperatif;
    }

    public Boolean getS3Ditemukan() {
        return s3Ditemukan;
    }

    public void setS3Ditemukan(Boolean s3Ditemukan) {
        this.s3Ditemukan = s3Ditemukan;
    }

    public String getS3Nama() {
        return s3Nama;
    }

    public void setS3Nama(String s3Nama) {
        this.s3Nama = s3Nama;
    }

    public String getS3Kelamin() {
        return s3Kelamin;
    }

    public void setS3Kelamin(String s3Kelamin) {
        this.s3Kelamin = s3Kelamin;
    }

    public Boolean getS3Kenal() {
        return s3Kenal;
    }

    public void setS3Kenal(Boolean s3Kenal) {
        this.s3Kenal = s3Kenal;
    }

    public String getS3Hubungan() {
        return s3Hubungan;
    }

    public void setS3Hubungan(String s3Hubungan) {
        this.s3Hubungan = s3Hubungan;
    }

    public Boolean getS3Kooperatif() {
        return s3Kooperatif;
    }

    public void setS3Kooperatif(Boolean s3Kooperatif) {
        this.s3Kooperatif = s3Kooperatif;
    }

    public String getSurveyorDiterimaDi() {
        return surveyorDiterimaDi;
    }

    public void setSurveyorDiterimaDi(String surveyorDiterimaDi) {
        this.surveyorDiterimaDi = surveyorDiterimaDi;
    }

    public Boolean getKondisiRumah() {
        return kondisiRumah;
    }

    public void setKondisiRumah(Boolean kondisiRumah) {
        this.kondisiRumah = kondisiRumah;
    }

    public String getPenghuni() {
        return penghuni;
    }

    public void setPenghuni(String penghuni) {
        this.penghuni = penghuni;
    }

    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    public String getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(String luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public String getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(String luasTanah) {
        this.luasTanah = luasTanah;
    }

    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public Boolean getGarasi() {
        return garasi;
    }

    public void setGarasi(Boolean garasi) {
        this.garasi = garasi;
    }

    public Boolean getCarport() {
        return carport;
    }

    public void setCarport(Boolean carport) {
        this.carport = carport;
    }

    public String getLamaPenempatan() {
        return lamaPenempatan;
    }

    public void setLamaPenempatan(String lamaPenempatan) {
        this.lamaPenempatan = lamaPenempatan;
    }

    public String getStatusKepemilikan() {
        return statusKepemilikan;
    }

    public void setStatusKepemilikan(String statusKepemilikan) {
        this.statusKepemilikan = statusKepemilikan;
    }

    public Boolean getDikenalNamaSama() {
        return dikenalNamaSama;
    }

    public void setDikenalNamaSama(Boolean dikenalNamaSama) {
        this.dikenalNamaSama = dikenalNamaSama;
    }

    public String getNamaPanggilan() {
        return namaPanggilan;
    }

    public void setNamaPanggilan(String namaPanggilan) {
        this.namaPanggilan = namaPanggilan;
    }

    public String getDidatangiDebtCollector() {
        return didatangiDebtCollector;
    }

    public void setDidatangiDebtCollector(String didatangiDebtCollector) {
        this.didatangiDebtCollector = didatangiDebtCollector;
    }

    public List<String> getFotoPada() {
        return fotoPada;
    }

    public void setFotoPada(List<String> fotoPada) {
        this.fotoPada = fotoPada;
    }

    public String getInfoS1() {
        return infoS1;
    }

    public void setInfoS1(String infoS1) {
        this.infoS1 = infoS1;
    }

    public String getInfoS2() {
        return infoS2;
    }

    public void setInfoS2(String infoS2) {
        this.infoS2 = infoS2;
    }

    public String getInfoS3() {
        return infoS3;
    }

    public void setInfoS3(String infoS3) {
        this.infoS3 = infoS3;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDitinjau() {
        return ditinjau;
    }

    public void setDitinjau(String ditinjau) {
        this.ditinjau = ditinjau;
    }

    public LpaImageDto getFoto1() {
        return foto1;
    }

    public void setFoto1(LpaImageDto foto1) {
        this.foto1 = foto1;
    }

    public LpaImageDto getFoto2() {
        return foto2;
    }

    public void setFoto2(LpaImageDto foto2) {
        this.foto2 = foto2;
    }

    public LpaImageDto getFoto3() {
        return foto3;
    }

    public void setFoto3(LpaImageDto foto3) {
        this.foto3 = foto3;
    }

    public LpaImageDto getFoto4() {
        return foto4;
    }

    public void setFoto4(LpaImageDto foto4) {
        this.foto4 = foto4;
    }

    public LpaImageDto getFoto5() {
        return foto5;
    }

    public void setFoto5(LpaImageDto foto5) {
        this.foto5 = foto5;
    }

    public LpaImageDto getFoto6() {
        return foto6;
    }

    public void setFoto6(LpaImageDto foto6) {
        this.foto6 = foto6;
    }

    public LpaImageDto getFoto7() {
        return foto7;
    }

    public void setFoto7(LpaImageDto foto7) {
        this.foto7 = foto7;
    }

    public LpaImageDto getFoto8() {
        return foto8;
    }

    public void setFoto8(LpaImageDto foto8) {
        this.foto8 = foto8;
    }

    public LpaImageDto getFoto9() {
        return foto9;
    }

    public void setFoto9(LpaImageDto foto9) {
        this.foto9 = foto9;
    }

    public LpaImageDto getFoto10() {
        return foto10;
    }

    public void setFoto10(LpaImageDto foto10) {
        this.foto10 = foto10;
    }

    public Long getIdJenisAppraisal() {
        return idJenisAppraisal;
    }

    public void setIdJenisAppraisal(Long idJenisAppraisal) {
        this.idJenisAppraisal = idJenisAppraisal;
    }

    public Long getDataCommonId() {
        return dataCommonId;
    }

    public void setDataCommonId(Long dataCommonId) {
        this.dataCommonId = dataCommonId;
    }
}