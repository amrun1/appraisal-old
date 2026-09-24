package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DocumentLpaTypeDto  implements Serializable {

    private Long id;
    @NotNull private String name;
    private Long assetTypeId;
    private String assetTypeName;
    private Long appraisalId;
    private String appraisalName;
    private String jenisData;
    @NotNull private Boolean mandatory;
    @NotNull private Boolean surveyYn;
    @NotNull private Boolean readFileYn;

    public String getJenisData() {
        return jenisData;
    }

    public void setJenisData(String jenisData) {
        this.jenisData = jenisData;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getAppraisalName() {
        return appraisalName;
    }

    public void setAppraisalName(String appraisalName) {
        this.appraisalName = appraisalName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(Long assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public Long getAppraisalId() {
        return appraisalId;
    }

    public void setAppraisalId(Long appraisalId) {
        this.appraisalId = appraisalId;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Boolean getSurveyYn() {
        return surveyYn;
    }

    public void setSurveyYn(Boolean surveyYn) {
        this.surveyYn = surveyYn;
    }

    public Boolean getReadFileYn() {
        return readFileYn;
    }

    public void setReadFileYn(Boolean readFileYn) {
        this.readFileYn = readFileYn;
    }
}