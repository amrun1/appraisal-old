package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Bachri on 3/31/2017.
 */
public class JenisAppraisalDto  implements Serializable {
    private Long id;
    @NotNull
    private String name;
    private Boolean external;

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

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }
}
