package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by SB on 4/17/2018.
 */
public class LpaItemInspeksiDto implements Serializable {
    private Long id;
    private Long lpaInspeksiId;
    private Long lpaId;
    private String type;
    @NotNull String name;
    @NotNull private String totalDoc;
    @NotNull private String totalFisik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLpaInspeksiId() {
        return lpaInspeksiId;
    }

    public void setLpaInspeksiId(Long lpaInspeksiId) {
        this.lpaInspeksiId = lpaInspeksiId;
    }

    public Long getLpaId() {
        return lpaId;
    }

    public void setLpaId(Long lpaId) {
        this.lpaId = lpaId;
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
}
