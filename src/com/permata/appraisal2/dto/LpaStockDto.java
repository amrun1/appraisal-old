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

public class LpaStockDto extends LpaUmumDto implements Validator, Serializable {

    @Override
    public boolean supports(Class<?> aClass) {
        return LpaStockDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "kategoriJenisAppraisal",
                AppConstans.ErrorMessage.CODE_REQUIRED,
                String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kategori Appraisal"));
        ValidationUtils.rejectIfEmpty(errors, "faktorMarketability", "name.empty");
        LpaStockDto w = (LpaStockDto) o;
        if (w.getFaktorMarketability().equalsIgnoreCase("Tidak Marketable")) {
            if (StringUtils.isBlank(w.getFaktorMarketCatatan())) {
                ValidationUtils.rejectIfEmpty(errors, "faktorMarketCatatan", "required", "Tidak Marketable, Catatan harus ada");
            }
        }
        if(AppConstans.JenisAppraisalCategory.EKSTERNAL.equalsIgnoreCase(w.getKategoriJenisAppraisal())){
            ValidationUtils.rejectIfEmpty(errors, "nilaiDokumenEks",
//                    ValidationUtils.rejectIfEmpty(errors, "totalDokumenEks",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Total Dokumen"));
            ValidationUtils.rejectIfEmpty(errors, "nilaiFisikEks",
//                    ValidationUtils.rejectIfEmpty(errors, "totalFisikEks",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Total Fisik"));
            ValidationUtils.rejectIfEmpty(errors, "persentaseEks",
//                    ValidationUtils.rejectIfEmpty(errors, "persenLikuidasiEks",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Persentase Likuidasi"));

        }
        // validasi untuk external
        // validasi untuk one to many yang ada
    }

    public void validate(Errors errors) {
        validate(this, errors);
    }

    private Long id;
    // data agunan
    @NotNull private String jenisPersedian;
    @NotNull private String asalPembuatan;
    @NotNull private String penempatanBarang;
    @NotNull private String kemasanBarang;
    @NotNull private String kondisiBarang;
    @NotNull private String metodePemeriksaan;
    @NotNull private String jumlahPersedian;
    // tempat penyimpanan
    @NotNull private String jenisPenyimpanan;
    @NotNull private String kondisiPenyimpanan;
    @NotNull private String lingkunganSekitar;
    @NotNull private String peralatanGudang;
    // data dokumen
    @NotNull private String jenisDokumen;
    @NotNull private String nomorDokumen;
    @NotNull private Date tanggalDokumen;
    // penilaian atas agunan
    @NotNull private BigDecimal nilaiDokumen;
    @NotNull private Boolean berinilaiDokumen;
    @NotNull private BigDecimal nilaiFisik;
    @NotNull private Boolean berinilaiFisik;
    @NotNull private Double persentase;
    @NotNull private BigDecimal likuidasiDokumen;
    @NotNull private BigDecimal likuidasiFisik;
    // penilaian atas agunan Eksternal
    private BigDecimal nilaiDokumenEks;
    private Boolean berinilaiDokumenEks;
    private BigDecimal nilaiFisikEks;
    private Boolean berinilaiFisikEks;
    private Double persentaseEks;
    private BigDecimal likuidasiDokumenEks;
    private BigDecimal likuidasiFisikEks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenisPersedian() {
        return jenisPersedian;
    }

    public void setJenisPersedian(String jenisPersedian) {
        this.jenisPersedian = jenisPersedian;
    }

    public String getAsalPembuatan() {
        return asalPembuatan;
    }

    public void setAsalPembuatan(String asalPembuatan) {
        this.asalPembuatan = asalPembuatan;
    }

    public String getPenempatanBarang() {
        return penempatanBarang;
    }

    public void setPenempatanBarang(String penempatanBarang) {
        this.penempatanBarang = penempatanBarang;
    }

    public String getKemasanBarang() {
        return kemasanBarang;
    }

    public void setKemasanBarang(String kemasanBarang) {
        this.kemasanBarang = kemasanBarang;
    }

    public String getKondisiBarang() {
        return kondisiBarang;
    }

    public void setKondisiBarang(String kondisiBarang) {
        this.kondisiBarang = kondisiBarang;
    }

    public String getMetodePemeriksaan() {
        return metodePemeriksaan;
    }

    public void setMetodePemeriksaan(String metodePemeriksaan) {
        this.metodePemeriksaan = metodePemeriksaan;
    }

    public String getJumlahPersedian() {
        return jumlahPersedian;
    }

    public void setJumlahPersedian(String jumlahPersedian) {
        this.jumlahPersedian = jumlahPersedian;
    }

    public String getJenisPenyimpanan() {
        return jenisPenyimpanan;
    }

    public void setJenisPenyimpanan(String jenisPenyimpanan) {
        this.jenisPenyimpanan = jenisPenyimpanan;
    }

    public String getKondisiPenyimpanan() {
        return kondisiPenyimpanan;
    }

    public void setKondisiPenyimpanan(String kondisiPenyimpanan) {
        this.kondisiPenyimpanan = kondisiPenyimpanan;
    }

    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    public String getPeralatanGudang() {
        return peralatanGudang;
    }

    public void setPeralatanGudang(String peralatanGudang) {
        this.peralatanGudang = peralatanGudang;
    }

    public String getJenisDokumen() {
        return jenisDokumen;
    }

    public void setJenisDokumen(String jenisDokumen) {
        this.jenisDokumen = jenisDokumen;
    }

    public String getNomorDokumen() {
        return nomorDokumen;
    }

    public void setNomorDokumen(String nomorDokumen) {
        this.nomorDokumen = nomorDokumen;
    }

    public Date getTanggalDokumen() {
        return tanggalDokumen;
    }

    public void setTanggalDokumen(Date tanggalDokumen) {
        this.tanggalDokumen = tanggalDokumen;
    }

    public BigDecimal getNilaiDokumen() {
        return nilaiDokumen;
    }

    public void setNilaiDokumen(BigDecimal nilaiDokumen) {
        this.nilaiDokumen = nilaiDokumen;
    }

    public Boolean getBerinilaiDokumen() {
        return berinilaiDokumen;
    }

    public void setBerinilaiDokumen(Boolean berinilaiDokumen) {
        this.berinilaiDokumen = berinilaiDokumen;
    }

    public BigDecimal getNilaiFisik() {
        return nilaiFisik;
    }

    public void setNilaiFisik(BigDecimal nilaiFisik) {
        this.nilaiFisik = nilaiFisik;
    }

    public Boolean getBerinilaiFisik() {
        return berinilaiFisik;
    }

    public void setBerinilaiFisik(Boolean berinilaiFisik) {
        this.berinilaiFisik = berinilaiFisik;
    }

    public Double getPersentase() {
        return persentase;
    }

    public void setPersentase(Double persentase) {
        this.persentase = persentase;
    }

    public BigDecimal getLikuidasiDokumen() {
        return likuidasiDokumen;
    }

    public void setLikuidasiDokumen(BigDecimal likuidasiDokumen) {
        this.likuidasiDokumen = likuidasiDokumen;
    }

    public BigDecimal getLikuidasiFisik() {
        return likuidasiFisik;
    }

    public void setLikuidasiFisik(BigDecimal likuidasiFisik) {
        this.likuidasiFisik = likuidasiFisik;
    }

    public BigDecimal getNilaiDokumenEks() {
        return nilaiDokumenEks;
    }

    public void setNilaiDokumenEks(BigDecimal nilaiDokumenEks) {
        this.nilaiDokumenEks = nilaiDokumenEks;
    }

    public Boolean getBerinilaiDokumenEks() {
        return berinilaiDokumenEks;
    }

    public void setBerinilaiDokumenEks(Boolean berinilaiDokumenEks) {
        this.berinilaiDokumenEks = berinilaiDokumenEks;
    }

    public BigDecimal getNilaiFisikEks() {
        return nilaiFisikEks;
    }

    public void setNilaiFisikEks(BigDecimal nilaiFisikEks) {
        this.nilaiFisikEks = nilaiFisikEks;
    }

    public Boolean getBerinilaiFisikEks() {
        return berinilaiFisikEks;
    }

    public void setBerinilaiFisikEks(Boolean berinilaiFisikEks) {
        this.berinilaiFisikEks = berinilaiFisikEks;
    }

    public Double getPersentaseEks() {
        return persentaseEks;
    }

    public void setPersentaseEks(Double persentaseEks) {
        this.persentaseEks = persentaseEks;
    }

    public BigDecimal getLikuidasiDokumenEks() {
        return likuidasiDokumenEks;
    }

    public void setLikuidasiDokumenEks(BigDecimal likuidasiDokumenEks) {
        this.likuidasiDokumenEks = likuidasiDokumenEks;
    }

    public BigDecimal getLikuidasiFisikEks() {
        return likuidasiFisikEks;
    }

    public void setLikuidasiFisikEks(BigDecimal likuidasiFisikEks) {
        this.likuidasiFisikEks = likuidasiFisikEks;
    }
}