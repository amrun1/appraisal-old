package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LpaMesinLampiranDto  implements Serializable {

    private Long id;
    @NotNull private Long idLpaMesin;

    @NotNull private String jenis;
    @NotNull private String merk;
    @NotNull private String typeModel;
    @NotNull private String noSeri;
    @NotNull private String asal;
    @NotNull private String tahun;
    @NotNull private String kegunaan;
    @NotNull private String kondisi;
    @NotNull private String rangkaian;

    @NotNull private String noInvoice;
    @NotNull private Date tanggal;
    @NotNull private String pemilik;

    @NotNull private BigDecimal nilaiDoc;
    @NotNull private BigDecimal nilaiFisik;

    public LpaMesinLampiranDto() {
        super();
    }

    public LpaMesinLampiranDto(Long idLpaMesin) {
        this.idLpaMesin = idLpaMesin;
    }

    public String getRangkaian() {
        return rangkaian;
    }

    public void setRangkaian(String rangkaian) {
        this.rangkaian = rangkaian;
    }

    public BigDecimal getNilaiDoc() {
        return nilaiDoc;
    }

    public void setNilaiDoc(BigDecimal nilaiDoc) {
        this.nilaiDoc = nilaiDoc;
    }

    public BigDecimal getNilaiFisik() {
        return nilaiFisik;
    }

    public void setNilaiFisik(BigDecimal nilaiFisik) {
        this.nilaiFisik = nilaiFisik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLpaMesin() {
        return idLpaMesin;
    }

    public void setIdLpaMesin(Long idLpaMesin) {
        this.idLpaMesin = idLpaMesin;
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

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
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

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }
}