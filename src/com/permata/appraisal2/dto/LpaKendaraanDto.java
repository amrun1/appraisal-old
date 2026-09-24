package com.permata.appraisal2.dto;

import com.permata.appraisal2.common.AppConstans;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LpaKendaraanDto extends LpaUmumDto implements Validator, Serializable {

    @Override
    public boolean supports(Class<?> aClass) {
        return LpaKendaraanDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "kategoriJenisAppraisal",
                AppConstans.ErrorMessage.CODE_REQUIRED,
                String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kategori Appraisal"));
        ValidationUtils.rejectIfEmpty(errors, "faktorMarketability", "name.empty");
        LpaKendaraanDto w = (LpaKendaraanDto) o;
        if (w.getFaktorMarketability().equalsIgnoreCase("Tidak Marketable")) {
            if (StringUtils.isBlank(w.getFaktorMarketCatatan())) {
                ValidationUtils.rejectIfEmpty(errors, "faktorMarketCatatan", "required", "Tidak Marketable, Catatan harus ada");
            }
        }
        if(w.isEksternal()){
            ValidationUtils.rejectIfEmpty(errors, "totalDokumenEks",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Total Dokumen"));
            ValidationUtils.rejectIfEmpty(errors, "totalFisikEks",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Total Fisik"));
            ValidationUtils.rejectIfEmpty(errors, "persenLikuidasiEks",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Persentase Likuidasi"));
            ValidationUtils.rejectIfEmpty(errors, "opini",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Opini Internal Atas Eksternal"));
            ValidationUtils.rejectIfEmpty(errors, "deviasiDoc",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Deviasi Dokumen"));
            ValidationUtils.rejectIfEmpty(errors, "deviasiFisik",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Deviasi Fisik"));
        }
        // validasi untuk external
        // validasi untuk one to many yang ada
    }

    public void validate(Errors errors) {
        validate(this, errors);
    }

    private Long id;

    // penilaian agunan Internal
    @NotNull private Double persenLikuidasi;
    @NotNull private BigDecimal totalDokumen;
    @NotNull private BigDecimal totalFisik;
    @NotNull private BigDecimal likuidasiDokumen;
    @NotNull private Boolean beriNilaiDokumen;
    @NotNull private Boolean beriNilaiFisik;
    @NotNull private BigDecimal likuidasiFisik;

    // penilaian agunan eksternal
    private Double persenLikuidasiEks;
    private BigDecimal totalDokumenEks;
    private BigDecimal totalFisikEks;
    private BigDecimal likuidasiDokumenEks;
    private Boolean beriNilaiDokumenEks;
    private Boolean beriNilaiFisikEks;
    private BigDecimal likuidasiFisikEks;

    private List<LpaItemKendaraanDto> kendaraan = new ArrayList<LpaItemKendaraanDto>();

    // getter setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LpaItemKendaraanDto> getKendaraan() {
        return kendaraan;
    }

    public void addKendaraan(LpaItemKendaraanDto item){
        kendaraan.add(item);
    }

    public void setKendaraan(List<LpaItemKendaraanDto> kendaraan) {
        this.kendaraan = kendaraan;
    }

    public Double getPersenLikuidasi() {
        return persenLikuidasi;
    }

    public void setPersenLikuidasi(Double persenLikuidasi) {
        this.persenLikuidasi = persenLikuidasi;
    }

    public BigDecimal getTotalDokumen() {
        return totalDokumen;
    }

    public void setTotalDokumen(BigDecimal totalDokumen) {
        this.totalDokumen = totalDokumen;
    }

    public BigDecimal getTotalFisik() {
        return totalFisik;
    }

    public void setTotalFisik(BigDecimal totalFisik) {
        this.totalFisik = totalFisik;
    }

    public BigDecimal getLikuidasiDokumen() {
        return likuidasiDokumen;
    }

    public void setLikuidasiDokumen(BigDecimal likuidasiDokumen) {
        this.likuidasiDokumen = likuidasiDokumen;
    }

    public Boolean getBeriNilaiDokumen() {
        return beriNilaiDokumen;
    }

    public void setBeriNilaiDokumen(Boolean beriNilaiDokumen) {
        this.beriNilaiDokumen = beriNilaiDokumen;
    }

    public Boolean getBeriNilaiFisik() {
        return beriNilaiFisik;
    }

    public void setBeriNilaiFisik(Boolean beriNilaiFisik) {
        this.beriNilaiFisik = beriNilaiFisik;
    }

    public BigDecimal getLikuidasiFisik() {
        return likuidasiFisik;
    }

    public void setLikuidasiFisik(BigDecimal likuidasiFisik) {
        this.likuidasiFisik = likuidasiFisik;
    }

    public Double getPersenLikuidasiEks() {
        return persenLikuidasiEks;
    }

    public void setPersenLikuidasiEks(Double persenLikuidasiEks) {
        this.persenLikuidasiEks = persenLikuidasiEks;
    }

    public BigDecimal getTotalDokumenEks() {
        return totalDokumenEks;
    }

    public void setTotalDokumenEks(BigDecimal totalDokumenEks) {
        this.totalDokumenEks = totalDokumenEks;
    }

    public BigDecimal getTotalFisikEks() {
        return totalFisikEks;
    }

    public void setTotalFisikEks(BigDecimal totalFisikEks) {
        this.totalFisikEks = totalFisikEks;
    }

    public BigDecimal getLikuidasiDokumenEks() {
        return likuidasiDokumenEks;
    }

    public void setLikuidasiDokumenEks(BigDecimal likuidasiDokumenEks) {
        this.likuidasiDokumenEks = likuidasiDokumenEks;
    }

    public Boolean getBeriNilaiDokumenEks() {
        return beriNilaiDokumenEks;
    }

    public void setBeriNilaiDokumenEks(Boolean beriNilaiDokumenEks) {
        this.beriNilaiDokumenEks = beriNilaiDokumenEks;
    }

    public Boolean getBeriNilaiFisikEks() {
        return beriNilaiFisikEks;
    }

    public void setBeriNilaiFisikEks(Boolean beriNilaiFisikEks) {
        this.beriNilaiFisikEks = beriNilaiFisikEks;
    }

    public BigDecimal getLikuidasiFisikEks() {
        return likuidasiFisikEks;
    }

    public void setLikuidasiFisikEks(BigDecimal likuidasiFisikEks) {
        this.likuidasiFisikEks = likuidasiFisikEks;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(super.toString());
        sb.append("LpaKendaraanDto{");
        sb.append("persenLikuidasi=").append(persenLikuidasi);
        sb.append(", totalDokumen=").append(totalDokumen);
        sb.append(", totalFisik=").append(totalFisik);
        sb.append(", likuidasiDokumen=").append(likuidasiDokumen);
        sb.append(", beriNilaiDokumen=").append(beriNilaiDokumen);
        sb.append(", beriNilaiFisik=").append(beriNilaiFisik);
        sb.append(", likuidasiFisik=").append(likuidasiFisik);
        sb.append(", persenLikuidasiEks=").append(persenLikuidasiEks);
        sb.append(", totalDokumenEks=").append(totalDokumenEks);
        sb.append(", totalFisikEks=").append(totalFisikEks);
        sb.append(", likuidasiDokumenEks=").append(likuidasiDokumenEks);
        sb.append(", beriNilaiDokumenEks=").append(beriNilaiDokumenEks);
        sb.append(", beriNilaiFisikEks=").append(beriNilaiFisikEks);
        sb.append(", likuidasiFisikEks=").append(likuidasiFisikEks);
        sb.append('}');
        return sb.toString();
    }
}