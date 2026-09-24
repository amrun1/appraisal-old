package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DocumentTypeDto  implements Serializable {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long idAssetType;
    @NotNull
    private Boolean jatuhTempo;

    public DocumentTypeDto() {
    }

    public DocumentTypeDto(Long idAssetType) {
        this.idAssetType = idAssetType;
        this.jatuhTempo = false;
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

    public Long getIdAssetType() {
        return idAssetType;
    }

    public void setIdAssetType(Long idAssetType) {
        this.idAssetType = idAssetType;
    }

    public Boolean getJatuhTempo() {
        return jatuhTempo;
    }

    public void setJatuhTempo(Boolean jatuhTempo) {
        this.jatuhTempo = jatuhTempo;
    }
}