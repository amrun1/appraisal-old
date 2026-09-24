package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LpaMesinDto extends LpaUmumDto  implements Serializable {

    private Long id;
    @NotNull private String rangkaian;
    @NotNull private String tempat;
    @NotNull private String kondisi;
    @NotNull private String lingkungan;
    @NotNull private String teknisi;
    @NotNull private String cukuCadang;
    @NotNull private String waktuPemakaian;

    @NotNull private Integer jumlahUnit;

    @NotNull private Double likuidasi;
    @NotNull private Boolean manual;
    @NotNull private Boolean beriNilaiDoc;
    @NotNull private Boolean beriNilaiFisik;
    @NotNull private BigDecimal nilaiDocPasar;
    @NotNull private BigDecimal nilaiFisikPasar;
    @NotNull private BigDecimal nilaiDocLikuidasi;
    @NotNull private BigDecimal nilaiFisikLikuidasi;

    private Double likuidasiEks;
    private Boolean manualEks;
    private Boolean beriNilaiDocEks;
    private Boolean beriNilaiFisikEks;
    private BigDecimal nilaiDocPasarEks;
    private BigDecimal nilaiFisikPasarEks;
    private BigDecimal nilaiDocLikuidasiEks;
    private BigDecimal nilaiFisikLikuidasiEks;

    private List<LpaMesinLampiranDto> lampiran;

    public List<LpaMesinLampiranDto> getLampiran() {
        return lampiran;
    }

    public void setLampiran(List<LpaMesinLampiranDto> lampiran) {
        this.lampiran = lampiran;
    }

    public Integer getJumlahUnit() {
        return jumlahUnit;
    }

    public void setJumlahUnit(Integer jumlahUnit) {
        this.jumlahUnit = jumlahUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRangkaian() {
        return rangkaian;
    }

    public void setRangkaian(String rangkaian) {
        this.rangkaian = rangkaian;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getLingkungan() {
        return lingkungan;
    }

    public void setLingkungan(String lingkungan) {
        this.lingkungan = lingkungan;
    }

    public String getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(String teknisi) {
        this.teknisi = teknisi;
    }

    public String getCukuCadang() {
        return cukuCadang;
    }

    public void setCukuCadang(String cukuCadang) {
        this.cukuCadang = cukuCadang;
    }

    public String getWaktuPemakaian() {
        return waktuPemakaian;
    }

    public void setWaktuPemakaian(String waktuPemakaian) {
        this.waktuPemakaian = waktuPemakaian;
    }

    public Double getLikuidasi() {
        return likuidasi;
    }

    public void setLikuidasi(Double likuidasi) {
        this.likuidasi = likuidasi;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Boolean getBeriNilaiDoc() {
        return beriNilaiDoc;
    }

    public void setBeriNilaiDoc(Boolean beriNilaiDoc) {
        this.beriNilaiDoc = beriNilaiDoc;
    }

    public Boolean getBeriNilaiFisik() {
        return beriNilaiFisik;
    }

    public void setBeriNilaiFisik(Boolean beriNilaiFisik) {
        this.beriNilaiFisik = beriNilaiFisik;
    }

    public BigDecimal getNilaiDocPasar() {
        return nilaiDocPasar;
    }

    public void setNilaiDocPasar(BigDecimal nilaiDocPasar) {
        this.nilaiDocPasar = nilaiDocPasar;
    }

    public BigDecimal getNilaiFisikPasar() {
        return nilaiFisikPasar;
    }

    public void setNilaiFisikPasar(BigDecimal nilaiFisikPasar) {
        this.nilaiFisikPasar = nilaiFisikPasar;
    }

    public BigDecimal getNilaiDocLikuidasi() {
        return nilaiDocLikuidasi;
    }

    public void setNilaiDocLikuidasi(BigDecimal nilaiDocLikuidasi) {
        this.nilaiDocLikuidasi = nilaiDocLikuidasi;
    }

    public BigDecimal getNilaiFisikLikuidasi() {
        return nilaiFisikLikuidasi;
    }

    public void setNilaiFisikLikuidasi(BigDecimal nilaiFisikLikuidasi) {
        this.nilaiFisikLikuidasi = nilaiFisikLikuidasi;
    }

    public Double getLikuidasiEks() {
        return likuidasiEks;
    }

    public void setLikuidasiEks(Double likuidasiEks) {
        this.likuidasiEks = likuidasiEks;
    }

    public Boolean getManualEks() {
        return manualEks;
    }

    public void setManualEks(Boolean manualEks) {
        this.manualEks = manualEks;
    }

    public Boolean getBeriNilaiDocEks() {
        return beriNilaiDocEks;
    }

    public void setBeriNilaiDocEks(Boolean beriNilaiDocEks) {
        this.beriNilaiDocEks = beriNilaiDocEks;
    }

    public Boolean getBeriNilaiFisikEks() {
        return beriNilaiFisikEks;
    }

    public void setBeriNilaiFisikEks(Boolean beriNilaiFisikEks) {
        this.beriNilaiFisikEks = beriNilaiFisikEks;
    }

    public BigDecimal getNilaiDocPasarEks() {
        return nilaiDocPasarEks;
    }

    public void setNilaiDocPasarEks(BigDecimal nilaiDocPasarEks) {
        this.nilaiDocPasarEks = nilaiDocPasarEks;
    }

    public BigDecimal getNilaiFisikPasarEks() {
        return nilaiFisikPasarEks;
    }

    public void setNilaiFisikPasarEks(BigDecimal nilaiFisikPasarEks) {
        this.nilaiFisikPasarEks = nilaiFisikPasarEks;
    }

    public BigDecimal getNilaiDocLikuidasiEks() {
        return nilaiDocLikuidasiEks;
    }

    public void setNilaiDocLikuidasiEks(BigDecimal nilaiDocLikuidasiEks) {
        this.nilaiDocLikuidasiEks = nilaiDocLikuidasiEks;
    }

    public BigDecimal getNilaiFisikLikuidasiEks() {
        return nilaiFisikLikuidasiEks;
    }

    public void setNilaiFisikLikuidasiEks(BigDecimal nilaiFisikLikuidasiEks) {
        this.nilaiFisikLikuidasiEks = nilaiFisikLikuidasiEks;
    }
}