package com.permata.appraisal2.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DataAttactmentDto  implements Serializable {

    private Long id;
    @NotNull
    private String name;
    private String extension;
    private byte[] file;
    @NotNull
    private Long dataDetailId;
    @NotNull
    private Long documentTypeId;
    private String documentTypeName;
    private Long assetType;
    private Long dataCommonId;

    public DataAttactmentDto(){
        super();
    }

    public DataAttactmentDto(Long dataDetailId){
        this.dataDetailId = dataDetailId;
    }

    public DataAttactmentDto(Long dataDetailId, Long documentTypeId){
        this.dataDetailId = dataDetailId;
        this.documentTypeId = documentTypeId;
    }

    public Long getDataCommonId() {
        return dataCommonId;
    }

    public void setDataCommonId(Long dataCommonId) {
        this.dataCommonId = dataCommonId;
    }

    public Long getAssetType() {
        return assetType;
    }

    public void setAssetType(Long assetType) {
        this.assetType = assetType;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Long getDataDetailId() {
        return dataDetailId;
    }

    public void setDataDetailId(Long dataDetailId) {
        this.dataDetailId = dataDetailId;
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DataAttactmentDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append(", file=");
        if (file == null) sb.append("null");
        else {
            sb.append(file.length);
        }
        sb.append(", dataDetailId=").append(dataDetailId);
        sb.append(", documentTypeId=").append(documentTypeId);
        sb.append(", documentTypeName='").append(documentTypeName).append('\'');
        sb.append(", assetType=").append(assetType);
        sb.append(", dataCommonId=").append(dataCommonId);
        sb.append('}');
        return sb.toString();
    }
}
