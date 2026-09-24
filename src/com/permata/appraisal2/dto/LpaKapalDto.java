package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LpaKapalDto extends LpaUmumDto  implements Serializable {

    private Long id;
    @NotNull private String nama;
    @NotNull private String pemilik;
    @NotNull private String kebangsaan;
    @NotNull private String selar;
    @NotNull private String galangan;
    @NotNull private String kelas;
    @NotNull private Integer tahun;
    @NotNull private String manufaktur;
    @NotNull private Double panjang;
    @NotNull private Double lebar;
    @NotNull private Double tinggi;
    @NotNull private Double isiKotor;
    @NotNull private Double isiBersih;
    @NotNull private String statusAgunan;
    @NotNull private String statusJaminanKapal;
    @NotNull private String kondisiUmum;

    // data Mesin LpaKapal
    @NotNull private String mesinMerk1;
    @NotNull private String mesinMerk2;
    @NotNull private String mesinMerk3;
    @NotNull private String mesinNomor1;
    @NotNull private String mesinNomor2;
    @NotNull private String mesinNomor3;
    @NotNull private String mesinKapasitas1;
    @NotNull private String mesinKapasitas2;
    @NotNull private String mesinKapasitas3;
    @NotNull private String mesinBackupMerk1;
    @NotNull private String mesinBackupMerk2;
    @NotNull private String mesinBackupNomor1;
    @NotNull private String mesinBackupNomor2;
    @NotNull private String mesinBackupKapasitas1;
    @NotNull private String mesinBackupKapasitas2;

    // data Legalitas LpaKapal
    @NotNull private String jenis;
    @NotNull private String noGrossAkta;
    @NotNull private Date tanggalGrossAkta;
    @NotNull private String noSuratUkur;
    @NotNull private Date tanggalSuratUkur;
    @NotNull private String noSertifikatLambung;
    @NotNull private Date tanggalSertifikatLambung;
    @NotNull private Date expiredSertifikatLambung;
    @NotNull private String noSertifikatGaris;
    @NotNull private Date tanggalSertifikatGaris;
    @NotNull private String noSertifikatMesin;
    @NotNull private Date tanggalSertifikatMesin;
    @NotNull private Date expiredSertifikatMesin;


    private Double likuidasi;
    private Boolean manual;
    private Boolean beriNilaiDoc;
    private Boolean beriNilaiFisik;
    private BigDecimal nilaiDocPasar;
    private BigDecimal nilaiFisikPasar;
    private BigDecimal nilaiDocLikuidasi;
    private BigDecimal nilaiFisikLikuidasi;

    private Double likuidasiEks;
    private Boolean manualEks;
    private Boolean beriNilaiDocEks;
    private Boolean beriNilaiFisikEks;
    private BigDecimal nilaiDocPasarEks;
    private BigDecimal nilaiFisikPasarEks;
    private BigDecimal nilaiDocLikuidasiEks;
    private BigDecimal nilaiFisikLikuidasiEks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getKebangsaan() {
        return kebangsaan;
    }

    public void setKebangsaan(String kebangsaan) {
        this.kebangsaan = kebangsaan;
    }

    public String getSelar() {
        return selar;
    }

    public void setSelar(String selar) {
        this.selar = selar;
    }

    public String getGalangan() {
        return galangan;
    }

    public void setGalangan(String galangan) {
        this.galangan = galangan;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public String getManufaktur() {
        return manufaktur;
    }

    public void setManufaktur(String manufaktur) {
        this.manufaktur = manufaktur;
    }

    public Double getPanjang() {
        return panjang;
    }

    public void setPanjang(Double panjang) {
        this.panjang = panjang;
    }

    public Double getLebar() {
        return lebar;
    }

    public void setLebar(Double lebar) {
        this.lebar = lebar;
    }

    public Double getTinggi() {
        return tinggi;
    }

    public void setTinggi(Double tinggi) {
        this.tinggi = tinggi;
    }

    public Double getIsiKotor() {
        return isiKotor;
    }

    public void setIsiKotor(Double isiKotor) {
        this.isiKotor = isiKotor;
    }

    public Double getIsiBersih() {
        return isiBersih;
    }

    public void setIsiBersih(Double isiBersih) {
        this.isiBersih = isiBersih;
    }

    public String getStatusAgunan() {
        return statusAgunan;
    }

    public void setStatusAgunan(String statusAgunan) {
        this.statusAgunan = statusAgunan;
    }

    public String getKondisiUmum() {
        return kondisiUmum;
    }

    public void setKondisiUmum(String kondisiUmum) {
        this.kondisiUmum = kondisiUmum;
    }

    public String getMesinMerk1() {
        return mesinMerk1;
    }

    public void setMesinMerk1(String mesinMerk1) {
        this.mesinMerk1 = mesinMerk1;
    }

    public String getMesinMerk2() {
        return mesinMerk2;
    }

    public void setMesinMerk2(String mesinMerk2) {
        this.mesinMerk2 = mesinMerk2;
    }

    public String getMesinMerk3() {
        return mesinMerk3;
    }

    public void setMesinMerk3(String mesinMerk3) {
        this.mesinMerk3 = mesinMerk3;
    }

    public String getMesinNomor1() {
        return mesinNomor1;
    }

    public void setMesinNomor1(String mesinNomor1) {
        this.mesinNomor1 = mesinNomor1;
    }

    public String getMesinNomor2() {
        return mesinNomor2;
    }

    public void setMesinNomor2(String mesinNomor2) {
        this.mesinNomor2 = mesinNomor2;
    }

    public String getMesinNomor3() {
        return mesinNomor3;
    }

    public void setMesinNomor3(String mesinNomor3) {
        this.mesinNomor3 = mesinNomor3;
    }

    public String getMesinKapasitas1() {
        return mesinKapasitas1;
    }

    public void setMesinKapasitas1(String mesinKapasitas1) {
        this.mesinKapasitas1 = mesinKapasitas1;
    }

    public String getMesinKapasitas2() {
        return mesinKapasitas2;
    }

    public void setMesinKapasitas2(String mesinKapasitas2) {
        this.mesinKapasitas2 = mesinKapasitas2;
    }

    public String getMesinKapasitas3() {
        return mesinKapasitas3;
    }

    public void setMesinKapasitas3(String mesinKapasitas3) {
        this.mesinKapasitas3 = mesinKapasitas3;
    }

    public String getMesinBackupMerk1() {
        return mesinBackupMerk1;
    }

    public void setMesinBackupMerk1(String mesinBackupMerk1) {
        this.mesinBackupMerk1 = mesinBackupMerk1;
    }

    public String getMesinBackupMerk2() {
        return mesinBackupMerk2;
    }

    public void setMesinBackupMerk2(String mesinBackupMerk2) {
        this.mesinBackupMerk2 = mesinBackupMerk2;
    }

    public String getMesinBackupNomor1() {
        return mesinBackupNomor1;
    }

    public void setMesinBackupNomor1(String mesinBackupNomor1) {
        this.mesinBackupNomor1 = mesinBackupNomor1;
    }

    public String getMesinBackupNomor2() {
        return mesinBackupNomor2;
    }

    public void setMesinBackupNomor2(String mesinBackupNomor2) {
        this.mesinBackupNomor2 = mesinBackupNomor2;
    }

    public String getMesinBackupKapasitas1() {
        return mesinBackupKapasitas1;
    }

    public void setMesinBackupKapasitas1(String mesinBackupKapasitas1) {
        this.mesinBackupKapasitas1 = mesinBackupKapasitas1;
    }

    public String getMesinBackupKapasitas2() {
        return mesinBackupKapasitas2;
    }

    public void setMesinBackupKapasitas2(String mesinBackupKapasitas2) {
        this.mesinBackupKapasitas2 = mesinBackupKapasitas2;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNoGrossAkta() {
        return noGrossAkta;
    }

    public void setNoGrossAkta(String noGrossAkta) {
        this.noGrossAkta = noGrossAkta;
    }

    public Date getTanggalGrossAkta() {
        return tanggalGrossAkta;
    }

    public void setTanggalGrossAkta(Date tanggalGrossAkta) {
        this.tanggalGrossAkta = tanggalGrossAkta;
    }

    public String getNoSuratUkur() {
        return noSuratUkur;
    }

    public void setNoSuratUkur(String noSuratUkur) {
        this.noSuratUkur = noSuratUkur;
    }

    public Date getTanggalSuratUkur() {
        return tanggalSuratUkur;
    }

    public void setTanggalSuratUkur(Date tanggalSuratUkur) {
        this.tanggalSuratUkur = tanggalSuratUkur;
    }

    public String getNoSertifikatLambung() {
        return noSertifikatLambung;
    }

    public void setNoSertifikatLambung(String noSertifikatLambung) {
        this.noSertifikatLambung = noSertifikatLambung;
    }

    public Date getTanggalSertifikatLambung() {
        return tanggalSertifikatLambung;
    }

    public void setTanggalSertifikatLambung(Date tanggalSertifikatLambung) {
        this.tanggalSertifikatLambung = tanggalSertifikatLambung;
    }

    public Date getExpiredSertifikatLambung() {
        return expiredSertifikatLambung;
    }

    public void setExpiredSertifikatLambung(Date expiredSertifikatLambung) {
        this.expiredSertifikatLambung = expiredSertifikatLambung;
    }

    public String getNoSertifikatGaris() {
        return noSertifikatGaris;
    }

    public void setNoSertifikatGaris(String noSertifikatGaris) {
        this.noSertifikatGaris = noSertifikatGaris;
    }

    public Date getTanggalSertifikatGaris() {
        return tanggalSertifikatGaris;
    }

    public void setTanggalSertifikatGaris(Date tanggalSertifikatGaris) {
        this.tanggalSertifikatGaris = tanggalSertifikatGaris;
    }

    public String getNoSertifikatMesin() {
        return noSertifikatMesin;
    }

    public void setNoSertifikatMesin(String noSertifikatMesin) {
        this.noSertifikatMesin = noSertifikatMesin;
    }

    public Date getTanggalSertifikatMesin() {
        return tanggalSertifikatMesin;
    }

    public void setTanggalSertifikatMesin(Date tanggalSertifikatMesin) {
        this.tanggalSertifikatMesin = tanggalSertifikatMesin;
    }

    public Date getExpiredSertifikatMesin() {
        return expiredSertifikatMesin;
    }

    public void setExpiredSertifikatMesin(Date expiredSertifikatMesin) {
        this.expiredSertifikatMesin = expiredSertifikatMesin;
    }

    public String getStatusJaminanKapal() {
        return statusJaminanKapal;
    }

    public void setStatusJaminanKapal(String statusJaminanKapal) {
        this.statusJaminanKapal = statusJaminanKapal;
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