package com.permata.appraisal2.dto;

import com.permata.appraisal2.common.AppConstans;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LpaInspeksiStockDto extends LpaUmumDto implements Validator, Serializable {

    @Override
    public boolean supports(Class<?> aClass) {
        return LpaInspeksiStockDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "kategoriJenisAppraisal",
//                AppConstans.ErrorMessage.CODE_REQUIRED,
//                String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kategori Appraisal"));
//        ValidationUtils.rejectIfEmpty(errors, "faktorMarketability", "name.empty");
//        LpaInspeksiStockDto w = (LpaInspeksiStockDto) o;
//        if (w.getFaktorMarketability().equalsIgnoreCase("Tidak Marketable")) {
//            if (StringUtils.isBlank(w.getFaktorMarketCatatan())) {
//                ValidationUtils.rejectIfEmpty(errors, "faktorMarketCatatan", "required", "Tidak Marketable, Catatan harus ada");
//            }
//        }
//        if(AppConstans.JenisAppraisalCategory.EKSTERNAL.equalsIgnoreCase(w.getKategoriJenisAppraisal())){
//            ValidationUtils.rejectIfEmpty(errors, "nilaiDokumenEks",
////                    ValidationUtils.rejectIfEmpty(errors, "totalDokumenEks",
//                    AppConstans.ErrorMessage.CODE_REQUIRED,
//                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Total Dokumen"));
//            ValidationUtils.rejectIfEmpty(errors, "nilaiFisikEks",
////                    ValidationUtils.rejectIfEmpty(errors, "totalFisikEks",
//                    AppConstans.ErrorMessage.CODE_REQUIRED,
//                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Total Fisik"));
//            ValidationUtils.rejectIfEmpty(errors, "persentaseEks",
////                    ValidationUtils.rejectIfEmpty(errors, "persenLikuidasiEks",
//                    AppConstans.ErrorMessage.CODE_REQUIRED,
//                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Persentase Likuidasi"));
//
//        }
        // validasi untuk external
        // validasi untuk one to many yang ada
    }

    public void validate(Errors errors) {
        validate(this, errors);
    }

    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}