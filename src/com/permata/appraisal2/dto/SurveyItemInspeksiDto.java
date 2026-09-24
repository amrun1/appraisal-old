package com.permata.appraisal2.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.Serializable;

public class SurveyItemInspeksiDto implements Validator, Serializable {

    @Override
    public boolean supports(Class<?> aClass) {
        return SurveyItemInspeksiDto.class.equals(aClass);
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
    private String type;
    private String name;
    private String totalDoc;
    private String totalFisik;
    private Long surveyInspeksiId;

    public SurveyItemInspeksiDto() {
    }

    public SurveyItemInspeksiDto(Long surveyInspeksiId) {
        this.surveyInspeksiId = surveyInspeksiId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalDoc() {
        return totalDoc;
    }

    public void setTotalDoc(String totalDoc) {
        this.totalDoc = totalDoc;
    }

    public String getTotalFisik() {
        return totalFisik;
    }

    public void setTotalFisik(String totalFisik) {
        this.totalFisik = totalFisik;
    }

    public Long getSurveyInspeksiId() {
        return surveyInspeksiId;
    }

    public void setSurveyInspeksiId(Long surveyInspeksiId) {
        this.surveyInspeksiId = surveyInspeksiId;
    }
}