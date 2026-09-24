package com.permata.appraisal2.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.Serializable;
import java.util.Date;

public class SurveyInspeksiDto extends SurveyPointDto implements Validator, Serializable {

    @Override
    public boolean supports(Class<?> aClass) {
        return SurveyInspeksiDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }



    public void validate(Errors errors) {
        validate(this, errors);
    }

    private Long id;
    private Long idDetail;
    private String jenisBarang;
    private String nomorDaftar;
    private Date tanggalDaftar;
    private String jenisTempat;
    private String infoAlamatTempat;
    private String alamatLain;
    private String statusTempat;
    private String kondisiTempat;
    private String aktifitasKerja;
    private String peralatanGudang;
    private String alatPemadam;
    private String keamanan;
    private String kondisiJalan;
    private String lingkunganSekitar;
    private String metodePeriksa;
    private String tempatPersediaan;
    private String susunanTempat;
    private String packing;
    private String subTotalGoods;
    private String subTotalHalfGoods;
    private String subTotalRawMaterial;
    private String subTotalGoodsFisik;
    private String subTotalHalfGoodsFisik;
    private String subTotalRawMaterialFisik;

    public SurveyInspeksiDto() {
    }

    public SurveyInspeksiDto(Long idDetail) {
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

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getNomorDaftar() {
        return nomorDaftar;
    }

    public void setNomorDaftar(String nomorDaftar) {
        this.nomorDaftar = nomorDaftar;
    }

    public Date getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(Date tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getJenisTempat() {
        return jenisTempat;
    }

    public void setJenisTempat(String jenisTempat) {
        this.jenisTempat = jenisTempat;
    }

    public String getInfoAlamatTempat() {
        return infoAlamatTempat;
    }

    public void setInfoAlamatTempat(String infoAlamatTempat) {
        this.infoAlamatTempat = infoAlamatTempat;
    }

    public String getAlamatLain() {
        return alamatLain;
    }

    public void setAlamatLain(String alamatLain) {
        this.alamatLain = alamatLain;
    }

    public String getStatusTempat() {
        return statusTempat;
    }

    public void setStatusTempat(String statusTempat) {
        this.statusTempat = statusTempat;
    }

    public String getKondisiTempat() {
        return kondisiTempat;
    }

    public void setKondisiTempat(String kondisiTempat) {
        this.kondisiTempat = kondisiTempat;
    }

    public String getAktifitasKerja() {
        return aktifitasKerja;
    }

    public void setAktifitasKerja(String aktifitasKerja) {
        this.aktifitasKerja = aktifitasKerja;
    }

    public String getPeralatanGudang() {
        return peralatanGudang;
    }

    public void setPeralatanGudang(String peralatanGudang) {
        this.peralatanGudang = peralatanGudang;
    }

    public String getAlatPemadam() {
        return alatPemadam;
    }

    public void setAlatPemadam(String alatPemadam) {
        this.alatPemadam = alatPemadam;
    }

    public String getKeamanan() {
        return keamanan;
    }

    public void setKeamanan(String keamanan) {
        this.keamanan = keamanan;
    }

    public String getKondisiJalan() {
        return kondisiJalan;
    }

    public void setKondisiJalan(String kondisiJalan) {
        this.kondisiJalan = kondisiJalan;
    }

    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    public String getMetodePeriksa() {
        return metodePeriksa;
    }

    public void setMetodePeriksa(String metodePeriksa) {
        this.metodePeriksa = metodePeriksa;
    }

    public String getTempatPersediaan() {
        return tempatPersediaan;
    }

    public void setTempatPersediaan(String tempatPersediaan) {
        this.tempatPersediaan = tempatPersediaan;
    }

    public String getSusunanTempat() {
        return susunanTempat;
    }

    public void setSusunanTempat(String susunanTempat) {
        this.susunanTempat = susunanTempat;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getSubTotalGoods() {
        return subTotalGoods;
    }

    public void setSubTotalGoods(String subTotalGoods) {
        this.subTotalGoods = subTotalGoods;
    }

    public String getSubTotalHalfGoods() {
        return subTotalHalfGoods;
    }

    public void setSubTotalHalfGoods(String subTotalHalfGoods) {
        this.subTotalHalfGoods = subTotalHalfGoods;
    }

    public String getSubTotalRawMaterial() {
        return subTotalRawMaterial;
    }

    public void setSubTotalRawMaterial(String subTotalRawMaterial) {
        this.subTotalRawMaterial = subTotalRawMaterial;
    }

    public String getSubTotalGoodsFisik() {
        return subTotalGoodsFisik;
    }

    public void setSubTotalGoodsFisik(String subTotalGoodsFisik) {
        this.subTotalGoodsFisik = subTotalGoodsFisik;
    }

    public String getSubTotalHalfGoodsFisik() {
        return subTotalHalfGoodsFisik;
    }

    public void setSubTotalHalfGoodsFisik(String subTotalHalfGoodsFisik) {
        this.subTotalHalfGoodsFisik = subTotalHalfGoodsFisik;
    }

    public String getSubTotalRawMaterialFisik() {
        return subTotalRawMaterialFisik;
    }

    public void setSubTotalRawMaterialFisik(String subTotalRawMaterialFisik) {
        this.subTotalRawMaterialFisik = subTotalRawMaterialFisik;
    }
}