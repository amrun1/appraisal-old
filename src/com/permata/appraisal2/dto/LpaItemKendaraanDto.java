package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LpaItemKendaraanDto  implements Serializable {

    private Long id;
    private Long lpaId;
    private Long lpaKendaraanId;
    @NotNull private String jenisKendaraan;
    @NotNull private String merkKendaraan;
    @NotNull private String modelType;
    @NotNull private Integer isiSilinder;
    @NotNull private Integer tahunPembuatan;
    @NotNull private String asalPembuatan;
    @NotNull private String warna;
    @NotNull private String noPolisi;
    @NotNull private String noRangka;
    @NotNull private String noMesin;
    @NotNull private String bahanBakar;

    // dokumen kendaraan
    @NotNull private String noBpkb;
    @NotNull private String pajakStnk;
    @NotNull private Date jatuhTempoStnk;
    @NotNull private Date jatuhTempoPajak;
    @NotNull private String noFaktur;
    @NotNull private Date tanggalBpkb;
    @NotNull private String atasNamaBpkb;
    @NotNull private String alamatBpkb;

    // fungsi dan kondisi kendaraan
    @NotNull private String penggunaan;
    @NotNull private String pemakaian;
    @NotNull private Double kilometer;
    @NotNull private String body;
    @NotNull private String bumper;
    @NotNull private String cat;
    @NotNull private String interior;
    @NotNull private String eksterior;
    @NotNull private String mesin;
    @NotNull private String ban;
    @NotNull private String catatan;

    // penilaian agunan
    @NotNull private BigDecimal nilaiPasar;
    @NotNull private BigDecimal nilaiDokumen;

    public LpaItemKendaraanDto() {
        super();
    }

    public LpaItemKendaraanDto(Long lpaKendaraanId) {
        this.lpaKendaraanId = lpaKendaraanId;
    }

    public Date getJatuhTempoStnk() {
        return jatuhTempoStnk;
    }

    public void setJatuhTempoStnk(Date jatuhTempoStnk) {
        this.jatuhTempoStnk = jatuhTempoStnk;
    }

    public Date getJatuhTempoPajak() {
        return jatuhTempoPajak;
    }

    public void setJatuhTempoPajak(Date jatuhTempoPajak) {
        this.jatuhTempoPajak = jatuhTempoPajak;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public Date getTanggalBpkb() {
        return tanggalBpkb;
    }

    public void setTanggalBpkb(Date tanggalBpkb) {
        this.tanggalBpkb = tanggalBpkb;
    }

    public String getAtasNamaBpkb() {
        return atasNamaBpkb;
    }

    public void setAtasNamaBpkb(String atasNamaBpkb) {
        this.atasNamaBpkb = atasNamaBpkb;
    }

    public String getAlamatBpkb() {
        return alamatBpkb;
    }

    public void setAlamatBpkb(String alamatBpkb) {
        this.alamatBpkb = alamatBpkb;
    }

    public String getPemakaian() {
        return pemakaian;
    }

    public void setPemakaian(String pemakaian) {
        this.pemakaian = pemakaian;
    }

    public String getBumper() {
        return bumper;
    }

    public void setBumper(String bumper) {
        this.bumper = bumper;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getEksterior() {
        return eksterior;
    }

    public void setEksterior(String eksterior) {
        this.eksterior = eksterior;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public BigDecimal getNilaiPasar() {
        return nilaiPasar;
    }

    public void setNilaiPasar(BigDecimal nilaiPasar) {
        this.nilaiPasar = nilaiPasar;
    }

    public BigDecimal getNilaiDokumen() {
        return nilaiDokumen;
    }

    public void setNilaiDokumen(BigDecimal nilaiDokumen) {
        this.nilaiDokumen = nilaiDokumen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLpaId() {
        return lpaId;
    }

    public void setLpaId(Long lpaId) {
        this.lpaId = lpaId;
    }

    public Long getLpaKendaraanId() {
        return lpaKendaraanId;
    }

    public void setLpaKendaraanId(Long lpaKendaraanId) {
        this.lpaKendaraanId = lpaKendaraanId;
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

    public String getAsalPembuatan() {
        return asalPembuatan;
    }

    public void setAsalPembuatan(String asalPembuatan) {
        this.asalPembuatan = asalPembuatan;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
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

    public String getNoBpkb() {
        return noBpkb;
    }

    public void setNoBpkb(String noBpkb) {
        this.noBpkb = noBpkb;
    }

    public String getPajakStnk() {
        return pajakStnk;
    }

    public void setPajakStnk(String pajakStnk) {
        this.pajakStnk = pajakStnk;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getMesin() {
        return mesin;
    }

    public void setMesin(String mesin) {
        this.mesin = mesin;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

}