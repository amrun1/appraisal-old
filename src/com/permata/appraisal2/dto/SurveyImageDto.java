package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SurveyImageDto implements Serializable {

    private Long id;
    @NotNull private Long commonId;
    private Long detailId;
    // private String category;
    @NotNull private Long idDocumentLpa;
    private String namaDocumentLpa;
    private String contentType;
    private String catatan;
    private byte[] image;
    private String content;

    private BigInteger ids;

    public SurveyImageDto() {
    }

    public SurveyImageDto(Long commonId) {
        this.commonId = commonId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public Long getIdDocumentLpa() {
        return idDocumentLpa;
    }

    public void setIdDocumentLpa(Long idDocumentLpa) {
        this.idDocumentLpa = idDocumentLpa;
    }

    public String getNamaDocumentLpa() {
        return namaDocumentLpa;
    }

    public void setNamaDocumentLpa(String namaDocumentLpa) {
        this.namaDocumentLpa = namaDocumentLpa;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigInteger getIds() {
        return ids;
    }

    public void setIds(BigInteger ids) {
        this.ids = ids;
    }
}
