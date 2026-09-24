package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * model as DocumentLpaType
 */
public class LpaImageDto  implements Serializable {

    private Long id;
    private Long commonId;
    @NotNull private Long lpaId;
    @NotNull private Long idDocumentLpa;
    private String namaDocumentLpa;
    private Boolean readFileYn;
    private String contentType;
    private String catatan;
    private byte[] image;

    public LpaImageDto() {

    }

    public LpaImageDto(Long lpaId) {
        this.lpaId = lpaId;
    }

    public String getNamaDocumentLpa() {
        return namaDocumentLpa;
    }

    public void setNamaDocumentLpa(String namaDocumentLpa) {
        this.namaDocumentLpa = namaDocumentLpa;
    }

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLpaId() {
        return lpaId;
    }

    public void setLpaId(Long lpaId) {
        this.lpaId = lpaId;
    }

    public Long getIdDocumentLpa() {
        return idDocumentLpa;
    }

    public void setIdDocumentLpa(Long idDocumentLpa) {
        this.idDocumentLpa = idDocumentLpa;
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

    public Boolean getReadFileYn() {
        return readFileYn;
    }

    public void setReadFileYn(Boolean readFileYn) {
        this.readFileYn = readFileYn;
    }

}
